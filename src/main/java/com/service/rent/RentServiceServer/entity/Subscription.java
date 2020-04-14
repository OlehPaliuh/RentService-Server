package com.service.rent.RentServiceServer.entity;

import com.service.rent.RentServiceServer.entity.enums.SubscriptionType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @Embedded
    private ApartmentsSearchParameters searchParameters;

    private SubscriptionType subscriptionType;

    private boolean active;
}
