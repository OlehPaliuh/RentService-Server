package com.service.rent.RentServiceServer.entity.messenger;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rent.RentServiceServer.entity.Account;
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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Account> accounts=new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<ChatMessage> messages = new ArrayList<>();

}