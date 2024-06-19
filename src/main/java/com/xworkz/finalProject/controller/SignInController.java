package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.dto.PasswordResetDTO;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
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
    public String checkSignIn(@RequestParam String email, @RequestParam String password, Model model,
                              HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
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
                    model.addAttribute("dto",signupDTOOptional);
                   HttpSession httpSession= request.getSession();
                   httpSession.setAttribute("email",email);
                    return "UserHomePage";
                }
            }
            else  {
                Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByemail(email);
                  int lockAccount=optionalSignupDTO.get().getLock_account();
                  if (lockAccount<2){
                      if (optionalSignupDTO.isPresent()){
                          int lockAc= lockAccount+1;
                          optionalSignupDTO.get().setLock_account(lockAc);
                          this.signUpService.update(optionalSignupDTO.get());
                          int chance=3-lockAc;
                              model.addAttribute("msg","You have only "+chance+" attempts"+
                                      "\"Enter Valid Email or Password..");
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
    public String resetUserPassword(@Valid PasswordResetDTO passwordResetDTO, Model model){
        System.out.println("newPassword : "+ passwordResetDTO.getPassword());
        System.out.println("confirmNewPassword : "+ passwordResetDTO.getConfirmNewPassword());
        Optional<SignupDTO> optionalSignupDTO=  this.signUpService.findByEmailAndPassword(passwordResetDTO.getEmail(),
                passwordResetDTO.getPassword());

        if (passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmNewPassword())){
            optionalSignupDTO.get().setUserPassword(passwordResetDTO.getNewPassword());
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
    @GetMapping("/findByEmail")
    public  String  findByEmail(@RequestParam String email,Model model){
        Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByemail(email);
        if (optionalSignupDTO.isPresent()){
            model.addAttribute("mail","Enter password sent by email..");
            model.addAttribute("dto", optionalSignupDTO.get());
            model.addAttribute("readOnly","disable");
            return "EditProfile";
        }else {
            model.addAttribute("msg","Enter valid email..");
            return "UserHomePage";
        }

    }
    @PostMapping("/resetPasswordAnyTime")
    public String resetUserPassword2(@Valid PasswordResetDTO passwordResetDTO, Model model){
        System.out.println("newPassword : "+ passwordResetDTO.getPassword());
        System.out.println("confirmNewPassword : "+ passwordResetDTO.getConfirmNewPassword());
        Optional<SignupDTO> optionalSignupDTO=  this.signUpService.findByEmailAndPassword(passwordResetDTO.getEmail(),
                passwordResetDTO.getPassword());

        if (passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmNewPassword())){
            optionalSignupDTO.get().setUserPassword(passwordResetDTO.getConfirmNewPassword());
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
