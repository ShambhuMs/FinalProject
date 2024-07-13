package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.dto.PasswordResetDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/adminLogin")
    private String checkForm(Model model){
        model.addAttribute("employee",true);
        return "AdminSignIn";
    }
    @GetMapping("/resetPasswordAnyTime")
    private String checkResetPassword(Model model){
        model.addAttribute("employee",true);
        return "ResetPasswordAnyTime";
    }
    @PostMapping("/adminLogin")
    public String employeeLogin(@Valid EmployeeDTO employeeDTO, Model model, HttpSession session){
        Optional<EmployeeDTO> optionalEmployeeDTO=this.employeeService.findByEmailAndPassword
                (employeeDTO.getEmail(),employeeDTO.getPassword());
        if (optionalEmployeeDTO.isPresent()){
            model.addAttribute("success","Welcome to Department Admin Home");
            session.setAttribute("dto",optionalEmployeeDTO.get());
            model.addAttribute("employee",true);
            return "EmployeeHome";
        }else {
            model.addAttribute("msg","Enter valid email and password");
            model.addAttribute("dto", employeeDTO);
            model.addAttribute("employee",true);
            return "AdminSignIn";
        }
    }

    @PostMapping(value = "/resetPasswordAnyTime")
    public String resetEmployeePassword(@Valid PasswordResetDTO passwordResetDTO, Model model,HttpSession session){
        EmployeeDTO employeeDTO=(EmployeeDTO) session.getAttribute("dto");
        Optional<EmployeeDTO> optionalEmployeeDTO=  this.employeeService.findByEmailAndPassword(employeeDTO.getEmail(),
                passwordResetDTO.getPassword());
        if (passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmNewPassword())){
            optionalEmployeeDTO.get().setPassword(passwordResetDTO.getNewPassword());
            boolean updateValue=this.employeeService.updateEmployee(optionalEmployeeDTO.get());
            if (updateValue){
                model.addAttribute("msg","Password Reset Success..");
            }else {
                model.addAttribute("errorMsg","Password update failed..\n\n Enter valid password");
            }
        } else {
            model.addAttribute("errorMsg","NewPassword and Confirm Password should be same");
        }
        model.addAttribute("employee",true);
        return "ResetPasswordAnyTime";
    }

    @PostMapping("/viewAssignedComplaints")
    public String fetchAssignedComplaints(@Valid ComplaintDTO complaintDTO,Model model){
        List<ComplaintDTO> list= this.adminService.fetchAllCompliant();
        if (!list.isEmpty()){
            model.addAttribute("employeeDTO",list);
        }else {
            model.addAttribute("msg","No Records found");
        }
        return "EmployeeHome";
    }
}
