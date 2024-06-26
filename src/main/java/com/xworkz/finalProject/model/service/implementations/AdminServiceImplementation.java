package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.ComplaintDTO;
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

    @Override
    public List<ComplaintDTO> fetchAllCompliant() {
        List<ComplaintDTO> complaintDTOS=this.adminRepository.fetchAllCompliant();
        if (!complaintDTOS.isEmpty()){
            return complaintDTOS;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ComplaintDTO> fetchByComplaintTypeOrCity(String complaintType,String city) {
        List<ComplaintDTO> complaintDTOS=this.adminRepository.fetchByComplaintTypeOrCity(complaintType,city);
        System.err.println(complaintDTOS);
        if (!complaintDTOS.isEmpty()){
            return complaintDTOS;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ComplaintDTO> getAllComplaintDetailsByTypeAndCity(String complaintType,String city) {
        List<ComplaintDTO> complaintDTOS=this.adminRepository.getAllComplaintDetailsByTypeAndCity(complaintType, city);
        if (!complaintDTOS.isEmpty()){
            return complaintDTOS;
        }else {
            return Collections.emptyList();
        }
    }
}
