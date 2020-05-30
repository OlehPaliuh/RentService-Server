package com.service.rent.RentServiceServer.controller.messenger.mappers;

import com.service.rent.RentServiceServer.controller.messenger.dtos.ChatDto;
import com.service.rent.RentServiceServer.entity.messenger.Chat;

public class ChatDtoMapper {
    public static ChatDto toDto(Chat chat, String currentUsername) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        String usernameOfGuyWithWhoYouAreCommunicating =
                chat.getAccounts().stream()
                    .filter(acc -> !acc.getUsername().equals(currentUsername))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Something goes wrong during chat mapping"))
                    .getUsername();

        chatDto.setChatName(usernameOfGuyWithWhoYouAreCommunicating);

        chatDto.setCreatedAt(chat.getCreatedAt());
        chatDto.setDeleted(chat.getDeleted());

        chatDto.setUsername(usernameOfGuyWithWhoYouAreCommunicating);

        return chatDto;

    }

}
