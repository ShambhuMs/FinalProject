package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.EmployeeDTO;

import java.util.Optional;

public interface EmployeeRepository {
    default Optional<EmployeeDTO> findByEmailAndPassword(String email,String password){
        return Optional.empty();
    }
    boolean updateEmployee(EmployeeDTO employeeDTO);
}
