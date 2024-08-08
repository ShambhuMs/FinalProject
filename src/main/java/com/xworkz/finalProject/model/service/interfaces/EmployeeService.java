package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.dto.SignupDTO;

import java.util.Optional;

public interface EmployeeService {
    default Optional<EmployeeDTO> findByEmailAndPassword(String email, String password){
        return Optional.empty();
    }
    boolean updateEmployee(EmployeeDTO employeeDTO);
    boolean updateOtp(int complaintId);
}
