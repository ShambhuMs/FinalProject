package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.SignupDTO;

import java.util.Optional;

public interface SignUpService {
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
    public void invalidateExpiredPasswords();
}
