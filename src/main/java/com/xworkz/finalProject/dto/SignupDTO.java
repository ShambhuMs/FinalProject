package com.xworkz.finalProject.dto;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "signup")
@NamedQuery(name = "findByEmail",query = "select signup from SignupDTO signup where signup.email=:email")
@NamedQuery(name = "findByPhoneNumber",query = "select signup from SignupDTO signup where signup.phoneNumber=:phoneNumber")
public class SignupDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "firstName not be null")
    @Size(min = 3,max = 15,message = "name should be greater than 3 and less than 15")
    @Column(name = "first_name")
    private String firstName;
    @NotNull(message = "firstName not be null")
    @Size(min = 3,max = 15,message = "name should be greater than 3 and less than 15")
    @Column(name = "last_name")
    private String lastName;
    @Min(1111111111L)
    @Max(value = 9999999999L,message = "phoneNumber must have 10 digits")
    @Column(name = "phone_number")
    private long phoneNumber;
    @NotNull(message = "email not be null")
    @Email(message = "Enter valid email" )
    private String email;
    @Getter
    private String password;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by")
    private String updatedBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    private LocalDateTime updatedDate;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
