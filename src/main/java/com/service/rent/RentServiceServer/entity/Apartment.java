package com.service.rent.RentServiceServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rent.RentServiceServer.entity.enums.ApartmentStatus;
import com.service.rent.RentServiceServer.entity.enums.BuildingType;
import com.service.rent.RentServiceServer.entity.enums.WallType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Indexed
@Data
@ToString
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long id;

    @Field
    private String title;

    @Field
    @Column(length = 2048)
    private String description;

    @Min(0)
    private Double price;

    @Min(1)
    private Double livingArea;

    @Min(1)
    @Max(45)
    private Integer floor;

    @Min(1)
    @Max(15000)
    private Double totalArea;

    private boolean hasPhotos;

    private boolean allowPets;

    private BuildingType buildingType;

    private WallType wallType;

    private String tags;

    @Enumerated(EnumType.STRING)
    private ApartmentStatus status;

    private LocalDateTime statusDateChange;

    @ToString.Exclude
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<ApartmentOverview> overviews;

    @Min(1)
    @Max(25)
    private Integer numberOfRooms;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime editDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account owner;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @IndexedEmbedded
    private Location location;

    @ToString.Exclude
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favourite> favouriteList;

    @ToString.Exclude
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    @ElementCollection
    private List<String> imageLinks;

    private Boolean isLocked = false;
}
