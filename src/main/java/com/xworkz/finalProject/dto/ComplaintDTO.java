package com.xworkz.finalProject.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_complaint")
@NamedQuery(name = "findComplaintDTOByUserId",query = "select complaint from ComplaintDTO complaint where complaint.userId = :userId")
@NamedQuery(name = "findComplaintDTOById",query = "select complaint from ComplaintDTO complaint where complaint.id = :id")
@NamedQuery(name = "getAllComplaintDetails",query = "select complaint from ComplaintDTO complaint ORDER BY complaint.createdDate DESC")
@NamedQuery(name = "getAllComplaintDetailsByTypeOrCity",query = "select complaint from ComplaintDTO complaint where " +
        "complaint.complaintType=:complaintType or complaint.city=:city")
@NamedQuery(name = "getAllComplaintDetailsByTypeAndCity",query = "select complaint from ComplaintDTO complaint where " +
        "complaint.complaintType=:complaintType and complaint.city=:city")
@NamedQuery(name = "statusUpdate",query = "update ComplaintDTO set complaintStatus=:status where id=:id")
@NamedQuery(name = "findByComplaintType",query = "select complaint from ComplaintDTO complaint where " +
        "complaint.complaintType=:complaintType")
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
    @Size(min = 3,max = 50,message = "Address should be between 3 and 50 characters")
    private String address;
    @NotNull(message = "description not be null")
    @Size(min = 3,max = 300,message = "Address should be between 3 and 300 characters")
    private String description;
    @Column(name = "department_id")
    private int departmentId;
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
    @Column(name = "complaint_status")
    private String complaintStatus;
    @Column(name = "employee_id")
    private Long employeeId;

}
