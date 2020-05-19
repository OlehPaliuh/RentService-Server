package com.service.rent.RentServiceServer.controller.messenger.mappers;

import com.service.rent.RentServiceServer.controller.messenger.dtos.ChatDto;
import com.service.rent.RentServiceServer.entity.messenger.ChatAssignment;
import org.springframework.util.StringUtils;

public class ChatDtoMapper {
    public static ChatDto toDto(ChatAssignment chatAssignment) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chatAssignment.getChat().getId());
        chatDto.setChatName(StringUtils.isEmpty(chatAssignment.getChatName()) ?
                                    chatAssignment.getChat().getDefaultChatName() : chatAssignment.getChatName());

        chatDto.setCreatedAt(chatAssignment.getChat().getCreatedAt());
        chatDto.setDeleted(chatAssignment.getChat().getDeleted());
        String usernameOfGuyWithWhoYouAreCommunicating =
                chatAssignment.getChat().getChatAssignments().stream()
                              .filter(chAss -> !chAss.getAccount().getUsername().equals(
                                      chatAssignment.getAccount().getUsername()))
                              .findFirst()
                              .orElseThrow(() -> new RuntimeException("Something goes wrong during chat mapping"))
                              .getAccount().getUsername();
        chatDto.setUsername(usernameOfGuyWithWhoYouAreCommunicating);

        return chatDto;

    }
}
