package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account getAccountById(Long accountId);

    Optional<Account> getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(String username, String password);

    Optional<Account> getAccountByUsernameAndEmail(String username, String email);

    @Modifying
    @Query(value = "UPDATE Account SET refreshTokenKey=:refreshTokenKey WHERE id=:id")
    int updateAccountRefreshToken(String refreshTokenKey, Long id);
}
