package com.service.rent.RentServiceServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rent.RentServiceServer.entity.enums.SubscriptionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "subscribe_account_id")
    private Account account;

    @OneToOne(mappedBy = "subscription")
    private ApartmentsSearchParameters searchParameters;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    private boolean isActive;
}
