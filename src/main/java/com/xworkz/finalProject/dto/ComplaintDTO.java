package com.xworkz.finalProject.dto;

import lombok.*;

import javax.persistence.*;
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
    @Column(name = "complaint_type")
    private String complaintType;
    private String country;
    private String state;
    private String city;
    private String address;
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
