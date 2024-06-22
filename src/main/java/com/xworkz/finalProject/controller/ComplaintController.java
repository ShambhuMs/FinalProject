package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/raiseComplaint")
    public String saveComplaintDetails(@Valid ComplaintDTO complaintDTO,
                                       HttpSession session, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError));
            model.addAttribute("errorMessage",bindingResult.getAllErrors());
        }  else {
            SignupDTO signupDTO1=(SignupDTO)  session.getAttribute("dto");
            System.err.println(signupDTO1);
            complaintDTO.setCreatedBy(signupDTO1.getFirstName()+" "+signupDTO1.getLastName());
            complaintDTO.setCreatedDate(LocalDateTime.now());
            complaintDTO.setUserId(signupDTO1.getId());
            boolean saved=this.complaintService.saveComplaintDetails(complaintDTO);
            if (saved){
                model.addAttribute("successMessage","Your complaint Submitted...");
            }else {
                model.addAttribute("failedMessage","Enter valid details..");
            }
        }
        return "RaiseComplaint";
    }

}
