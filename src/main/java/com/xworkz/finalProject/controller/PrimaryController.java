package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import com.xworkz.finalProject.randomPassword.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class PrimaryController {
    @Autowired
    private SignUpService signUpService;
    public PrimaryController() {
        System.out.println("Created no arg constructor in PrimaryController...");
    }

    @PostMapping("/sign")
    public String validateAndSave(@Valid SignupDTO signupDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError));
            model.addAttribute("errorMessage",bindingResult.getAllErrors());
        }else {
           boolean result= this.signUpService.save(signupDTO);
           if (result){
               model.addAttribute("msg",signupDTO.getFirstName()+", Your application submitted..");
               /*model.addAttribute("password","Your password is : "+signupDTO.getPassword());*/
               System.out.println("dto in controller"+signupDTO.getPassword());
           }
            else {
                System.out.println("dto in controller"+signupDTO);
               String email=signupDTO.getEmail();
               Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByemail(email);
               long phoneNumber= signupDTO.getPhoneNumber();
               Optional<SignupDTO> optionalPhoneNumber=this.signUpService.findByPhoneNumber(phoneNumber);
               if (optionalSignupDTO.isPresent()){
                   model.addAttribute("failedEmailMsg","Email already exist...");
               }else if (optionalPhoneNumber.isPresent()){
                   model.addAttribute("failedPhNoMsg","PhoneNumber already exist...");
               }
           }
        }
        return "Signup";
    }



}
