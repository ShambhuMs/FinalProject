package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.defaultValue.DefaultValues;
import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public String saveComplaintDetails(@Valid ComplaintDTO complaintDTO,@RequestParam String submit,
                                       HttpSession session, BindingResult bindingResult, Model model){
        boolean edit=submit.equalsIgnoreCase("Update");
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError));
            model.addAttribute("errorMessage",bindingResult.getAllErrors());
            model.addAttribute("complaintDto",complaintDTO);
        }  else {
            SignupDTO signupDTO1 = (SignupDTO) session.getAttribute("dto");
            if (!edit) {
                complaintDTO.setCreatedBy(signupDTO1.getFirstName() + " " + signupDTO1.getLastName());
                complaintDTO.setCreatedDate(LocalDateTime.now());
                complaintDTO.setUserId(signupDTO1.getId());
                complaintDTO.setComplaintStatus(DefaultValues.STATUS.getDefaultStatus());
                complaintDTO.setEmployeeId(DefaultValues.ZERO.getLongValue());
                complaintDTO.setAdminRead(false);
                boolean saved = this.complaintService.saveComplaintDetails(complaintDTO);
                if (saved) {
                    model.addAttribute("successMessage", "Your complaint Submitted...");
                } else {
                    model.addAttribute("failedMessage", "Enter valid details..");
                }
            }else {
             Optional<ComplaintDTO> optionalComplaintDTO=  this.complaintService.findById(complaintDTO.getId());
             optionalComplaintDTO.get().setDescription(complaintDTO.getDescription());
             optionalComplaintDTO.get().setUpdatedBy(signupDTO1.getFirstName() + " " + signupDTO1.getLastName());
             optionalComplaintDTO.get().setUpdatedDate(LocalDateTime.now());
              boolean update=this.complaintService.updateComplaint(optionalComplaintDTO.get());
                if (update) {
                    model.addAttribute("successMessage", "Your complaint Updated...");
                } else {
                    model.addAttribute("failedMessage", "Enter valid details..");
                }
            }
        }
        return "RaiseComplaint";
    }

    @GetMapping("/viewComplaints")
    public String viewAllComplaints(Model model,HttpSession session){
        SignupDTO signupDTO=(SignupDTO)  session.getAttribute("dto");
        if (signupDTO!=null){
        Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByEmail(signupDTO.getEmail());
        List<ComplaintDTO> complaintDTOList = this.complaintService.findByUserId(optionalSignupDTO.get().getId());
            if (!complaintDTOList.isEmpty()) {
                model.addAttribute("complaintDto", complaintDTOList);
            } else {
                model.addAttribute("msg", "0 Records found");
            }
        }
        else {
            model.addAttribute("msg", "Your Session Expired, Please re-login");
        }
        return "ViewComplaint";
    }


    @PostMapping("/viewComplaints")
    public String viewComplaintsByStatus(@RequestParam String viewComplaints, HttpSession session,Model model){
      SignupDTO signupDTO=(SignupDTO)  session.getAttribute("dto");
      Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByEmail(signupDTO.getEmail());
      List<ComplaintDTO> complaintDTOList=this.complaintService.findByUserId(optionalSignupDTO.get().getId());
      List<ComplaintDTO> activeComplaints =new ArrayList<>();
      if (viewComplaints.equals(DefaultValues.STATUS.getDefaultStatus())) { //Active complaints
          if (!complaintDTOList.isEmpty()) {
              complaintDTOList.forEach(complaintDTO -> {
                  if (complaintDTO.getComplaintStatus().equals(DefaultValues.STATUS.getDefaultStatus())) {
                      activeComplaints.add(complaintDTO);
                  }
              });
          }
      }else if (viewComplaints.equals("Resolved")) { //Resolved complaints
          if (!complaintDTOList.isEmpty()) {
              complaintDTOList.forEach(complaintDTO -> {
                  if (complaintDTO.getComplaintStatus().equals("Resolved")) {
                      activeComplaints.add(complaintDTO);
                  }
              });
          }
      }else if (viewComplaints.equals("UnResolved")) {// UnResolved  complaints
          if (!complaintDTOList.isEmpty()) {
              complaintDTOList.forEach(complaintDTO -> {
                  if (complaintDTO.getComplaintStatus().equals("UnResolved")) {
                      activeComplaints.add(complaintDTO);
                  }
              });
          }
      }
      if (!activeComplaints.isEmpty()){
              model.addAttribute("complaintDto",activeComplaints);
          }
          else {
              model.addAttribute("msg","0 Records found");
          }
      /*}else {
          model.addAttribute("msg","0 Records found");
      }*/
      return "ViewComplaint";
    }

    @GetMapping("/findByComplaintId")
    public String getById(@RequestParam long id,Model model,@RequestParam String edit){
      Optional<ComplaintDTO>  optionalComplaintDTO= this.complaintService.findById(id);
      if (optionalComplaintDTO.isPresent()){
          if ("edit".equals(edit)){
              model.addAttribute("complaintDto",optionalComplaintDTO.get());
              model.addAttribute("readOnly", "disable");
              model.addAttribute("action","edit");
              return "RaiseComplaint";
          }
      }else {
          model.addAttribute("msg","0 Records found");
      }
    return "ViewComplaint";
    }


}
