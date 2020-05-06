package com.service.rent.RentServiceServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    /**
     * District of the city
     */
    private String sublocality;

    /**
     * Street name
     */
    private String route;

    /**
     * Building number
     */
    private String streetNumber;

    private String country;

    private String political;

    /**
     * Region
     */
    private String administrativeArea;

    /**
     * Zip code
     */
    private String postalCode;

    private double latitude;

    private double longitude;

    private String fullAddress;

    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "location")
    private Apartment apartment;

}
