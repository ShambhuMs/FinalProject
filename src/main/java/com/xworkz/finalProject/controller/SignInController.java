package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.dto.UserSignInDTO;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
             if (signupDTOOptional.isPresent()) {
                model.addAttribute("dto", signupDTOOptional.get());
                model.addAttribute("readOnly", "disable");
              int  count = signupDTOOptional.get().getLogin_count();
              signupDTOOptional.get().setLock_account(0);
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
                Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByemail(email);
                  int lockAccount=optionalSignupDTO.get().getLock_account();
                  if (lockAccount<3){
                      if (optionalSignupDTO.isPresent()){
                          int lockAc= lockAccount+1;
                          optionalSignupDTO.get().setLock_account(lockAc);
                          this.signUpService.update(optionalSignupDTO.get());
                          int chance=3-lockAc;
                              model.addAttribute("msg","You have only "+chance+" attempts"+
                                      "\"In-correct Email or Password..");
                          return "SignIn";
                      }
                  }else {
                      optionalSignupDTO.get().setPassword(null);
                      optionalSignupDTO.get().setUserPassword(null);
                      optionalSignupDTO.get().setUpdatedDate(LocalDateTime.now());
                      this.signUpService.update(optionalSignupDTO.get());
                      model.addAttribute("msg","Your account is locked...please reset password");
                      return "FindByEmail";
                  }
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

   @GetMapping("/fetchByEmail")
    public  String  getDataByEmail(@RequestParam String email,Model model){
       Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByEmailForReset(email);
       if (optionalSignupDTO.isPresent()){
           model.addAttribute("mail","Enter password sent by email..");
           model.addAttribute("dto", optionalSignupDTO.get());
           return "ResetPasswordAnyTime";
       }else {
           model.addAttribute("msg","Enter valid email..");
           return "FindByEmail";
       }

   }
    @PostMapping("/resetPasswordAnyTime")
    public String resetUserPassword2(@Valid UserSignInDTO userSignInDTO, Model model){
        System.out.println("newPassword : "+userSignInDTO.getPassword());
        System.out.println("confirmNewPassword : "+userSignInDTO.getConfirmNewPassword());
        Optional<SignupDTO> optionalSignupDTO=  this.signUpService.findByEmailAndPassword(userSignInDTO.getEmail(),
                userSignInDTO.getPassword());

        if (userSignInDTO.getNewPassword().equals(userSignInDTO.getConfirmNewPassword())){
            optionalSignupDTO.get().setUserPassword(userSignInDTO.getConfirmNewPassword());
            System.out.println("optionalSignupDTO in controller...: "+optionalSignupDTO);
            boolean updateValue=this.signUpService.update(optionalSignupDTO.get());
            if (updateValue){
                System.out.println("data saved for email: " + optionalSignupDTO.get().getEmail());
                model.addAttribute("msg","Password Reset Success..");
                return "ResetPasswordAnyTime";
            }else {
                System.out.println("Data not saved!!!!!!!!");
            }
        } else {
            model.addAttribute("errorMsg","NewPassword and Confirm Password should be same");
            return "ResetPasswordAnyTime";
        }
        return "ResetPasswordAnyTime";
    }
}
