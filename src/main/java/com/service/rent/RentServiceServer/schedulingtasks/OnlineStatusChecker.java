package com.service.rent.RentServiceServer.schedulingtasks;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class OnlineStatusChecker {

    @Autowired
    private AccountRepo accountRepo;

    @Scheduled(cron = "*/20 * * * * *") // кожні 20 секунд
    public void checkAccountOnlineStatus() {
        List<Account> accountList = accountRepo.findAll();

        for (Account account : accountList) {
            LocalDateTime from = account.getLastLoginTime();
            if (from == null) {
                continue;
            }
            LocalDateTime tempDateTime = LocalDateTime.from(from);

            if (tempDateTime.until(LocalDateTime.now(), ChronoUnit.MINUTES) < 5L) {
                account.setIsOnline(true);
            } else {
                account.setIsOnline(false);
            }
        }

        accountRepo.saveAll(accountList);
    }
}
