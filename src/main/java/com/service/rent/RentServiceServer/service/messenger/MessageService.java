package com.service.rent.RentServiceServer.service.messenger;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.messenger.Chat;
import com.service.rent.RentServiceServer.entity.messenger.ChatMessage;
import com.service.rent.RentServiceServer.exception.UserNotFoundException;
import com.service.rent.RentServiceServer.exception.messenger.ChatNotFoundException;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.messenger.ChatRepo;
import com.service.rent.RentServiceServer.repository.messenger.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private AccountRepo accountRepo;


    public long save(ChatMessage message, Long chatId, String senderUsername) {
        Chat chat = chatRepo.findById(chatId)
                            .orElseThrow(() -> new ChatNotFoundException("No chat with id " + chatId));
        Account sender = accountRepo.getAccountByUsername(senderUsername)
                                    .orElseThrow(() -> new UserNotFoundException("No user with username " + senderUsername));
        message.setChat(chat);
        message.setSender(sender);
        return messageRepo.save(message).getId();
    }

    public void delete(ChatMessage message) {
        message.setDeleted(true);
        message.setDeletedAt(LocalDateTime.now());

        messageRepo.save(message);
    }


    public List<ChatMessage> getNext30Messages(long chatId, int pageNumber) { // max number of messages =)
        Chat chat = chatRepo.findById(chatId)
                            .orElseThrow(() -> new ChatNotFoundException("Chat not found with id " + chatId));

        return messageRepo.findAllByChatOrderByCreatedAtDesc(chat, PageRequest.of(pageNumber, 30));
    }

    public List<ChatMessage> getAllMessages(long chatId) { // max number of messages =)
        Chat chat = chatRepo.findById(chatId)
                            .orElseThrow(() -> new ChatNotFoundException("Chat not found with id " + chatId));

        return messageRepo.findAllByChatOrderByCreatedAtDesc(chat);
    }
}
