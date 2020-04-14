package com.service.rent.RentServiceServer.entity;

import com.service.rent.RentServiceServer.entity.enums.ApartmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@ToString
public class Apartments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Double area;

    private String address;

    private String tags;

    @Enumerated(EnumType.STRING)
    private ApartmentStatus status;

    @OneToMany(mappedBy = "apartments")
    private List<ApartmentsOverview> overviews;

    @Min(1)
    private Integer numberOfRooms;

    // etc.

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
}
