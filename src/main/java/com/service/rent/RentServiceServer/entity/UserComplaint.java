package com.service.rent.RentServiceServer.entity;

import com.service.rent.RentServiceServer.entity.enums.ComplaintSeverity;
import com.service.rent.RentServiceServer.entity.enums.ComplaintType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@ToString
@Data
@NoArgsConstructor
public class UserComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String complaintContent;

    private String complaintTitle;

    @Enumerated(EnumType.STRING)
    private ComplaintType complaintType;

    @Enumerated(EnumType.STRING)
    private ComplaintSeverity complaintSeverity;

    @ManyToOne
    @NotNull
    private Account toAccount;

    @ManyToOne
    @NotNull
    private Account fromAccount;

    /**
     * Deleted complaint
     */
    @NotNull
    private Boolean isDeleted = false;
    @CreationTimestamp
    private LocalDateTime deletedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
