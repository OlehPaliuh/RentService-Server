package com.service.rent.RentServiceServer.entity.messenger;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
//TODO look
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private Boolean deleted = false;

    private String defaultChatName;

    private ChatType chatType = ChatType.SIMPLE_CHAT;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<ChatMessage> messages = new ArrayList<>();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<ChatAssignment> chatAssignments = new ArrayList<>();

    public void addChatAssignment(ChatAssignment chatAssignment) {
        this.getChatAssignments().add(chatAssignment);
    }
}