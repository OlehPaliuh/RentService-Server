package com.service.rent.RentServiceServer.controller.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComplaintsByMonthDto {
    List<String> months = new ArrayList<>();
    List<Long> complainsSeverityPossibleMakler = new ArrayList<>();
    List<Long> complaintsSeverityIsMakler = new ArrayList<>();
}
