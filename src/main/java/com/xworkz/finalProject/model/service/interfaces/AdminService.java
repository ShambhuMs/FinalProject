package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;
import com.xworkz.finalProject.dto.SignupDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    default Optional<AdminDTO> findByEmailAndPassword(String email, String password){
        return Optional.empty();
    }
    List<SignupDTO> fetchAllClientRecords();
    List<ComplaintDTO> fetchAllCompliant();
    List<ComplaintDTO> fetchByComplaintTypeOrCity( String complaintType,String city);
    List<ComplaintDTO> getAllComplaintDetailsByTypeAndCity(String complaintType,String city);
    default Optional<DepartmentDTO> findByDepartmentType(String departmentType){
        return Optional.empty();
    }
    boolean updateStatus(long id,String status);
}
