package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.implementations.SignupRepository;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignupServiceImplementation implements SignUpService {
    @Autowired
    private SignupRepository signupRepository;
    @Override
    public boolean save(SignupDTO signupDTO) {
        System.out.println("Running save method in Service....");

        boolean saveResult= this.signupRepository.save(signupDTO);
        System.out.println("dto in service"+signupDTO.getPassword());
        return saveResult;
    }

    @Override
    public Optional<SignupDTO> findByemail(String email) {
        Optional<SignupDTO>  optionalSignupDTO= this.signupRepository.findByEmail(email);
        if (optionalSignupDTO.isPresent()){
            System.out.println("data found for email : "+email);
            return optionalSignupDTO;
        }else {
            System.out.println("data not found for email : "+email);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SignupDTO> findByPhoneNumber(long phoneNumber) {
        Optional<SignupDTO>  optionalSignupDTO= this.signupRepository.findByPhoneNumber(phoneNumber);
        if (optionalSignupDTO.isPresent()){
            System.out.println("data found for phoneNumber : "+phoneNumber);
            return optionalSignupDTO;
        }else {
            System.out.println("data not found for phoneNumber : "+phoneNumber);
            return Optional.empty();
        }
    }
}
