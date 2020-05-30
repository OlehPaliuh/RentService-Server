package com.service.rent.RentServiceServer.repository.messenger;

import com.service.rent.RentServiceServer.entity.messenger.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {
}
