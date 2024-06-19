package com.xworkz.finalProject.dto;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetDTO {
    @Email(message = "Enter valid email")
    private String email;
    @NotNull(message = "Password not be null")
    private String password;
    @Size(min = 6,max = 18,message = "Password should be min 6 and max 18 characters")
    private String newPassword;
    private String confirmNewPassword;

}
