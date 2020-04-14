package com.service.rent.RentServiceServer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "owner")
    private List<Apartments> apartmentsList;

    @OneToMany(mappedBy = "account")
    private List<ApartmentsOverview> apartmentsOverviews;

    @OneToMany(mappedBy = "account")
    private List<Subscription> subscriptions;
}
