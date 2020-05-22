package com.service.rent.RentServiceServer.entity.messenger;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
@ToString
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

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<ChatMessage> messages = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<ChatAssignment> chatAssignments = new ArrayList<>();

    public void addChatAssignment(ChatAssignment chatAssignment) {
        this.getChatAssignments().add(chatAssignment);
    }
}