package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/departmentAdmin")
public class DepartmentAdminController {
    @Autowired
    private DepartmentAdminService departmentAdminService;

    @GetMapping("/adminLogin")
    private String checkForm(Model model){
       model.addAttribute("departmentAdmin",true);
       return "AdminSignIn";
    }
    @PostMapping("/departmentAdmin")
    public String departmentLogin(@Valid DepartmentAdminDTO departmentAdminDTO, Model model){
        Optional<DepartmentAdminDTO> departmentAdminResult = this.departmentAdminService.findByAdminEmailAndPassword(departmentAdminDTO.getDepartmenAdmintEmail(), departmentAdminDTO.getDepartmenAdmintpassword());
        if (departmentAdminResult.isPresent()){
            model.addAttribute("success","Welcome to Department Admin Home");
            return "AdminHomePage";
        }else {
            model.addAttribute("msg","Enter valid email and password");
            model.addAttribute("dto", departmentAdminResult);
            return "AdminSignIn";
        }
    }
}
