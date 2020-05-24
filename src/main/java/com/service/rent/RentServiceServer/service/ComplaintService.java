package com.service.rent.RentServiceServer.service;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.UserComplaint;
import com.service.rent.RentServiceServer.exception.UserNotFoundException;
import com.service.rent.RentServiceServer.repository.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.service.rent.RentServiceServer.entity.enums.ComplaintType.MAKLER;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;

    @Autowired
    private AccountService accountService;

    public UserComplaint createUserComplaints(Long accountFromId, Long accountToId, UserComplaint userComplaint) {
        UserComplaint newComplaint = new UserComplaint();

        Account accountFrom = accountService.getById(accountFromId);
        Account accountTo = accountService.getById(accountToId);

        if(accountFrom == null || accountTo == null )  {
            throw new UserNotFoundException("User not found");
        }

        newComplaint.setFromAccount(accountFrom);
        newComplaint.setToAccount(accountTo);
        newComplaint.setComplaintTitle(userComplaint.getComplaintTitle());
        newComplaint.setComplaintContent(userComplaint.getComplaintContent());
        newComplaint.setComplaintType(MAKLER);
        newComplaint.setComplaintSeverity(userComplaint.getComplaintSeverity());

        return complaintRepo.save(newComplaint);
    }
}
