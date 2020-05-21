package com.service.rent.RentServiceServer.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ApartmentsSearchParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private boolean deleted;

    private boolean disabled;

    @OneToOne
    private Subscription subscription;
    private String regions;

    private Integer roomsMin;
    private Integer roomsMax;

    private Integer floorMin;
    private Integer floorMax;

    private Integer priceMin;
    private Integer priceMax;

    private String tags;

    private String searchText;
}
