package com.service.rent.RentServiceServer.controller.messenger;

import com.service.rent.RentServiceServer.controller.messenger.dtos.ChatDto;
import com.service.rent.RentServiceServer.controller.messenger.mappers.ChatDtoMapper;
import com.service.rent.RentServiceServer.service.messenger.ChatService;
import com.service.rent.RentServiceServer.service.messenger.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/messenger")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageService messageService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{username}/chats")
    public List<ChatDto> getChatsByUser(@PathVariable String username) {

        List<ChatDto> dtos = chatService.getAllByUsername(username).stream()
                                        .map(chat -> ChatDtoMapper.toDto(chat, username))
                                        .collect(Collectors.toList());

        for (ChatDto dto : dtos) {
            dto.setMessages(messageService.getNext30Messages(dto.getId(), 0));
        }

        return dtos;
    }


    @GetMapping("/{username}/chats/getOrCreate")
    public ChatDto getOrcreateChat(@PathVariable String username, @RequestParam String withUsername) {

        return ChatDtoMapper.toDto(chatService.getOrCreateChat(username, withUsername), username);
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
