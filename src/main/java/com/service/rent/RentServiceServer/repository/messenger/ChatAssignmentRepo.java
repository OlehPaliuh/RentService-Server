package com.service.rent.RentServiceServer.repository.messenger;

import com.service.rent.RentServiceServer.entity.messenger.Chat;
import com.service.rent.RentServiceServer.entity.messenger.ChatAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatAssignmentRepo extends JpaRepository<ChatAssignment, Long> {
}
