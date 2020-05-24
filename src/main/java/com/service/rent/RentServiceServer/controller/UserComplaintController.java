package com.service.rent.RentServiceServer.controller;

import com.service.rent.RentServiceServer.entity.UserComplaint;
import com.service.rent.RentServiceServer.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/complaint/")
public class UserComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping(path = "{accountFromId}/{accountToId}/create")
    public UserComplaint createComplaint(@PathVariable Long accountFromId, @PathVariable Long accountToId, @RequestBody UserComplaint complaint) {
        return complaintService.createUserComplaints(accountFromId, accountToId, complaint);
    }
}
