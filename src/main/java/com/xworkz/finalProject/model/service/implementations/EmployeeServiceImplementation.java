package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.model.repository.interfaces.EmployeeRepository;
import com.xworkz.finalProject.model.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Optional<EmployeeDTO> findByEmailAndPassword(String email, String password) {
        Optional<EmployeeDTO> optionalEmployeeDTO=this.employeeRepository.findByEmailAndPassword(email, password);
        return optionalEmployeeDTO;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
         boolean update=  this.employeeRepository.updateEmployee(employeeDTO);
         return update;
    }
}
