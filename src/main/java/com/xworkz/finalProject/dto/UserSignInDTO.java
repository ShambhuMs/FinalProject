package com.xworkz.finalProject.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInDTO {
    private String email;
    private String password;
    private String newPassword;
    private String confirmNewPassword;

}
