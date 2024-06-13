package com.xworkz.finalProject.dto;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.*;
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
@NamedQuery(name = "findByPhoneNumber",query = "select signup from SignupDTO signup where" +
        " signup.phoneNumber=:phoneNumber")
@NamedQuery(name = "findByEmailAndPassword",query = "select signup from SignupDTO signup where " +
        "signup.email=:email and signup.password=:password")
@NamedQuery(name = "findByEmailAndUserPassword",query = "select signup from SignupDTO signup where " +
        "signup.email=:email and signup.userPassword=:password")
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
    private String password;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by")
    private String updatedBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    private LocalDateTime updatedDate;
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9]).{6,}$\n",message = "password should be digit or alphabet and special characters")
    @Column(name = "user_password")
    private String userPassword;
    private int login_count;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getLogin_count() {
        return login_count;
    }

    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }
}
