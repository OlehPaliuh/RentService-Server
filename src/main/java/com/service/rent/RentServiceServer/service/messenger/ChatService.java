package com.service.rent.RentServiceServer.service.messenger;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.messenger.Chat;
import com.service.rent.RentServiceServer.entity.messenger.ChatAssignment;
import com.service.rent.RentServiceServer.repository.messenger.ChatRepo;
import com.service.rent.RentServiceServer.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatService {

    @Autowired
    private ChatRepo chatRepository;
    @Autowired
    private AccountService accountService;

    public List<ChatAssignment> getAllByUsername(String username) {
        Account account = accountService.getAccount(username);
        return account.getChatAssignments().stream()
                      .filter(ass->!ass.getChat().getDeleted())
                      .collect(Collectors.toList());
    }

    public Chat createChat(String usernameAuthor, String usernameGuest) {
        Chat chat = new Chat();
        chat.setCreatedAt(LocalDateTime.now());
        chat.setDefaultChatName("Chat");
        chat = chatRepository.save(chat);
        chat.addChatAssignment(createChatAssignment(chat, usernameAuthor, usernameGuest));
        chat.addChatAssignment(createChatAssignment(chat, usernameGuest, usernameAuthor));
        return chatRepository.save(chat);
    }

    private ChatAssignment createChatAssignment(Chat chat, String username, String chatName) {
        ChatAssignment chatAssignment = new ChatAssignment();
        chatAssignment.setChat(chat);
        chatAssignment.setChatName(chatName);
        chatAssignment.setCreatedAt(LocalDateTime.now());
        chatAssignment.setAccount(accountService.getAccount(username));

        return chatAssignment;
    }

}
