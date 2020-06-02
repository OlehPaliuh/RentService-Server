package com.service.rent.RentServiceServer.controller.messenger.mappers;

import com.service.rent.RentServiceServer.controller.messenger.dtos.ChatDto;
import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.entity.messenger.Chat;

public class ChatDtoMapper {
    public static ChatDto toDto(Chat chat, String currentUsername) {
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        Account accountWithWhoYouAreCommunicating =
                chat.getAccounts().stream()
                    .filter(acc -> !acc.getUsername().equals(currentUsername))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Something goes wrong during chat mapping"));

        chatDto.setChatName(accountWithWhoYouAreCommunicating.getFirstName() + ' ' +
                            accountWithWhoYouAreCommunicating.getLastName());

        chatDto.setCreatedAt(chat.getCreatedAt());
        chatDto.setDeleted(chat.getDeleted());

        chatDto.setUsername(accountWithWhoYouAreCommunicating.getUsername());

        return chatDto;

    }

}
