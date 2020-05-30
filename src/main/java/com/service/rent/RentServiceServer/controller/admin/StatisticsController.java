package com.service.rent.RentServiceServer.controller.admin;

import com.service.rent.RentServiceServer.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
