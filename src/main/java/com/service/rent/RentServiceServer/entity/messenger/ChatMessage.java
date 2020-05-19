package com.service.rent.RentServiceServer.entity.messenger;

import com.service.rent.RentServiceServer.entity.Account;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    private LocalDateTime dateSeen;

    private boolean deleted;

    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account sender;

    @ManyToOne(fetch = FetchType.EAGER)
    private Chat chat;

}