package com.service.rent.RentServiceServer.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Account is locked
     */
    private boolean isLocked;

    /**
     * Account is created but not activated OR account is deleted
     */
    private boolean isDisabled;

    @Column(name = "refresh_token_key", nullable = false)
    private String refreshTokenKey;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "account_role_id")
    private Role role;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    @Length(max = 13)
    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Apartment> ownApartmentList;

    @OneToMany(mappedBy = "account")
    private List<ApartmentsOverview> apartmentOverviews;

    @OneToMany(mappedBy = "account")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "account")
    private List<Favourite> favouriteList;


}
