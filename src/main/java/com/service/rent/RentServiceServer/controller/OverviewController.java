package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.ApartmentOverview;
import com.service.rent.RentServiceServer.entity.dto.ApartmentOverviewRequestDto;
import com.service.rent.RentServiceServer.entity.dto.ApartmentOverviewResponseDto;
import com.service.rent.RentServiceServer.service.OverviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/overview/")
public class OverviewController {

    @Autowired
    private OverviewService overviewService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path="create")
    public @ResponseBody
    ApartmentOverview createApartmentOverview(@RequestBody ApartmentOverviewRequestDto apartmentOverviewRequestDto) {
        return overviewService.createOverviewRequest(apartmentOverviewRequestDto);
    }

    @GetMapping(path="apartment/{id}")
    public @ResponseBody
    List<ApartmentOverviewResponseDto> getOverviewByApartmentId(@PathVariable Long id) {
        return overviewService.getOverviewByApartmentId(id).stream().map(obj -> modelMapper.map(obj, ApartmentOverviewResponseDto.class)).collect(Collectors.toList());
    }

    @GetMapping(path="account/{id}")
    public @ResponseBody
    List<ApartmentOverviewResponseDto> getOverviewByAccountId(@PathVariable Long id) {
        return overviewService.getOverviewByAccountId(id).stream().map(obj -> modelMapper.map(obj, ApartmentOverviewResponseDto.class)).collect(Collectors.toList());
    }

    @PostMapping(path="{id}/approve")
    public @ResponseBody
    ApartmentOverview approveOverviewRequest(@PathVariable Long id) {
        return overviewService.approveOverviewRequest(id);
    }

    @PostMapping(path="{id}/reject")
    public @ResponseBody
    ApartmentOverview rejectOverviewRequest(@PathVariable Long id) {
        return overviewService.rejectOverviewRequest(id);
    }
}
