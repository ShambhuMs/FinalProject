package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.dto.PasswordResetDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.EmployeeService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private SignUpService signUpService;

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

    @PostMapping("/resetPasswordAnyTime")
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

    @GetMapping("/viewAssignedComplaints")
    public String fetchAssignedComplaints(Model model,HttpSession session){
        List<ComplaintDTO> complaintDTOListlist= this.adminService.fetchAllCompliant();
        EmployeeDTO employeeDTO=(EmployeeDTO) session.getAttribute("dto");
        if (!complaintDTOListlist.isEmpty()){
            List<ComplaintDTO> assignedComplaints=new ArrayList<>();
            complaintDTOListlist.forEach(complaintDTO -> {
                if (complaintDTO.getEmployeeId() != null) {
                    if (complaintDTO.getEmployeeId() == employeeDTO.getEmployeeId()) {
                        assignedComplaints.add(complaintDTO);
                    }
                }
            });
            if (!assignedComplaints.isEmpty()){
                model.addAttribute("empDTO",assignedComplaints);
            }else {
                model.addAttribute("msg","No complaints have been assigned..");
            }
        }else {
            model.addAttribute("msg","No complaints have been assigned..");
        }
        return "EmployeeHome";
    }

    @GetMapping("/updateComplaintStatusByEmployee")
    public String updateComplaintStatusByEmployee(@RequestParam(defaultValue = " ") String password,
                                                   ComplaintDTO complaintDTO, Model model,
                                                  HttpSession session, RedirectAttributes redirectAttributes){
        model.addAttribute("employee",true);
        if (complaintDTO==null){
            redirectAttributes.addFlashAttribute("msg","complaint dto is empty");
            return "redirect:/employee/viewAssignedComplaints";
        }
       Optional<ComplaintDTO> optionalComplaintDTO=  this.complaintService.findById(complaintDTO.getId());
       if (optionalComplaintDTO.isPresent()){
         Optional<SignupDTO> signupDTO = this.signUpService.findById(optionalComplaintDTO.get().getUserId());
           if (complaintDTO.getComplaintStatus().equals("Resolved") && signupDTO.isPresent()){
               if (!signupDTO.get().getPassword().equals(password) && password!=null){
                   model.addAttribute("otpError", "Invalid OTP. Please try again.");
                   return "redirect:/employee/viewAssignedComplaints";
               }
           }
           optionalComplaintDTO.get().setComplaintStatus(complaintDTO.getComplaintStatus());
           optionalComplaintDTO.get().setComment(complaintDTO.getComment());
           EmployeeDTO employeeDTO=(EmployeeDTO) session.getAttribute("dto");
           optionalComplaintDTO.get().setUpdatedBy(employeeDTO.getEmployeeName());
           optionalComplaintDTO.get().setUpdatedDate(LocalDateTime.now());
            boolean update= this.complaintService.updateComplaint(optionalComplaintDTO.get());
          if (update){
              redirectAttributes.addFlashAttribute("successMsg","Updated successfully");
          }
          else {
              redirectAttributes.addFlashAttribute("msg","Update failed");
          }
       }
        return "redirect:/employee/viewAssignedComplaints";
    }
}
