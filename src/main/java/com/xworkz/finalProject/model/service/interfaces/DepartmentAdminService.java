package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;
import com.xworkz.finalProject.dto.EmployeeDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface DepartmentAdminService {
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
    default List<EmployeeDTO> getEmployeesByDepartmentId(int departmentId){
        return Collections.emptyList();
    }
    default List<ComplaintDTO> fetchByComplaintType(String complaintType){
        return Collections.emptyList();
    }
    default List<ComplaintDTO> getComplaintsByDepartmentId(int departmentId){
        return Collections.emptyList();
    }
    boolean updateDepartmentAdminDTO(DepartmentAdminDTO departmentAdminDTO);
}
