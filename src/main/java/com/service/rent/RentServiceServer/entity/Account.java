package com.service.rent.RentServiceServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rent.RentServiceServer.entity.messenger.Chat;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    @NotNull
    private boolean isLocked = false;
    private String lockReason;
    private LocalDateTime lockTimestamp;
    private LocalDateTime unlockTimestamp;

    /**
     * Account is created but not activated OR account is deleted
     */
    @NotNull
    private boolean isDisabled = false;

    @Column(name = "refresh_token_key", nullable = false)
    private String refreshTokenKey;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "account_role_id")
    private Role role;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private String activationCode;

    private String avatarPath;

    @CreationTimestamp
    private LocalDateTime registeredAt;
    @UpdateTimestamp
    private LocalDateTime editedAt;

    @Length(max = 13)
    private String phoneNumber;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Apartment> ownApartmentList;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ApartmentOverview> apartmentOverviews;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favourite> favouriteList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "accounts")
    @JsonIgnore
    private List<Chat> chats;

    private Double maklerProbabilityScore = 0d;

    private Integer owningApartmentsCount;

    private Boolean documentsVerified = false;

    private LocalDateTime lastLoginTime;

    private Boolean isOnline = false;
}
