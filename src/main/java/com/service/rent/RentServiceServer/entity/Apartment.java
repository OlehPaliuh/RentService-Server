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
    private String description;

    private Double price;

    private Double livingArea;

    private Integer floor;

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
    @OneToMany(mappedBy = "apartment")
    private List<ApartmentOverview> overviews;

    @Min(1)
    @Max(25)
    private Integer numberOfRooms;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime editDate;

    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account owner;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "location_id")
    @IndexedEmbedded
    private Location location;

    @ToString.Exclude
    @OneToMany(mappedBy = "apartment")
    @JsonIgnore
    private List<Favourite> favouriteList;

    @OneToMany(mappedBy = "apartment")
    private List<Comment> comments;

    @ElementCollection
    private List<String> imageLinks;
}
