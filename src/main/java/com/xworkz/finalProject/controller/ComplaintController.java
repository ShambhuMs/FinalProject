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
import java.util.Optional;

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
            model.addAttribute("complaintDto",complaintDTO);
        }  else {
            SignupDTO signupDTO1=(SignupDTO)  session.getAttribute("dto");
            System.err.println(signupDTO1);
            complaintDTO.setCreatedBy(signupDTO1.getFirstName()+" "+signupDTO1.getLastName());
            complaintDTO.setCreatedDate(LocalDateTime.now());
            complaintDTO.setUserId(signupDTO1.getId());
            complaintDTO.setComplaintStatus("unResolved");
            boolean saved=this.complaintService.saveComplaintDetails(complaintDTO);
            if (saved){
                model.addAttribute("successMessage","Your complaint Submitted...");
            }else {
                model.addAttribute("failedMessage","Enter valid details..");
            }
        }
        return "RaiseComplaint";
    }

    @PostMapping("/viewComplaintDetails")
    public String viewAllComplaints(HttpSession session,Model model){
      SignupDTO signupDTO=(SignupDTO)  session.getAttribute("dto");
      Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByemail(signupDTO.getEmail());
      List<ComplaintDTO> complaintDTOList=this.complaintService.findByUserId(optionalSignupDTO.get().getId());
      if (!complaintDTOList.isEmpty()){
          complaintDTOList.forEach(complaintDTO -> {
              if (complaintDTO.getComplaintStatus()=="unResolved"){
                  model.addAttribute("dto",complaintDTOList);
              }else {
                  model.addAttribute("msg","0 Records found");
              }
          });
      }else {
          model.addAttribute("msg","0 Records found");
      }
      return "ViewComplaint";
    }
}
