package com.xworkz.finalProject.controller.Ajax;


import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/")
public class AjaxValidationForUserController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private DepartmentAdminService departmentAdminService;

    @GetMapping("/validateEmail")
    public String validateUserEmail(@RequestParam String email) {
        Optional<SignupDTO> optional = signUpService.findByemail(email);
        return optional.isPresent() ? "Email is already Exist" : "";
    }

    @GetMapping("/validatePhone")
    public String validateUserPhone(@RequestParam long phoneNumber) {
        Optional<SignupDTO> existingPhone = signUpService.findByPhoneNumber(phoneNumber);
        return existingPhone.isPresent() ? "Phone is already Exist": ""; // Return true if phone number already exists
    }

    @GetMapping("departmentAdmin/validateEmployeeEmail")
    public String validateEmployeeEmail(@RequestParam String email) {
        Optional<EmployeeDTO> optional = this.departmentAdminService.findByEmployeeEmail(email);
        return optional.isPresent() ? "Email is already Exist" : "";
    }

    @GetMapping("departmentAdmin/validateEmployeePhone")
    public String validateEmployeePhone(@RequestParam long phoneNumber) {
        Optional<EmployeeDTO> existingPhone = this.departmentAdminService.findByEmployeePhoneNumber(phoneNumber);
        return existingPhone.isPresent() ? "Phone is already Exist": ""; // Return true if phone number already exists
    }
    
}

