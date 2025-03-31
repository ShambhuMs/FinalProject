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
import java.time.LocalTime;
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
    public String checkSignIn(@RequestParam("email") Optional<String> email,
                              @RequestParam("password") Optional<String> rawPassword,
                              Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Validate input
        if (!email.isPresent() || !rawPassword.isPresent()) {
            model.addAttribute("msg", "Enter email or password");
            return "SignIn";
        }
        // Find user by email
        Optional<SignupDTO> signupDTOOptional = signUpService.findByEmail(email.get());
        if (!signupDTOOptional.isPresent()) {
            model.addAttribute("msg", "Enter valid email or password..");
            return "SignIn";
        }
        SignupDTO signupDTO = signupDTOOptional.get();
        try {
            // Check password
            boolean passwordMatches = passwordEncoder.matches(rawPassword.get(), signupDTO.getPassword())
                    || passwordEncoder.matches(rawPassword.get(), signupDTO.getUserPassword());
            if ((passwordMatches && !signupDTO.getExpireTime().plusMinutes(15).isBefore(LocalTime.now())) ||
                    (passwordMatches && signupDTO.getExpireTime().equals(LocalTime.of(0,0,0)))) {
                model.addAttribute("dto", signupDTO);
                model.addAttribute("readOnly", "disable");

                HttpSession session = request.getSession();
                session.setAttribute("dto", signupDTO);

                List<ProfileDTO> profileDTOs = profileService.findDatasById(signupDTO.getId());
                if (!profileDTOs.isEmpty()) {
                    profileDTOs.forEach(data -> {
                        if ("Active".equals(data.getStatus())) {
                            String ref = "/profile/" + data.getImageName();
                            session.setAttribute("profileDTO", ref);
                        }
                    });
                }
                int loginCount = signupDTO.getLogin_count();
                signupDTO.setLock_account(0);
                if (loginCount == 0) {
                    signupDTO.setLogin_count(1);
                    signUpService.update(signupDTO);
                    return "PasswordReset";
                } else {
                    signupDTO.setLogin_count(loginCount + 1);
                    signUpService.update(signupDTO);
                    return "UserHomePage";
                }
            } else {
                handleInvalidPassword(model, signupDTO);
                return "SignIn";
            }
        } catch (IllegalArgumentException e) {
            handleInvalidPassword(model, signupDTO);
            return "SignIn";
        }
    }
    private String handleInvalidPassword(Model model, SignupDTO signupDTO) {
        int lockAccount = signupDTO.getLock_account();
        if (lockAccount < DefaultValues.LOCK_ACCOUNT.getIntValue()) {
            signupDTO.setLock_account(lockAccount + 1);
            signUpService.update(signupDTO);
            int remainingAttempts = 3 - signupDTO.getLock_account();
            model.addAttribute("msg", "You have only " + remainingAttempts + " attempts left. " +
                    "Enter valid email or password.");
            return "SignIn";
        } else {
            signupDTO.setPassword(null);
            signupDTO.setUpdatedDate(LocalDateTime.now());
            signUpService.update(signupDTO);
            model.addAttribute("msg", "Your account is locked...please reset password");
            return "FindByEmail";
        }
    }
    @PostMapping("/resetPassword")
    public String resetUserPassword(@Valid PasswordResetDTO passwordResetDTO, Model model){
        Optional<SignupDTO> optionalSignupDTO=  this.signUpService.findByEmail(passwordResetDTO.getEmail());
        if (passwordEncoder.matches(passwordResetDTO.getPassword(),optionalSignupDTO.get().getPassword())){
            if (!optionalSignupDTO.get().getExpireTime().plusMinutes(15).isBefore(LocalTime.now())){
                if (passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmNewPassword())){
                    String userPassword=passwordEncoder.encode(passwordResetDTO.getNewPassword());
                    optionalSignupDTO.get().setUserPassword(userPassword);
                   // optionalSignupDTO.get().setExpireTime(LocalTime.of(0,0,0));
                    optionalSignupDTO.get().setLock_account(0);
                    boolean updateValue=this.signUpService.update(optionalSignupDTO.get()); //update password 
                    if (updateValue){
                        model.addAttribute("successMsg","Password Reset Success.. Login with new password");
                        return "SignIn";
                    }
                } else {
                    model.addAttribute("errorMsg","NewPassword and Confirm Password should be same");
                }
            }else {
                model.addAttribute("errorMsg","Your Password Expired, Please request a new Password");
            }
        }else {
            model.addAttribute("errorMsg","Enter valid password");
        }
        return "PasswordReset";
    }
    @GetMapping("/fetchByEmail")
    public  String  getDataByEmail(@RequestParam String email,Model model){
       Optional<SignupDTO> optionalSignupDTO= this.signUpService.findByEmailForReset(email);
       if (optionalSignupDTO.isPresent()){
           model.addAttribute("msg","Enter password sent by email..");
           model.addAttribute("dto", optionalSignupDTO.get());
           model.addAttribute("readOnly","disable");
           return "PasswordReset";
       }else {
           model.addAttribute("msg","Enter valid email..");
           return "FindByEmail";
       }

   }

    @PostMapping("/resetPasswordAnyTime")
    public String resetUserPassword2(@Valid PasswordResetDTO passwordResetDTO, Model model,HttpSession session){
        SignupDTO signupDTO=(SignupDTO) session.getAttribute("dto");
        Optional<SignupDTO> optionalSignupDTO=  this.signUpService.findByEmail(signupDTO.getEmail());
        if (!passwordEncoder.matches(passwordResetDTO.getPassword(),optionalSignupDTO.get().getUserPassword())){
            model.addAttribute("errorMsg","Enter valid old password");
            return "ResetPasswordAnyTime";
        }
        if (passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmNewPassword())){
            String userPassword=passwordEncoder.encode(passwordResetDTO.getNewPassword());
            optionalSignupDTO.get().setUserPassword(userPassword);
            boolean updateValue=this.signUpService.update(optionalSignupDTO.get());
            if (updateValue){
                log.info("data saved for email: " + optionalSignupDTO.get().getEmail());
                model.addAttribute("msg","Password Reset Success..");
                return "ResetPasswordAnyTime";
            }else {
                log.info("Data not saved!!!!!!!!");
            }
        } else {
            model.addAttribute("errorMsg","NewPassword and Confirm Password should be same");
            return "ResetPasswordAnyTime";
        }
        return "ResetPasswordAnyTime";
    }
}
