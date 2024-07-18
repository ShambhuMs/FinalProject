package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.defaultValue.DefaultValues;
import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.dto.PasswordResetDTO;
import com.xworkz.finalProject.model.service.interfaces.ProfileService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
@Slf4j
public class SignInController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public SignInController() {
        log.info("created no-arg constructor in SignInController...");
    }

    @PostMapping("/signIn")
    public String checkSignIn(@RequestParam String email, @RequestParam String password, Model model,
                              HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        Optional<SignupDTO> signupDTOOptional= this.signUpService.findByemail(email);
        if(password==null || password.isEmpty()){
            model.addAttribute("msg","Enter email or password");
            return  "SignIn";
        }
             if (signupDTOOptional.isPresent()) {
                 if (passwordEncoder.matches(signupDTOOptional.get().getPassword(),password) ||
                         passwordEncoder.matches(signupDTOOptional.get().getUserPassword(),password)) {
                     model.addAttribute("dto", signupDTOOptional.get());
                     model.addAttribute("readOnly", "disable");
                     HttpSession httpSession = request.getSession();
                     List<ProfileDTO> profileDTO = profileService.findDatasById(signupDTOOptional.get().getId());
                     httpSession.setAttribute("dto", signupDTOOptional.get());
                     if (!profileDTO.isEmpty()) {
                         profileDTO.forEach(data -> {
                             if (data.getStatus() == "Active" || data.getStatus().equals("Active")) {
                                 String ref = "/profile/" + data.getImageName();
                                 httpSession.setAttribute("profileDTO", ref);
                             }
                         });
                     }
                    /* else {
                         httpSession.removeAttribute("profileDTO");
                     }*/
                     int count = signupDTOOptional.get().getLogin_count();
                     signupDTOOptional.get().setLock_account(DefaultValues.ZERO.getIntValue());
                     if (count == DefaultValues.ZERO.getIntValue()) {
                         signupDTOOptional.get().setLogin_count(DefaultValues.ONE.getIntValue());
                         this.signUpService.update(signupDTOOptional.get());
                         return "PasswordReset";
                     } else {
                         int num = count + 1;
                         signupDTOOptional.get().setLogin_count(num);
                         this.signUpService.update(signupDTOOptional.get());
                         /*model.addAttribute("dto",signupDTOOptional);*/
                         return "UserHomePage";
                     }
                 }else {
                     model.addAttribute("msg","Enter valid email or password..");
                       int lockAccount=signupDTOOptional.get().getLock_account();
                  if (lockAccount<DefaultValues.LOCK_ACCOUNT.getIntValue()){
                          int lockAc= lockAccount+1;
                          signupDTOOptional.get().setLock_account(lockAc);
                          this.signUpService.update(signupDTOOptional.get());
                          int chance=3-lockAc;
                              model.addAttribute("msg","You have only "+chance+" attempts"+
                                      "\"Enter Valid Email or Password..");
                          return "SignIn";
                  }else {
                      signupDTOOptional.get().setPassword(null);
                      signupDTOOptional.get().setUserPassword(null);
                      signupDTOOptional.get().setUpdatedDate(LocalDateTime.now());
                      this.signUpService.update(signupDTOOptional.get());
                      model.addAttribute("msg","Your account is locked...please reset password");
                      return "FindByEmail";
                  }
                 }
            }
            else  {
                 model.addAttribute("msg","Enter valid email or password..");
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

    @PostMapping("/resetPasswordAnyTime")
    public String resetUserPassword2(@Valid PasswordResetDTO passwordResetDTO, Model model){
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
