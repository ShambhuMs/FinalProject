package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.dto.UserSignInDTO;
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
    public String checkSignIn(@RequestParam String email,@RequestParam String password,  Model model)  {
        Optional<SignupDTO> signupDTOOptional= this.signUpService.findByEmailAndPassword(email, password);
        int count=0;
             if (signupDTOOptional.isPresent()) {
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
    public String resetUserPassword(@Valid UserSignInDTO userSignInDTO, Model model){
        System.out.println("newPassword : "+userSignInDTO.getPassword());
        System.out.println("confirmNewPassword : "+userSignInDTO.getConfirmNewPassword());
        Optional<SignupDTO> optionalSignupDTO=  this.signUpService.findByEmailAndPassword(userSignInDTO.getEmail(),
                userSignInDTO.getPassword());

        if (userSignInDTO.getNewPassword().equals(userSignInDTO.getConfirmNewPassword())){
            optionalSignupDTO.get().setUserPassword(userSignInDTO.getNewPassword());
            System.out.println("optionalSignupDTO in controller...: "+optionalSignupDTO);
              boolean updateValue=this.signUpService.update(optionalSignupDTO.get());
              if (updateValue){
                  System.out.println("data saved for email: " + optionalSignupDTO.get().getEmail());
                  model.addAttribute("msg","Password Reset Success..");
                  return "PasswordReset";
              }else {
                  System.out.println("Data not saved!!!!!!!!");
              }
        } else {
            model.addAttribute("errorMsg","NewPassword and Confirm Password should be same");
            return "PasswordReset";
        }
        return "PasswordReset";
    }


}
