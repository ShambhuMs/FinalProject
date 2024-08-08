package com.xworkz.finalProject.controller.Ajax;


import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
@RestController
@RequestMapping("/")
public class AjaxValidationForUserController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private DepartmentAdminService departmentAdminService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/validateEmail")
    public String validateUserEmail(@RequestParam String email) {
        Optional<SignupDTO> optional = signUpService.findByEmail(email);
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

    @GetMapping("/validateDepartmentAdminEmail")
    public String validateDepartmentAdminEmail(@RequestParam String email) {
        Optional<DepartmentAdminDTO> optional = this.adminService.fetchByDepAdminEmail(email);
        return optional.isPresent() ? "Email is already Exist" : "";
    }

    @GetMapping("/validateDepartmentAdminPhone")
    public String validateDepartmentAdminPhone(@RequestParam long phoneNumber) {
        Optional<DepartmentAdminDTO> existingPhone = this.adminService.fetchByDepAdminPhone(phoneNumber);
        return existingPhone.isPresent() ? "Phone is already Exist": ""; // Return true if phone number already exists
    }

    @PostMapping("employee/sendOtp")
    public String sendOtp(@RequestParam("id") int complaintId, RedirectAttributes redirectAttributes) {
        try {
            employeeService.updateOtp(complaintId);
            redirectAttributes.addFlashAttribute("message", "OTP sent successfully to the user.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to send OTP: " + e.getMessage());
        }
        return "redirect:/employee/viewAssignedComplaints";
    }
    @GetMapping("/validateOtp")
    public String validateOtp(@RequestParam("otp") String otp, @RequestParam("complaintId") int complaintId) {
        Optional<ComplaintDTO> complaint = complaintService.findById(complaintId);
        if (complaint != null) {
          Optional<SignupDTO>  signupDTO= signUpService.findById(complaint.get().getUserId());
            if (signupDTO.isPresent() && signupDTO.get().getPassword().equals(otp)){
                return "";
            }
        }
        return "Invalid OTP. Please try again.";
    }
}

