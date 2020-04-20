//package com.service.rent.RentServiceServer.entity.chat;
//
//import lombok.Data;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Data
//@Entity
////TODO look
//public class Message {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String content;
//
//    @CreationTimestamp
//    private LocalDateTime sendTime;
//
//    @ManyToOne
//    private Account sender;
//
//    @ManyToOne
//    private Chat chat;
//
//    private LocalDateTime dataSeen;
//
//
//    public void setChat(Chat chat) {
//        this.chat = chat;
//    }
//}