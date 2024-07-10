package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;
import com.xworkz.finalProject.model.repository.interfaces.DepartmentAdminRepository;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentAdminServiceImplementation implements DepartmentAdminService {
    @Autowired
    private DepartmentAdminRepository departmentAdminRepository;
    @Override
    public Optional<DepartmentAdminDTO> findByAdminEmailAndPassword(String email, String password) {
        Optional<DepartmentAdminDTO> optionalDepartmentDTO= this.departmentAdminRepository.findByAdminEmailAndPassword(email, password);
        return Optional.ofNullable(optionalDepartmentDTO.get());
    }
}
