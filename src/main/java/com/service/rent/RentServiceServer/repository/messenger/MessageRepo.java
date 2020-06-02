package com.service.rent.RentServiceServer.repository.messenger;

import com.service.rent.RentServiceServer.entity.messenger.Chat;
import com.service.rent.RentServiceServer.entity.messenger.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<ChatMessage, Long> {
    public ChatMessage findTopByChatOrderByCreatedAtDesc(Chat chat);

    public List<ChatMessage> findAllByChatOrderByCreatedAtDesc(Chat chat, Pageable pageable);

    public List<ChatMessage> findAllByChatOrderByCreatedAtDesc(Chat chat);
}
