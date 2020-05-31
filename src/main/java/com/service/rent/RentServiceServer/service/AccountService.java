package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.UserComplaint;
import com.service.rent.RentServiceServer.entity.enums.RoleName;
import com.service.rent.RentServiceServer.exception.UserAlreadyExistException;
import com.service.rent.RentServiceServer.exception.UserDisabledException;
import com.service.rent.RentServiceServer.exception.UserNotFoundException;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.ComplaintRepo;
import com.service.rent.RentServiceServer.repository.RoleRepo;
import com.service.rent.RentServiceServer.security.dto.RegisterAccountDto;
import com.service.rent.RentServiceServer.security.jwt.JwtTokenUtil;
import com.service.rent.RentServiceServer.service.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private ComplaintRepo complaintRepo;

    public Iterable<Account> getAccounts() {
        return accountRepo.findAll();
    }

    public List<Account> getAccounts(Integer pageNumber, Integer count) {
        return accountRepo.findAll(PageRequest.of(pageNumber, count)).getContent();
    }

    public List<Account> getAccounts(Integer pageNumber, Integer count, String query) {
        return accountRepo.findAll(PageRequest.of(pageNumber, count)).getContent();
    }

    public List<Account> getAccountsSortedStortedByMaklerProbabilityScoreDesc(Integer pageNumber, Integer count) {
        return accountRepo.findAllOrderedByMaklerProbabilityScore(
                PageRequest.of(pageNumber, count, Sort.by("maklerProbabilityScore").descending()))
                .getContent();
    }

    public List<Account> getAccountsSortedStortedByMaklerProbabilityScoreDesc(Integer pageNumber, Integer count,
                                                                              String searchQuery) {
        return accountRepo.fullTextSearch('%' + searchQuery + '%',
                PageRequest.of(pageNumber, count, Sort.by("maklerProbabilityScore").descending()))
                .getContent();
    }

    public Account getAccount(String username) {
        return accountRepo.getAccountByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username " + username + " not found in DB."));
    }

    public Long countAccounts() {
        return accountRepo.count();
    }

    public Long countAccounts(String q) {
        return accountRepo.countByFullTextSearch(q);
    }

    public Account createAccount(Account account) throws UserAlreadyExistException {
        if (getByUsername(account.getUsername()) != null) {
            throw new UserAlreadyExistException(String.format("User with username %s already exist.", account.getUsername()));
        }
        return accountRepo.save(account);
    }

    public Account updateAccount(Account account) {
        if (accountRepo.getAccountByUsername(account.getUsername()).isEmpty() &&
                accountRepo.findById(account.getId()).isEmpty()) {
            throw new UserNotFoundException("No such username and id to update User.");
        }

        if (account.getId() == null) {
            account.setId(accountRepo.getAccountByUsername(account.getUsername()).get().getId());
        }

        return accountRepo.saveAndFlush(account);
    }

    public Account updateAccount(Long accountId, Account updateAccount) {
        Account account = accountRepo.getAccountById(accountId);

        if (account == null) {
            throw new UserNotFoundException("No such account to update information about User.");
        }

        account.setFirstName(updateAccount.getFirstName());
        account.setLastName(updateAccount.getLastName());
        account.setEmail(updateAccount.getEmail());
        account.setPhoneNumber(updateAccount.getPhoneNumber());

        return accountRepo.save(account);
    }

    public Account lockAccount(String username, String lockReason) {
        Account account = this.getAccount(username);
        if (account.isDisabled()) {
            throw new UserDisabledException("You cannot modify user with username +" + username + " because user is disabled");
        }
        account.setLocked(true);
        account.setLockTimestamp(java.time.LocalDateTime.now());
        account.setLockReason(lockReason);
        return accountRepo.save(account);
    }

    public Account lockAccount(Long accountId, String lockReason) {
        Account account = accountRepo.getAccountById(accountId);
        if (account.isDisabled()) {
            throw new UserDisabledException("You cannot modify user because user is disabled");
        }
        account.setLocked(true);
        account.setLockTimestamp(java.time.LocalDateTime.now());
        account.setLockReason(lockReason);
        return accountRepo.save(account);
    }

    public Account unlockAccount(String username) {
        Account account = this.getAccount(username);
        if (account.isDisabled()) {
            throw new UserDisabledException("You cannot modify user with username +" + username + " because user is disabled");
        }
        account.setLocked(false);
        account.setUnlockTimestamp(java.time.LocalDateTime.now());
        account.setLockReason("");
        return accountRepo.save(account);
    }

    public Account deleteAccount(String username, String deletionReason) {
        Account account = this.getAccount(username);
        account.setDisabled(true);
        account.setLockReason("Deleted " + deletionReason);
        account.setLocked(true);
        account.setLockTimestamp(java.time.LocalDateTime.now());
        return accountRepo.save(account);
    }

    public void createAccount(RegisterAccountDto account) throws UserAlreadyExistException {
        if (getByUsername(account.getUsername()) != null) {
            throw new UserAlreadyExistException(String.format("User with username %s already exist.", account.getUsername()));
        }
        Account newAccount = new Account();
        newAccount.setFirstName(account.getFirstName());
        newAccount.setLastName(account.getLastName());
        newAccount.setUsername(account.getUsername());
        newAccount.setEmail(account.getEmail());
        newAccount.setPhoneNumber(account.getPhoneNumber());
        newAccount.setPassword(bcryptEncoder.encode(account.getPassword()));
        newAccount.setRole(roleRepo.getRoleByName(RoleName.USER));
        newAccount.setDisabled(true);
        newAccount.setActivationCode(UUID.randomUUID().toString());
        newAccount.setRefreshTokenKey(jwtTokenUtil.generateTokenKey());
        newAccount.setLocked(false);
        Account savedAccount = saveAccount(newAccount);

        if (!StringUtils.isEmpty(savedAccount.getEmail())) {
            String message = String.format(
                    "Hello, %s %s! \n" +
                            "Welcome to Rent Service. Please, visit next link: http://localhost:8080/api/activate/%s",
                    savedAccount.getFirstName(),
                    savedAccount.getLastName(),
                    savedAccount.getActivationCode());

            mailSender.send(savedAccount.getEmail(), "Account activation", message);
        }
    }

    public Account saveAccount(Account account) {
        return accountRepo.save(account);
    }

    public Account getById(Long id) {
        return accountRepo.findById(id).orElse(null);
    }

    public Account getByUsername(String username) {
        return accountRepo.getAccountByUsername(username).orElse(null);
    }

    public void deleteAccount(Long id) {
        accountRepo.delete(getById(id));
    }

    public boolean deleteOwnAccountWithAllInfo(Long id) {
        Account account = accountRepo.getAccountById(id);

        for (UserComplaint userComplaint : complaintRepo.getAllByFromAccount_IdOrToAccount_Id(id, id)) {
            complaintRepo.delete(userComplaint);
        }

        accountRepo.delete(account);

        return true;
    }

    public int updateAccountRefreshToken(String refreshTokenKey, Long id) {
        return accountRepo.updateAccountRefreshToken(refreshTokenKey, id);
    }

    public Account updateAvatar(Long id, String avatarPath) {
        Account account = accountRepo.getAccountById(id);
        account.setAvatarPath(avatarPath);
        return accountRepo.save(account);
    }

    public Account updateLastLoginTime(Long id, LocalDateTime time) {
        Account account = accountRepo.getAccountById(id);
        account.setLastLoginTime(time);
        return accountRepo.save(account);
    }

    public String activateAccount(String code) {
        Account account = accountRepo.getAccountByActivationCode(code);
        if (account == null) {
            return "Activation link is not valid anymore";
        }

        account.setDisabled(false);
        account.setActivationCode(null);

        accountRepo.save(account);
        return "User activated";
    }
}
