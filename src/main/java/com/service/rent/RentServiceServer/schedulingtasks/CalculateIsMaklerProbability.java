package com.service.rent.RentServiceServer.schedulingtasks;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.enums.ComplaintSeverity;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.UserComplaintRepo;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class CalculateIsMaklerProbability {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private UserComplaintRepo userComplaintRepo;

    private static final Logger log =
            LoggerFactory.getLogger(CalculateIsMaklerProbability.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(cron = "* 0 1 * * *") / вночі раз в день
    @Scheduled(cron = "*/10 * * * * *") // що 10 секунд
    public void reportCurrentTime() {
        log.info("--------> Starting makler probability calculation <----------");

        List<Account> landLords = accountRepo.getAllByOwningApartmentsCountGreaterThan(0);

        for (Account landlord : landLords) {
            landlord.setMaklerProbabilityScore(calculateBeingMaklerProbability(landlord));
        }

        accountRepo.saveAll(landLords);

        log.info("--------> Finished makler probability calculation <----------");
    }

    private double calculateBeingMaklerProbability(Account landlord) {
        return apartmentsCountFunction(landlord) +
               isMaklerSeverityComplaintsFunction(landlord) +
               possibleMaklerSeverityComplaintsFunction(landlord);
    }

    private double apartmentsCountFunction(Account landlord) {
        if (BooleanUtils.isTrue(landlord.getDocumentsVerified())) {
            return 0;
        }

        if (landlord.getOwningApartmentsCount() < 2) {
            return 0;
        }

        return Math.exp(-1L / ((long) landlord.getOwningApartmentsCount() - 1.0));
    }

    private double isMaklerSeverityComplaintsFunction(Account landlord) {
        double complaintCount = userComplaintRepo
                .getAllByComplaintSeverityAndToAccountAndIsDeletedIsFalse(ComplaintSeverity.IS_MAKLER, landlord).size();

        if (complaintCount == 0) {
            return 0;
        }

        return Math.exp(-0.2 / (complaintCount - 0.7));
    }

    private double possibleMaklerSeverityComplaintsFunction(Account landlord) {
        double complaintCount = userComplaintRepo
                .getAllByComplaintSeverityAndToAccountAndIsDeletedIsFalse(ComplaintSeverity.POSSIBLY_MAKLER, landlord).size();

        if (complaintCount == 0) {
            return 0;
        }

        return Math.exp(-7L / complaintCount);
    }
}
