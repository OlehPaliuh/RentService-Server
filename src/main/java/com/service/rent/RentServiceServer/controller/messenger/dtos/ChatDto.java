package com.service.rent.RentServiceServer.controller.messenger.dtos;

import com.service.rent.RentServiceServer.entity.messenger.ChatType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
@EqualsAndHashCode
public class ChatDto {
    private Long id;

    private LocalDateTime createdAt;

    private boolean deleted;

    private String chatName;

    private ChatType chatType = ChatType.SIMPLE_CHAT;

    /**
     * Guy with whom you are communicating
     */
    private String username;
}
