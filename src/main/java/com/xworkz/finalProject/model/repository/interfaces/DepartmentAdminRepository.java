package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;

import java.util.Optional;

public interface DepartmentAdminRepository {
    default   Optional<DepartmentAdminDTO> findByAdminEmailAndPassword(String email, String password){
        return Optional.empty();
    }
}
