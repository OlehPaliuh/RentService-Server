package com.service.rent.RentServiceServer.controller.admin;

import com.service.rent.RentServiceServer.controller.admin.dtos.CityApartmentDistributionDto;
import com.service.rent.RentServiceServer.controller.admin.dtos.ComplaintsByMonthDto;
import com.service.rent.RentServiceServer.controller.admin.dtos.MaklerByMonthDto;
import com.service.rent.RentServiceServer.controller.admin.dtos.SuccesfulDealsDto;
import com.service.rent.RentServiceServer.entity.Apartment;
import com.service.rent.RentServiceServer.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/admin/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping(path = "/accounts/online/count")
    public Long countOnlineAccounts() {

        return statisticsService.countAccountsOnline();
    }

    @GetMapping(path = "/apartments/new/count")
    public Long countNewApartments() {
        return statisticsService.countNewApartments();
    }

    @GetMapping(path = "/accounts/new/count")
    public Long countNewUsers() {
        return statisticsService.countNewAccounts();
    }

    @GetMapping(path = "/rieltors/count")
    public Long countRieltorsDetected() {
        return statisticsService.countRieltorsDetected();
    }

    @GetMapping(path = "/cities")
    public CityApartmentDistributionDto cityApartmentsDistribution() {
        Map<String, List<Apartment>> distrib = statisticsService.cityApartmentsDistribution();
        CityApartmentDistributionDto cityApartmentDistributionDto = new CityApartmentDistributionDto();

        for (Map.Entry<String, List<Apartment>> entry : distrib.entrySet()) {
            cityApartmentDistributionDto.getCities().add(entry.getKey());
            cityApartmentDistributionDto.getApartmentCounts().add((long) entry.getValue().size());
        }

        return cityApartmentDistributionDto;
    }

    @GetMapping(path = "/complaints")
    public ComplaintsByMonthDto complaintsByMonth() {
        Map<String, Pair<Long, Long>> complaintsByMonth = statisticsService.complaintsByMonth();
        ComplaintsByMonthDto complaintsByMonthDto = new ComplaintsByMonthDto();

        for (Map.Entry<String, Pair<Long, Long>> entry : complaintsByMonth.entrySet()) {
            complaintsByMonthDto.getMonths().add(entry.getKey());
            complaintsByMonthDto.getComplaintsSeverityIsMakler().add(entry.getValue().getFirst());
            complaintsByMonthDto.getComplainsSeverityPossibleMakler().add(entry.getValue().getSecond());
        }

        return complaintsByMonthDto;
    }

    @GetMapping(path = "/maklers")
    public MaklerByMonthDto maklersByMonth() {
        Map<String, Pair<Long, Long>> maklersByMonth = statisticsService.maklersByMonth();
        MaklerByMonthDto maklerByMonthDto = new MaklerByMonthDto();

        for (Map.Entry<String, Pair<Long, Long>> entry : maklersByMonth.entrySet()) {
            maklerByMonthDto.getMonths().add(entry.getKey());
            maklerByMonthDto.getMaklers().add(entry.getValue().getFirst());
            maklerByMonthDto.getPossiblyMaklers().add(entry.getValue().getSecond());
        }

        return maklerByMonthDto;
    }

    @GetMapping(path = "/successful-deals")
    public SuccesfulDealsDto successfulDeals() {
        Map<String, Long> successfulDealsByMonth = statisticsService.succesfulDealsByMonth();
        SuccesfulDealsDto succesfulDealsDto = new SuccesfulDealsDto();

        for (Map.Entry<String, Long> entry : successfulDealsByMonth.entrySet()) {
            succesfulDealsDto.getMonths().add(entry.getKey());
            succesfulDealsDto.getSuccessfulDeal().add(entry.getValue());
        }

        return succesfulDealsDto;
    }

}
