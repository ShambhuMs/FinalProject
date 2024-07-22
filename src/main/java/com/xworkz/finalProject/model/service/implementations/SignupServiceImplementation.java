package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.defaultValue.DefaultValues;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.implementations.SignupRepository;
import com.xworkz.finalProject.model.repository.interfaces.AdminRepository;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import com.xworkz.finalProject.randomPassword.RandomPasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SignupServiceImplementation implements SignUpService {
    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;

    public void sendEmail(SignupDTO signupDTO,String password) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(signupDTO.getEmail());
        simpleMailMessage.setSubject("One time Password");
        simpleMailMessage.setText("Dear " + signupDTO.getFirstName() + " " + signupDTO.getLastName() +
                " your signup success Please SignIn through this password :" + password + "\n\n" +
                "Thanks and Regards,\n" + " " + "X-workz Team");
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public boolean save(SignupDTO signupDTO) {
        log.info("Running save method in Service....");
        String fullName = signupDTO.getFirstName() + " " + signupDTO.getLastName();
        signupDTO.setCreatedBy(fullName);
        //   signupDTO.setUpdatedBy(fullName);
        signupDTO.setCreatedDate(LocalDateTime.now());
        //  signupDTO.setUpdatedDate(LocalDateTime.now());
        signupDTO.setLogin_count(0);
        signupDTO.setLock_account(0);
        String password = RandomPasswordGenerator.generatePassword();
        String encryptPassword=passwordEncoder.encode(password);
        signupDTO.setExpireTime(LocalTime.now());
        signupDTO.setPassword(encryptPassword);
        boolean saveResult = this.signupRepository.save(signupDTO);
        if (saveResult) {
            sendEmail(signupDTO,password);
        }
        log.info("dto in service" + signupDTO.getPassword());
        return saveResult;
    }



    @Override
    public Optional<SignupDTO> findByEmailForReset(String email) {
        Optional<SignupDTO> optionalSignupDTO = this.signupRepository.findByEmail(email);
        if (optionalSignupDTO.isPresent()) {
            String password = RandomPasswordGenerator.generatePassword();
            String encryptPassword= passwordEncoder.encode(password);
            optionalSignupDTO.get().setExpireTime(LocalTime.now());
                optionalSignupDTO.get().setPassword(encryptPassword);
                optionalSignupDTO.get().setUpdatedDate(LocalDateTime.now());
                optionalSignupDTO.get().setUpdatedBy(optionalSignupDTO.get().getFirstName());
                boolean update = this.update(optionalSignupDTO.get());
                if (update) {
                        sendEmail(optionalSignupDTO.get(),password);
                }
            return optionalSignupDTO;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SignupDTO> findByEmail(String email) {
        Optional<SignupDTO> optionalSignupDTO = this.signupRepository.findByEmail(email);
        if (optionalSignupDTO.isPresent()) {
            log.info("data found for email : " + email);
            return optionalSignupDTO;
        } else {
            log.info("data not found for email : " + email);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SignupDTO> findByPhoneNumber(long phoneNumber) {
        Optional<SignupDTO> optionalSignupDTO = this.signupRepository.findByPhoneNumber(phoneNumber);
        if (optionalSignupDTO.isPresent()) {
            log.info("data found for phoneNumber : " + phoneNumber);
            return optionalSignupDTO;
        } else {
            log.info("data not found for phoneNumber : " + phoneNumber);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SignupDTO> findByEmailAndPassword(String email, String password) {
        Optional<SignupDTO> optionalSignupDTO = this.signupRepository.findByEmailAndPassword(email, password);
        if (optionalSignupDTO.isPresent()) {
            log.info("data found for Email  : " + email);
            return optionalSignupDTO;
        } else {
            log.info("data not found for Email : " + email);
            return Optional.empty();
        }
    }

    @Override
    public boolean update(SignupDTO signupDTO) {
        signupDTO.setUpdatedBy(signupDTO.getFirstName());
        signupDTO.setUpdatedDate(LocalDateTime.now());
        boolean result = this.signupRepository.update(signupDTO);
        return result;
    }


}
