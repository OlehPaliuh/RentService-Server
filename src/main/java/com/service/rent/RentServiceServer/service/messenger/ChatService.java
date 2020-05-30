package com.service.rent.RentServiceServer.service.messenger;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.messenger.Chat;
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

    public List<Chat> getAllByUsername(String username) {
        Account account = accountService.getAccount(username);
        return account.getChats().stream()
                      .filter(chat -> !chat.getDeleted())
                      .collect(Collectors.toList());
    }

    public Chat getOrCreateChat(String usernameAuthor, String usernameGuest) {
        Chat existingChat = getChatByUsernames(usernameAuthor, usernameGuest);
        if (existingChat != null) {
            return existingChat;
        }

        Chat chat = new Chat();
        chat.setCreatedAt(LocalDateTime.now());
        chat.getAccounts().add(accountService.getByUsername(usernameAuthor));
        chat.getAccounts().add(accountService.getByUsername(usernameGuest));
        chat = chatRepository.save(chat);

        return chat;
    }

    public Chat getChatByUsernames(String username, String withUsername) {

        List<Chat> authorsChats = accountService.getByUsername(username).getChats();
        for (Chat chat : authorsChats) {
            if (chat.getAccounts().stream().anyMatch(account -> account.getUsername().equals(withUsername))) {
                return chat;
            }
        }
        return null;
    }

}
