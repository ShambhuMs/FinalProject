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
@Table(name = "department_admin")
public class DepartmentAdminDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_admin_id")
    private long departmentAdminId;
    @Column(name = "  dep_admin_email")
    private String departmenAdmintEmail;
    @Column(name = "dep_admin_password")
    private String departmenAdmintpassword;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by")
    private String updatedBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    private LocalDateTime updatedDate;
}
