package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.defaultValue.DefaultValues;
import com.xworkz.finalProject.dto.*;
import com.xworkz.finalProject.model.repository.interfaces.AdminRepository;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.randomPassword.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMailToDepartmentAdmin(DepartmentAdminDTO departmentAdminDTO){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(departmentAdminDTO.getEmail());
        simpleMailMessage.setSubject("One Time Password");
        simpleMailMessage.setText("Dear " + departmentAdminDTO.getDepartmentAdminName() +" your 'Department Admin Account'" +
                " has created successfully, Please SignIn through this password :" + departmentAdminDTO.getPassword() + "\n\n"+
                "Thanks and Regards,\n" + "X-workz Team");
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public Optional<AdminDTO> findByEmailAndPassword(String email, String password) {
      Optional<AdminDTO> loginData= this.adminRepository.findByEmailAndPassword(email,password);
        return loginData;
    }

    @Override
    public List<SignupDTO> fetchAllClientRecords() {
        List<SignupDTO> signupDTOS=this.adminRepository.fetchAllClientRecords();
        if (!signupDTOS.isEmpty()){
            return signupDTOS;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ComplaintDTO> fetchAllCompliant() {
        List<ComplaintDTO> complaintDTOS=this.adminRepository.fetchAllCompliant();
        if (!complaintDTOS.isEmpty()){
            return complaintDTOS;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ComplaintDTO> fetchByComplaintTypeOrCity(String complaintType,String city) {
        List<ComplaintDTO> complaintDTOS=this.adminRepository.fetchByComplaintTypeOrCity(complaintType,city);
        System.err.println(complaintDTOS);
        if (!complaintDTOS.isEmpty()){
            return complaintDTOS;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ComplaintDTO> getAllComplaintDetailsByTypeAndCity(String complaintType,String city) {
        List<ComplaintDTO> complaintDTOS=this.adminRepository.getAllComplaintDetailsByTypeAndCity(complaintType, city);
        if (!complaintDTOS.isEmpty()){
            return complaintDTOS;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<DepartmentDTO> findByDepartmentType(String departmentType) {
        Optional<DepartmentDTO> departmentDTO= this.adminRepository.findByDepartmentType(departmentType);
        return departmentDTO;
    }

    @Override
    public boolean updateStatus(long id, String status) {
      boolean updateStatus=  this.adminRepository.updateStatus(id, status);
        return updateStatus;
    }

    @Override
    public Optional<DepartmentAdminDTO> fetchByDepAdminPhone(long phoneNumber) {
        Optional<DepartmentAdminDTO> optionalDepartmentAdminDTO=this.adminRepository.fetchByDepAdminPhone(phoneNumber);
        return optionalDepartmentAdminDTO;
    }

    @Override
    public Optional<DepartmentAdminDTO> fetchByDepAdminEmail(String email) {
        Optional<DepartmentAdminDTO> optionalDepartmentAdminDTO=this.adminRepository.fetchByDepAdminEmail(email);
        return optionalDepartmentAdminDTO;
    }

    @Override
    public boolean updateDepartmentAdminDTO(DepartmentAdminDTO departmentAdminDTO) {
        String password = RandomPasswordGenerator.generatePassword();
        departmentAdminDTO.setPassword(password);
        departmentAdminDTO.setCreatedBy(DefaultValues.ZERO.getDefaultStatus());
        departmentAdminDTO.setCreatedDate(LocalDateTime.now());
        boolean saved= this.adminRepository.updateDepartmentAdminDTO(departmentAdminDTO);
        if (saved){
            sendMailToDepartmentAdmin(departmentAdminDTO);
            return saved;
        }
        return false;
    }
}
