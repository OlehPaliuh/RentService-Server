package com.service.rent.RentServiceServer.controller.messenger;

import com.service.rent.RentServiceServer.controller.messenger.dtos.ChatDto;
import com.service.rent.RentServiceServer.controller.messenger.mappers.ChatDtoMapper;
import com.service.rent.RentServiceServer.entity.messenger.Chat;
import com.service.rent.RentServiceServer.entity.messenger.ChatAssignment;
import com.service.rent.RentServiceServer.service.messenger.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/messenger")
public class ChatController {
    private ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{username}/chats")
    public List<ChatDto> getChatsByUser(@PathVariable String username) {

        return chatService.getAllByUsername(username).stream()
                          .map(ChatDtoMapper::toDto)
                          .collect(Collectors.toList());
    }

    /*
     * @GetMapping("/chat/{id}") public ChatDetailsDTO getChatById(@PathVariable Long id , @RequestParam("userId") Long
     * userId ) {
     * 
     *//*
        * if (userId == null) { userId = (long) 1; // TODO later I will take this data from token }
        *//*
           * return chatService.get(id); }
           */

}
