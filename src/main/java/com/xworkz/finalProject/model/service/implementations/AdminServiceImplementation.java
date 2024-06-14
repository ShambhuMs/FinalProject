package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.interfaces.AdminRepository;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Optional<AdminDTO> findByEmailAndPassword(String email, String password) {
      Optional<AdminDTO> loginData= this.adminRepository.findByEmailAndPassword(email,password);
      if (loginData.isPresent()){
          return loginData;
      }else {
          return Optional.empty();
      }

    }

    @Override
    public List<SignupDTO> fetchAllClientRecords() {
        List<SignupDTO> signupDTOS=this.adminRepository.fetchAllClientRecords();
        if (!signupDTOS.isEmpty()){
            return signupDTOS;
        }else {
            return Collections.emptyList();
        }
    }
}
