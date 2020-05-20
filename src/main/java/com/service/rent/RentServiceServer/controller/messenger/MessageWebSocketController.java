package com.service.rent.RentServiceServer.controller.messenger;

import com.service.rent.RentServiceServer.entity.messenger.ChatMessage;
import com.service.rent.RentServiceServer.service.messenger.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Map;

import static java.lang.String.format;

@Controller
public class MessageWebSocketController {

    private SimpMessageSendingOperations messagingTemplate;

    private MessageService messageService;

    @Autowired
    public MessageWebSocketController(SimpMessageSendingOperations messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/{chatId}/message")
    public void sendMessageString(@DestinationVariable Long chatId, @RequestBody Map<String, Object> jsonBody) {

        ChatMessage message = new ChatMessage();

        message.setCreatedAt(LocalDateTime.now());
        message.setContent((String) jsonBody.get("messageContent"));
        String senderUsername = (String) jsonBody.get("sender");
        message.setId(messageService.save(message, chatId, senderUsername));

        messagingTemplate.convertAndSend(format("/chat/%s/messages", chatId), message);
    }
}
