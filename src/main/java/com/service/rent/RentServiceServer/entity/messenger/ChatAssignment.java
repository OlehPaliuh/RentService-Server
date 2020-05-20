package com.service.rent.RentServiceServer.entity.messenger;

import com.service.rent.RentServiceServer.entity.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode
@ToString
public class ChatAssignment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Chat chat;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    private String chatName;
}
