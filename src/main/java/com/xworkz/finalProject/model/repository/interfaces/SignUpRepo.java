package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.SignupDTO;

import java.util.Optional;

public interface SignUpRepo {
    boolean save(SignupDTO signupDTO);
    default Optional<SignupDTO> findByEmail(String email){
       return Optional.empty();
    }
    default Optional<SignupDTO> findByPhoneNumber(long phoneNumber){
        return Optional.empty();
    }
    default Optional<SignupDTO> findByEmailAndPassword(String email, String password){
        return Optional.empty();
    }

    boolean update(SignupDTO signupDTO);
    default Optional<SignupDTO> findByEmailForReset(String email){
        return Optional.empty();
    }

}
