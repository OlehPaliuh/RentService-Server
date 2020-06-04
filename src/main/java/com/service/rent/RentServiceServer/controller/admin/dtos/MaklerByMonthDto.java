package com.service.rent.RentServiceServer.controller.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaklerByMonthDto {
    List<String> months = new ArrayList<>();
    List<Long> maklers = new ArrayList<>();
    List<Long> possiblyMaklers = new ArrayList<>();
}
