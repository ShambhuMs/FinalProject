package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    default Optional<AdminDTO> findByEmailAndPassword(String email, String password){
        return Optional.empty();
    }

    List<SignupDTO> fetchAllClientRecords();
    List<ComplaintDTO> fetchAllCompliant();
    List<ComplaintDTO> fetchByComplaintTypeOrCity(String complaintType,String city);
    List<ComplaintDTO> getAllComplaintDetailsByTypeAndCity(String complaintType,String city);
}
