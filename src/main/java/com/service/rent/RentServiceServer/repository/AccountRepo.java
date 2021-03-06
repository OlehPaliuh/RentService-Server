package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account getAccountById(Long accountId);

    Optional<Account> getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(String username, String password);

    Optional<Account> getAccountByUsernameAndEmail(String username, String email);

    List<Account> getAllByOwningApartmentsCountGreaterThan(Integer count);

    Long countAccountsByIsOnlineTrue();

    Long countAccountsByIsLockedTrueAndMaklerProbabilityScoreAfter(Double minimumMps);

    List<Account> getAllByIsLockedTrueAndMaklerProbabilityScoreAfter(Double minimumMps);
    List<Account> getAllByIsLockedTrueAndMaklerProbabilityScoreBetween(Double minimumMps, Double maxMps);

    Long countAccountsByRegisteredAtAfter(LocalDateTime date);

    @Modifying
    @Query(value = "UPDATE Account SET refreshTokenKey=:refreshTokenKey WHERE id=:id")
    int updateAccountRefreshToken(String refreshTokenKey, Long id);

    @Query(value = "select acc from Account acc where acc.firstName like ?1 or " +
                   "acc.lastName like ?1 or " +
                   "acc.username like ?1 or " +
                   "acc.email like ?1 or " +
                   "acc.lockReason like ?1 or " +
                   "acc.phoneNumber like ?1 or " +
                   "acc.role.name = ?1 order by acc.maklerProbabilityScore DESC NULLS LAST ")
    Page<Account> fullTextSearch(@Param("text") String text, Pageable pageable);

    @Query(value = "SELECT count(acc) from Account acc where acc.firstName like ?1 or " +
                   "acc.lastName like ?1 or " +
                   "acc.username like ?1 or " +
                   "acc.email like ?1 or " +
                   "acc.lockReason like ?1 or " +
                   "acc.phoneNumber like ?1 or " +
                   "acc.role.name = ?1 ")
    long countByFullTextSearch(@Param("text") String text);

    @Query(value = "select acc from Account acc order by acc.maklerProbabilityScore DESC NULLS LAST")
    Page<Account> findAllOrderedByMaklerProbabilityScore(Pageable pageable);

    Account getAccountByActivationCode(String code);
}
