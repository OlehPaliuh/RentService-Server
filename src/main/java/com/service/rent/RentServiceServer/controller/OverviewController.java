package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.ApartmentOverview;
import com.service.rent.RentServiceServer.entity.dto.ApartmentOverviewRequestDto;
import com.service.rent.RentServiceServer.service.OverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/overview/")
public class OverviewController {

    @Autowired
    private OverviewService overviewService;

    @PostMapping(path="create")
    public @ResponseBody
    ApartmentOverview createApartmentOverview(@RequestBody ApartmentOverviewRequestDto apartmentOverviewRequestDto) {
        return overviewService.createOverviewRequest(apartmentOverviewRequestDto);
    }

    @GetMapping(path="apartment/{id}")
    public @ResponseBody
    List<ApartmentOverview> getOverviewByApartmentId(@PathVariable Long id) {
        return overviewService.getOverviewByApartmentId(id);
    }

    @PutMapping(path="{id}/approve")
    public @ResponseBody
    ApartmentOverview approveOverviewRequest(@PathVariable Long id) {
        return overviewService.approveOverviewRequest(id);
    }

    @PutMapping(path="{id}/reject")
    public @ResponseBody
    ApartmentOverview rejectOverviewRequest(@PathVariable Long id) {
        return overviewService.rejectOverviewRequest(id);
    }
}
