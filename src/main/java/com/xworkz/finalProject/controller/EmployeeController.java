package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.model.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/adminLogin")
    private String checkForm(Model model){
        model.addAttribute("employee",true);
        return "AdminSignIn";
    }

    @PostMapping("/adminLogin")
    public String employeeLogin(@Valid EmployeeDTO employeeDTO,Model model){
        Optional<EmployeeDTO> optionalEmployeeDTO=this.employeeService.findByEmailAndPassword
                (employeeDTO.getEmail(),employeeDTO.getPassword());
        if (optionalEmployeeDTO.isPresent()){
            model.addAttribute("success","Welcome to Department Admin Home");
            return "EmployeeHome";
        }else {
            model.addAttribute("msg","Enter valid email and password");
            model.addAttribute("dto", employeeDTO);
            model.addAttribute("employee",true);
            return "AdminSignIn";
        }
    }
}
