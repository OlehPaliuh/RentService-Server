package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.entity.UserComplaint;
import com.service.rent.RentServiceServer.entity.enums.ApartmentStatus;
import com.service.rent.RentServiceServer.entity.enums.ComplaintSeverity;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.ApartmentRepo;
import com.service.rent.RentServiceServer.repository.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class StatisticsService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private ComplaintRepo complaintRepo;

    @Autowired
    private ApartmentRepo apartmentRepo;


    public Long countNewAccounts() {
        return accountRepo.countAccountsByRegisteredAtAfter(LocalDateTime.now().minusMonths(1));
    }

    public Long countAccountsOnline() {
        return accountRepo.countAccountsByIsOnlineTrue();
    }

    public Long countNewApartments() {
        return apartmentRepo.countApartmentsByCreateDateAfter(LocalDateTime.now().minusMonths(3));
    }

    public Long countRieltorsDetected() {
        return accountRepo.countAccountsByIsLockedTrueAndMaklerProbabilityScoreAfter(0.4);
    }

    public Map<String, List<Apartment>> cityApartmentsDistribution() {
        Map<String, List<Apartment>> cityApartment = apartmentRepo.findAll().stream()
                                                                  .collect(groupingBy(app -> app.getLocation().getCity()));
        return cityApartment;
    }

    public Map<String, Pair<Long, Long>> complaintsByMonth() {
        Map<String, Pair<Long, Long>> result = new LinkedHashMap<>();

        List<UserComplaint> isMaklerComplaints =
                complaintRepo.getAllByComplaintSeverityEquals(ComplaintSeverity.IS_MAKLER)
                             .stream().sorted(Comparator.comparing(UserComplaint::getCreatedAt))
                             .collect(Collectors.toList());
        List<UserComplaint> possibleMaklerComplaints =
                complaintRepo.getAllByComplaintSeverityEquals(ComplaintSeverity.PROBABLY_MAKLER)
                             .stream().sorted(Comparator.comparing(UserComplaint::getCreatedAt))
                             .collect(Collectors.toList());

        for (UserComplaint isMaklerComplaint : isMaklerComplaints) {
            result.putIfAbsent(isMaklerComplaint.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                               Pair.of(0L, 0L));

            Pair<Long, Long> prevValue = result.get(
                    isMaklerComplaint.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            result.put(
                    isMaklerComplaint.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    Pair.of(prevValue.getFirst() + 1, prevValue.getSecond()));
        }

        for (UserComplaint possibleMaklerComplaint : possibleMaklerComplaints) {
            result.putIfAbsent(
                    possibleMaklerComplaint.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    Pair.of(0L, 0L));
            Pair<Long, Long> prevValuePossibleMakler = result.get(
                    possibleMaklerComplaint.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            result.put(
                    possibleMaklerComplaint.getCreatedAt().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    Pair.of(prevValuePossibleMakler.getFirst(), prevValuePossibleMakler.getSecond() + 1));
        }

        return result;
    }


    public Map<String, Pair<Long, Long>> maklersByMonth() {
        Map<String, Pair<Long, Long>> result = new LinkedHashMap<>();

        List<Account> isMaklers =
                accountRepo.getAllByIsLockedTrueAndMaklerProbabilityScoreAfter(0.7)
                           .stream().sorted(Comparator.comparing(Account::getLockTimestamp))
                           .collect(Collectors.toList());
        List<Account> possiblyMaklers =
                accountRepo.getAllByIsLockedTrueAndMaklerProbabilityScoreBetween(0.4, 0.7)
                           .stream().sorted(Comparator.comparing(Account::getLockTimestamp))
                           .collect(Collectors.toList());

        for (Account isMakler : isMaklers) {
            result.putIfAbsent(isMakler.getLockTimestamp().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                               Pair.of(0L, 0L));

            Pair<Long, Long> prevValue = result.get(
                    isMakler.getLockTimestamp().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            result.put(
                    isMakler.getLockTimestamp().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    Pair.of(prevValue.getFirst() + 1, prevValue.getSecond()));
        }

        for (Account possiblyMakler : possiblyMaklers) {
            result.putIfAbsent(
                    possiblyMakler.getLockTimestamp().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    Pair.of(0L, 0L));
            Pair<Long, Long> prevValuePossibleMakler = result.get(
                    possiblyMakler.getLockTimestamp().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            result.put(
                    possiblyMakler.getLockTimestamp().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    Pair.of(prevValuePossibleMakler.getFirst(), prevValuePossibleMakler.getSecond() + 1));
        }

        return result;
    }

    public Map<String, Long> succesfulDealsByMonth() {
        Map<String, Long> result = new LinkedHashMap<>();

        List<Apartment> apartments = apartmentRepo.findAll().stream()
                                                  .filter(app -> app.getStatus().equals(ApartmentStatus.RENTED))
                                                  .sorted(Comparator.comparing(Apartment::getStatusDateChange))
                                                  .collect(Collectors.toList());

        for (Apartment apartment : apartments) {
            String currentMonthName = apartment.getStatusDateChange().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            result.putIfAbsent(currentMonthName, 0L);

            result.put(currentMonthName, result.get(currentMonthName) + 1);
        }

        return result;
    }
}
