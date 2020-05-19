package com.service.rent.RentServiceServer.entity.messenger;

import com.service.rent.RentServiceServer.entity.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode

public class ChatAssignment implements Serializable {

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Chat chat;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    private String chatName;
}
