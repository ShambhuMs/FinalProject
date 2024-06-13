package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class SignInController {
    @Autowired
    private SignUpService signUpService;
    public SignInController() {
        System.out.println("created no-arg constructor in SignInController...");
    }

    @PostMapping("/signIn")
    public String checkSignIn(@RequestParam String email,@RequestParam String password,  Model model) throws NoSuchElementException {
        Optional<SignupDTO> signupDTOOptional= this.signUpService.findByEamilAndPassword(email, password);
        Optional<SignupDTO> signupDTOOptional1= this.signUpService.findByEamilAndUsePassword(email, password);
        int count=0;
        int count1=0;
        try {
            System.out.println("count from controller :" + count + " dto : " + signupDTOOptional);
            count1 = signupDTOOptional1.get().getLogin_count();
            System.out.println("count from controller :"+count1+" dto : "+signupDTOOptional1);
        }catch (Exception e){
            System.err.println(e);
        }

            if(signupDTOOptional1.isPresent()) {
                   model.addAttribute("dto", signupDTOOptional1.get());
                   model.addAttribute("readOnly", "disable");
                if (count1==0 ) {
                   signupDTOOptional1.get().setLogin_count(1);
                   this.signUpService.update(signupDTOOptional1.get());
                   return "PasswordReset";
               }else {
                   int num=count1+1;
                   signupDTOOptional1.get().setLogin_count(num);
                   this.signUpService.update(signupDTOOptional1.get());
                   return "index";
               }
           } else if (signupDTOOptional.isPresent()) {
                model.addAttribute("dto", signupDTOOptional.get());
                model.addAttribute("readOnly", "disable");
                count = signupDTOOptional.get().getLogin_count();
                if (count==0) {
                signupDTOOptional.get().setLogin_count(1);
                this.signUpService.update(signupDTOOptional.get());
                return "PasswordReset";
            }else {
                int num=count+1;
                signupDTOOptional.get().setLogin_count(num);
                this.signUpService.update(signupDTOOptional.get());
                return "index";
            }
        }
           else  {
                   model.addAttribute("msg", "In-correct Email or Password..");
                   return "SignIn";
           }
    }


    @PostMapping("/resetPassword")
    public String resetUserPassword(@RequestParam String newPassword,@RequestParam String password,
       @RequestParam String email, @RequestParam String confirmNewPassword,Model model){
        System.out.println("newPassword : "+newPassword);
        System.out.println("confirmNewPassword : "+confirmNewPassword);
        Optional<SignupDTO> optionalSignupDTO=  this.signUpService.findByEamilAndPassword(email, password);

       int count= optionalSignupDTO.get().getLogin_count();
        if (newPassword.equals(confirmNewPassword)){
            optionalSignupDTO.get().setUserPassword(confirmNewPassword);
            System.out.println("optionalSignupDTO in controller...: "+optionalSignupDTO);
              boolean updateValue=this.signUpService.update(optionalSignupDTO.get());
              if (updateValue){
                  System.out.println("data saved for email: " + optionalSignupDTO.get().getEmail());
                  model.addAttribute("success", optionalSignupDTO.get().getFirstName()
                          + ", Welcome to new Era....");
                  return "index";
              }else {
                  System.out.println("Data not saved!!!!!!!!");
              }
        } else {
            model.addAttribute("msg","NewPassword and Confirm Password should be same");
            return "PasswordReset";
        }
        return "PasswordReset";
    }


}
