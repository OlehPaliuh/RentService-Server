package com.service.rent.RentServiceServer.entity.chat;

import com.service.rent.RentServiceServer.entity.Account;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
//TODO look
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private LocalDateTime dateSeen;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Chat chat;

}