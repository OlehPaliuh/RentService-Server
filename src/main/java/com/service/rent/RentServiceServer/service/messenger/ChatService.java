package com.service.rent.RentServiceServer.service.messenger;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.messenger.Chat;
import com.service.rent.RentServiceServer.entity.messenger.ChatAssignment;
import com.service.rent.RentServiceServer.repository.messenger.ChatAssignmentRepo;
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
    @Autowired
    private ChatAssignmentRepo chatAssignmentRepo;

    public List<ChatAssignment> getAllByUsername(String username) {
        Account account = accountService.getAccount(username);
        return account.getChatAssignments().stream()
                      .filter(ass -> !ass.getChat().getDeleted())
                      .collect(Collectors.toList());
    }

    public ChatAssignment createChat(String usernameAuthor, String usernameGuest) {
        Chat chat = new Chat();
        chat.setCreatedAt(LocalDateTime.now());
        chat.setDefaultChatName("Chat");
        chat = chatRepository.save(chat);

        ChatAssignment authorAssignment = createChatAssignment(chat, usernameAuthor, usernameGuest);
        chat.addChatAssignment(authorAssignment);
        chat.addChatAssignment(createChatAssignment(chat, usernameGuest, usernameAuthor));

        chatRepository.save(chat);

        return authorAssignment;
    }

        public Long getChatIdByUsernames(String username, String withUsername) {
        List<ChatAssignment> chatAssignments = chatAssignmentRepo.findAllByAccount_Username(username);
        List<ChatAssignment> chatAssignmentsWithUsername = chatAssignmentRepo.findAllByAccount_Username(username);

        Long chatId = null;

        for (ChatAssignment chatAssignment : chatAssignments) {
            for (ChatAssignment chatAssignmentWithUsername : chatAssignmentsWithUsername) {
                if (chatAssignment.getChat().getId().equals(chatAssignmentWithUsername.getChat().getId())) {
                    chatId = chatAssignment.getChat().getId();
                }
            }
        }
        return chatId;
    }

    private ChatAssignment createChatAssignment(Chat chat, String username, String chatName) {
        ChatAssignment chatAssignment = new ChatAssignment();
        Account account = accountService.getAccount(username);
        chatAssignment.setChat(chat);
        chatAssignment.setChatName(account.getFirstName() + ' ' + account.getLastName());
        chatAssignment.setCreatedAt(LocalDateTime.now());
        chatAssignment.setAccount(account);

        return chatAssignmentRepo.save(chatAssignment);
    }

}
