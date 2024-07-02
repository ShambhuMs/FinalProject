package com.xworkz.finalProject.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "department")
@NamedQuery(name = "findByDepartmentType",query = "select department from DepartmentDTO department where department_type=:departmentType")
public class DepartmentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int department_id;
    private String department_name;
    private String department_type;
    private String department_area;
    private String department_address;
    private LocalDateTime created_at;
    private String created_by;
    private LocalDateTime modified_at;
    private String modified_by;
}
