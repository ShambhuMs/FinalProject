package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;

import java.util.Optional;

public interface DepartmentAdminService {
  default   Optional<DepartmentAdminDTO> findByAdminEmailAndPassword(String email, String password){
     return Optional.empty();
  }
}
