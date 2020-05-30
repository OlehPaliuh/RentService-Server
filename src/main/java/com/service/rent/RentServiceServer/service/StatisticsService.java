package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.ApartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StatisticsService {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ApartmentRepo apartmentRepo;


    public Long countNewAccounts() {
        return accountRepo.countAccountsByRegisteredAtAfter(LocalDateTime.now().minusMonths(1));
    }

    public Long countAccountsOnline() {
        return accountRepo.countAccountsByIsOnlineTrue();
    }

    public Long countNewApartments() {
        return apartmentRepo.countApartmentsByCreateDateAfter(LocalDateTime.now().minusMonths(1));
    }

    public Long countRieltorsDetected() {
        return accountRepo.countAccountsByIsLockedTrueAndMaklerProbabilityScoreAfter(0.4);
    }
}
