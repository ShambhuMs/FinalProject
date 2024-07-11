package com.xworkz.finalProject.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_table")
public class EmployeeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long employeeId;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "department_id")
    private int departmentId;
    @Column(name = "employee_email")
    private String employeeEmail;
    @Column(name = "employee_password")
    private String employeePassword;
    @Column(name = "employee_phone_number")
    private long employeePhoneNumber;
}
