package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.implementations.SignupRepository;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import com.xworkz.finalProject.randomPassword.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
