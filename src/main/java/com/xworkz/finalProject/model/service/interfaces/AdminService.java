package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.SignupDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    default Optional<AdminDTO> findByEmailAndPassword(String email, String password){
        return Optional.empty();
    }
    List<SignupDTO> fetchAllClientRecords();
}
