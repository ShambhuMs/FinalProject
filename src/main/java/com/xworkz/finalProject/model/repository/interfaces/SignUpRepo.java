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
    default Optional<SignupDTO> findByEamilAndPassword(String email,String password){
        return Optional.empty();
    }
    default Optional<SignupDTO> findByEamilAndUsePassword(String email,String password){
        return Optional.empty();
    }
    boolean update(SignupDTO signupDTO);
}
