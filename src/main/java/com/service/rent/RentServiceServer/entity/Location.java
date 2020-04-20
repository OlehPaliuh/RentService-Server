package com.service.rent.RentServiceServer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String country;
    private String address;
    private String buildingNumber;
    private String zipCode;
    private String shototam;//shyryna vysota

    @OneToOne(mappedBy = "location")
    private Apartment apartment;

}
