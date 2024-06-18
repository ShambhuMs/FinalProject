package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.implementations.SignupRepository;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import com.xworkz.finalProject.randomPassword.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class SignupServiceImplementation implements SignUpService {
    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(SignupDTO signupDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(signupDTO.getEmail());
        simpleMailMessage.setSubject("One time Password");
        simpleMailMessage.setText("Dear " + signupDTO.getFirstName() + " " + signupDTO.getLastName() +
                " your signup success Please SignIn through this password :" + signupDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " + "X-workz Team");
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public boolean save(SignupDTO signupDTO) {
        System.out.println("Running save method in Service....");
        String fullName = signupDTO.getFirstName() + " " + signupDTO.getLastName();
        signupDTO.setCreatedBy(fullName);
        //   signupDTO.setUpdatedBy(fullName);
        signupDTO.setCreatedDate(LocalDateTime.now());
        //  signupDTO.setUpdatedDate(LocalDateTime.now());
        signupDTO.setLogin_count(0);
        signupDTO.setLock_account(0);
        String password = RandomPasswordGenerator.generatePassword();
        signupDTO.setPassword(password);
        boolean saveResult = this.signupRepository.save(signupDTO);
        if (saveResult) {
            sendEmail(signupDTO);
        }
        System.out.println("dto in service" + signupDTO.getPassword());
        return saveResult;
    }

    @Override
    public Optional<SignupDTO> findByEmailForReset(String email) {
        Optional<SignupDTO> optionalSignupDTO = this.signupRepository.findByEmailForReset(email);
        if (optionalSignupDTO.isPresent()) {
          int  attempts = optionalSignupDTO.get().getLock_account();
            LocalDateTime lastSentTime = optionalSignupDTO.get().getUpdatedDate();
            LocalDateTime currentTime = LocalDateTime.now();
            if (attempts < 6) {
                String password = RandomPasswordGenerator.generatePassword();
                optionalSignupDTO.get().setPassword(password);
                optionalSignupDTO.get().setUpdatedDate(LocalDateTime.now());
                optionalSignupDTO.get().setUpdatedBy(optionalSignupDTO.get().getFirstName());
                boolean update = this.update(optionalSignupDTO.get());
                if (update) {
                        sendEmail(optionalSignupDTO.get());
                        attempts = attempts + 1;
                        optionalSignupDTO.get().setLock_account(attempts);
                        this.update(optionalSignupDTO.get());
                }
            } else {
                if (lastSentTime == null || ChronoUnit.HOURS.between(lastSentTime, currentTime) >= 24) {
                    // Reset attempts after 24 hours have passed
                    attempts = 3;
                    optionalSignupDTO.get().setLock_account(attempts);
                    this.update(optionalSignupDTO.get());
                }else {
                    System.out.println("getting mail after 24 hours..");
                }
            }
            return optionalSignupDTO;
        } else {
            return Optional.empty();
        }
        /*LocalDateTime lastSentTime =optionalSignupDTO.get().getUpdatedDate();
        LocalDateTime currentTime=LocalDateTime.now();
        long hoursSinceLastEmail = ChronoUnit.HOURS.between(lastSentTime, currentTime);
        if (attempts >=3 || hoursSinceLastEmail >= 24) {
                // Reset attempts after 24 hours have passed
                attempts = 3;
                optionalSignupDTO.get().setLock_account(attempts);
                this.update(optionalSignupDTO.get());
            }
        if (attempts<6){
            String password = RandomPasswordGenerator.generatePassword();
            optionalSignupDTO.get().setPassword(password);
            optionalSignupDTO.get().setUpdatedDate(currentTime);
            optionalSignupDTO.get().setUpdatedBy(optionalSignupDTO.get().getFirstName());

            boolean update = this.update(optionalSignupDTO.get());
            if (update) {
                // Send email and increment the attempt counter
                sendEmail(optionalSignupDTO.get());
                attempts += 1;
                optionalSignupDTO.get().setLock_account(attempts);
                this.update(optionalSignupDTO.get());
            }
            return Optional.of(optionalSignupDTO.get());
        }
        else {
        System.out.println("Email sending blocked. Please try again after 24 hours.");
        return Optional.empty();
    }
    }
*/
    }

    @Override
    public Optional<SignupDTO> findByemail(String email) {
        Optional<SignupDTO> optionalSignupDTO = this.signupRepository.findByEmail(email);
        if (optionalSignupDTO.isPresent()) {
            System.out.println("data found for email : " + email);
            return optionalSignupDTO;
        } else {
            System.out.println("data not found for email : " + email);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SignupDTO> findByPhoneNumber(long phoneNumber) {
        Optional<SignupDTO> optionalSignupDTO = this.signupRepository.findByPhoneNumber(phoneNumber);
        if (optionalSignupDTO.isPresent()) {
            System.out.println("data found for phoneNumber : " + phoneNumber);
            return optionalSignupDTO;
        } else {
            System.out.println("data not found for phoneNumber : " + phoneNumber);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SignupDTO> findByEmailAndPassword(String email, String password) {
        Optional<SignupDTO> optionalSignupDTO = this.signupRepository.findByEmailAndPassword(email, password);
        if (optionalSignupDTO.isPresent()) {
            System.out.println("data found for Email  : " + email);
            return optionalSignupDTO;
        } else {
            System.out.println("data not found for Email : " + email);
            return Optional.empty();
        }
    }

    @Override
    public boolean update(SignupDTO signupDTO) {
        boolean result = this.signupRepository.update(signupDTO);
        if (result) {
            System.out.println("update success..." + signupDTO);
            return result;
        } else {
            System.out.println("update failed..." + signupDTO);
            return false;
        }

    }


}
