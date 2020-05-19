package com.service.rent.RentServiceServer.service.messenger;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.messenger.Chat;
import com.service.rent.RentServiceServer.entity.messenger.ChatAssignment;
import com.service.rent.RentServiceServer.exception.UserNotFoundException;
import com.service.rent.RentServiceServer.repository.AccountRepo;
import com.service.rent.RentServiceServer.repository.messenger.ChatRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChatService {

    @Autowired
    private ChatRepo chatRepository;
    @Autowired
    private AccountRepo accountRepository;

    public List<ChatAssignment> getAllByUsername(String username) {
        Account account = accountRepository.getAccountByUsername(username).orElseThrow(
                () -> new UserNotFoundException("No user with username - " + username));
        return account.getChatAssignments();
    }

}
