package com.service.rent.RentServiceServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rent.RentServiceServer.entity.messenger.ChatAssignment;
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

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Apartment> ownApartmentList;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<ApartmentOverview> apartmentOverviews;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Favourite> favouriteList;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ChatAssignment> chatAssignments;

    private Double maklerProbabilityScore = 0d;

    private Integer owningApartmentsCount;

    private Boolean documentsVerified = false;

    private LocalDateTime lastLoginTime;

    private Boolean isOnline = false;
}
