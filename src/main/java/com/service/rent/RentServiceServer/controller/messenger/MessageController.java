package com.service.rent.RentServiceServer.controller.messenger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rent.RentServiceServer.entity.messenger.ChatMessage;
import com.service.rent.RentServiceServer.service.messenger.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messenger/chats/")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{chatId}/messages")
    @JsonIgnore
    public List<ChatMessage> getCurrentMessages(@PathVariable Long chatId, @RequestParam("pageNumber") int pageNumber) {
        List<ChatMessage> list = messageService.getNext30Messages(chatId, pageNumber);

        return messageService.getNext30Messages(chatId, pageNumber);
    }

}
