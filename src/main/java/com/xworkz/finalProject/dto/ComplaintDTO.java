package com.xworkz.finalProject.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_complaint")
public class ComplaintDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private long id;
    @NotNull(message = "complaintType not be null")
    @Column(name = "complaint_type")
    private String complaintType;
    @NotNull(message = "country not be null")
    private String country;
    @NotNull(message = "state not be null")
    private String state;
    @NotNull(message = "city not be null")
    private String city;
    @NotNull(message = "address not be null")
    private String address;
    @NotNull(message = "description not be null")
    private String description;

    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by")
    private String updatedBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    private LocalDateTime updatedDate;
    @Column(name = "user_id")
    private int userId;
}