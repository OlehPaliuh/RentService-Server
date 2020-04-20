package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.enums.RoleName;
import com.service.rent.RentServiceServer.exception.UserAlreadyExistException;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.RoleRepo;
import com.service.rent.RentServiceServer.security.dto.RegisterAccountDto;
import com.service.rent.RentServiceServer.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
