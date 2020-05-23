package com.service.rent.RentServiceServer.repository;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.UserComplaint;
import com.service.rent.RentServiceServer.entity.enums.ComplaintSeverity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserComplaintRepo extends JpaRepository<UserComplaint, Long> {
    List<UserComplaint> getAllByComplaintSeverityAndToAccountAndIsDeletedIsFalse(ComplaintSeverity complaintSeverity, Account account);
}
