package com.service.rent.RentServiceServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rent.RentServiceServer.entity.enums.ApartmentStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@ToString
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Double area;

    private String address;

    private String tags;

    @Enumerated(EnumType.STRING)
    private ApartmentStatus status;

    @ToString.Exclude
    @OneToMany(mappedBy = "apartment")
    private List<ApartmentsOverview> overviews;

    @Min(1)
    @Max(25)
    private Integer numberOfRooms;

    // etc.

    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account owner;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ToString.Exclude
    @OneToMany(mappedBy = "apartment")
    private List<Favourite> favouriteList;
}
