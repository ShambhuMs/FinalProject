package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;
import com.xworkz.finalProject.dto.EmployeeDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface DepartmentAdminRepository {
    default   Optional<DepartmentAdminDTO> findByAdminEmailAndPassword(String email, String password){
        return Optional.empty();
    }
    boolean addEmployee(EmployeeDTO employeeDTO);
    default List<DepartmentDTO> fetchAllDepartments(){
     return Collections.emptyList();
  }
    default   Optional<EmployeeDTO> findByEmployeeEmail(String email){
        return Optional.empty();
    }
    default   Optional<EmployeeDTO> findByEmployeePhoneNumber(long phoneNumber){
        return Optional.empty();
    }
}
