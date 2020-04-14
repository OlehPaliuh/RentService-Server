package com.service.rent.RentServiceServer.entity.chat;


import com.service.rent.RentServiceServer.entity.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@EqualsAndHashCode
//TODO look
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@NonNull*/
    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages;

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Account receiver;
}