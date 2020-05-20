package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.ApartmentOverview;
import com.service.rent.RentServiceServer.entity.dto.ApartmentOverviewRequestDto;
import com.service.rent.RentServiceServer.entity.enums.OverviewStatus;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.ApartmentRepo;
import com.service.rent.RentServiceServer.repository.OverviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OverviewService {

    @Autowired
    private OverviewRepo overviewRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ApartmentRepo apartmentRepo;

    public ApartmentOverview createOverviewRequest(ApartmentOverviewRequestDto apartmentOverviewRequestDto) {
        ApartmentOverview apartmentOverview = new ApartmentOverview();
        apartmentOverview.setDateTime(apartmentOverviewRequestDto.getDateTime());
        apartmentOverview.setRequesterComment(apartmentOverviewRequestDto.getComment());
        apartmentOverview.setStatus(OverviewStatus.REQUESTED);
        apartmentOverview.setAccount(accountRepo.getAccountById(apartmentOverviewRequestDto.getAccountId()));
        apartmentOverview.setApartment(apartmentRepo.getApartmentById(apartmentOverviewRequestDto.getApartmentId()));
        return overviewRepo.save(apartmentOverview);
    }

    public List<ApartmentOverview> getOverviewByApartmentId(Long apartmentId) {
        return overviewRepo.getAllApartmentOverviewByApartmentId(apartmentId).stream()
                .filter(overview -> overview.getStatus().equals(OverviewStatus.REQUESTED) || overview.getStatus().equals(OverviewStatus.AGREED))
                .filter(overview -> overview.getDateTime().compareTo(LocalDateTime.now()) > 0)
                .collect(Collectors.toList());
    }

    public ApartmentOverview approveOverviewRequest(Long overviewId) {
        ApartmentOverview apartmentOverview = overviewRepo.getApartmentOverviewById(overviewId);
        apartmentOverview.setStatus(OverviewStatus.AGREED);
        return overviewRepo.save(apartmentOverview);
    }

    public ApartmentOverview rejectOverviewRequest(Long overviewId) {
        ApartmentOverview apartmentOverview = overviewRepo.getApartmentOverviewById(overviewId);
        apartmentOverview.setStatus(OverviewStatus.REJECTED);
        return overviewRepo.save(apartmentOverview);
    }
}
