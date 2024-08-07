package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.implementations.SignupRepository;
import com.xworkz.finalProject.model.repository.interfaces.ComplaintRepository;
import com.xworkz.finalProject.model.repository.interfaces.EmployeeRepository;
import com.xworkz.finalProject.model.service.interfaces.EmployeeService;
import com.xworkz.finalProject.randomPassword.RandomPasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ComplaintRepository complaintRepository;
    @Override
    public Optional<EmployeeDTO> findByEmailAndPassword(String email, String password) {
        Optional<EmployeeDTO> optionalEmployeeDTO=this.employeeRepository.findByEmailAndPassword(email, password);
        return optionalEmployeeDTO;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
         boolean update=  this.employeeRepository.updateEmployee(employeeDTO);
         return update;
    }

    @Override
    public boolean updateOtp(int complaintId) {
        Optional<ComplaintDTO> optionalComplaintDTO= this.complaintRepository.findById(complaintId);
        if (optionalComplaintDTO.isPresent()){
          Optional<SignupDTO> optionalSignupDTO= signupRepository.findById(optionalComplaintDTO.get().getUserId());
            String otp=RandomPasswordGenerator.generatePassword();
            optionalSignupDTO.get().setPassword(otp);
            boolean update= this.signupRepository.update(optionalSignupDTO.get());
            sendEmail(optionalSignupDTO.get(),otp);
            return update;
        }
        return false;
    }

    public void sendEmail(SignupDTO signupDTO, String otp) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(signupDTO.getEmail());
        simpleMailMessage.setSubject("One time Password");
        simpleMailMessage.setText("Dear " + signupDTO.getFirstName() + " " + signupDTO.getLastName() +
                " This is One time password, If your complaint resolved please share this otp to the employee :" + otp + "\n\n" +
                "Thanks and Regards,\n" + " " + "X-workz Team");
        javaMailSender.send(simpleMailMessage);
    }
}
