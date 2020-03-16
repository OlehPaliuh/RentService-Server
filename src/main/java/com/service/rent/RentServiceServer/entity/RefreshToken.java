//package com.service.rent.RentServiceServer.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Getter
//@Setter
//public class RefreshToken {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "refresh_token_id")
//    private Long id;
//
//    private String token;
//
//    private Date expirationDate;
//
//    @OneToOne
//    private User userRole;
//}
