package com.service.rent.RentServiceServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rent.RentServiceServer.entity.enums.OverviewStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
public class ApartmentsOverview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OverviewStatus status;

    private LocalDateTime dateTime;

    private String accountComment;
    private String ownerComment;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

}
