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
@NamedQuery(name = "findByEmployeeEmail",query = "select employee from EmployeeDTO employee where " +
        "employee.email=:email")
@NamedQuery(name = "findByEmployeePhoneNumber",query = "select employee from EmployeeDTO employee where " +
        "employee.employeePhoneNumber=:phoneNumber")
@NamedQuery(name = "findByEmployeeEmailAndPassword",query = "select employee from EmployeeDTO employee where " +
        "employee.email=:email and employee.password=:password")
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
    private String email;
    @Column(name = "employee_password")
    private String password;
    @Column(name = "employee_phone_number")
    private long employeePhoneNumber;
}
