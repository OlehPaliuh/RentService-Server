package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.enums.RoleName;
import com.service.rent.RentServiceServer.exception.UserAlreadyExistException;
import com.service.rent.RentServiceServer.exception.UserDisabledException;
import com.service.rent.RentServiceServer.exception.UserNotFoundException;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.RoleRepo;
import com.service.rent.RentServiceServer.security.dto.RegisterAccountDto;
import com.service.rent.RentServiceServer.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Iterable<Account> getAccounts() {
        return accountRepo.findAll();
    }

    public List<Account> getAccounts(Integer pageNumber, Integer count) {
        return accountRepo.findAll(PageRequest.of(pageNumber, count)).getContent();
    }

    public Account getAccount(String username) {
        return accountRepo.getAccountByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username " + username + " not found in DB."));
    }

    public Long countAccounts() {
        return accountRepo.count();
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

        return accountRepo.save(account);
    }

    public Account updateAccount(Long accountId, Account updateAccount ) {
        Account account = accountRepo.getAccountById(accountId);

        if(account == null) {
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
        account.setLockReason(lockReason);
        return accountRepo.save(account);
    }

    public Account unlockAccount(String username) {
        Account account = this.getAccount(username);
        if (account.isDisabled()) {
            throw new UserDisabledException("You cannot modify user with username +" + username + " because user is disabled");
        }
        account.setLocked(false);
        account.setLockReason("");
        return accountRepo.save(account);
    }

    public Account deleteAccount(String username, String deletionReason) {
        Account account = this.getAccount(username);
        account.setDisabled(true);
        account.setLockReason(deletionReason);
        account.setLocked(true);
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
        newAccount.setRole(roleRepo.getRoleByName(RoleName.ADMIN));
        newAccount.setDisabled(false);
        newAccount.setRefreshTokenKey(jwtTokenUtil.generateTokenKey());
        newAccount.setLocked(false);
        saveAccount(newAccount);
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

    public int updateAccountRefreshToken(String refreshTokenKey, Long id) {
        return accountRepo.updateAccountRefreshToken(refreshTokenKey, id);
    }

    public Account updateAvatar(Long id, String avatarPath) {
        Account account = accountRepo.getAccountById(id);
        account.setAvatarPath(avatarPath);
        return accountRepo.save(account);
    }
}
