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
@NamedQuery(name = "findByDepartmentAdminEmailAndPassword",query = "select admin from DepartmentAdminDTO admin where " +
        "admin.email=:email and admin.password=:password")
public class DepartmentAdminDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_admin_id")
    private long departmentAdminId;
    @Column(name = "dep_admin_email")
    private String email;
    @Column(name = "dep_admin_password")
    private String password;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by")
    private String updatedBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    private LocalDateTime updatedDate;

}
