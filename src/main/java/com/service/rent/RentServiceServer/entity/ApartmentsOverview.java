package com.service.rent.RentServiceServer.entity;

import com.service.rent.RentServiceServer.entity.enums.OverviewStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
public class ApartmentsOverview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OverviewStatus status;

    private LocalDateTime dateTime;

    private String accountComment;
    private String ownerComment;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "apartments_id")
    private Apartments apartments;

}
