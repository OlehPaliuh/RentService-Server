package com.service.rent.RentServiceServer.repository.messenger;

import com.service.rent.RentServiceServer.entity.messenger.ChatAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatAssignmentRepo extends JpaRepository<ChatAssignment, Long> {
    List<ChatAssignment> findAllByAccount_Username(String username);
}
