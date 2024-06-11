package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.SignupDTO;

public interface SignUpRepo {
    boolean save(SignupDTO signupDTO);
}
