package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;
import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.model.repository.interfaces.DepartmentAdminRepository;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import com.xworkz.finalProject.randomPassword.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentAdminServiceImplementation implements DepartmentAdminService {
    @Autowired
    private DepartmentAdminRepository departmentAdminRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailToEmployee(EmployeeDTO employeeDTO){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(employeeDTO.getEmail());
        simpleMailMessage.setSubject("One Time Password");
        simpleMailMessage.setText("Dear " + employeeDTO.getEmployeeName() +" your 'Employee-Account' has created successfully," +
          " Please SignIn through this password :" + employeeDTO.getPassword() + "\n\n"+
                "Thanks and Regards,\n" + "X-workz Team");
        javaMailSender.send(simpleMailMessage);
    }
    @Override
    public Optional<DepartmentAdminDTO> findByAdminEmailAndPassword(String email, String password) {
        Optional<DepartmentAdminDTO> optionalDepartmentDTO= this.departmentAdminRepository.findByAdminEmailAndPassword(email, password);
        return optionalDepartmentDTO;
    }

    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        String password = RandomPasswordGenerator.generatePassword();
        employeeDTO.setPassword(password);
        boolean saved= this.departmentAdminRepository.addEmployee(employeeDTO);
        if (saved){
          sendMailToEmployee(employeeDTO);
          return saved;
        }
      return false;
    }

    @Override
    public List<DepartmentDTO> fetchAllDepartments() {
        List<DepartmentDTO> departmentDTOList= this.departmentAdminRepository.fetchAllDepartments();
        if (!departmentDTOList.isEmpty()){
            return  departmentDTOList;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<EmployeeDTO> findByEmployeeEmail(String email) {
        Optional<EmployeeDTO> optionalEmployeeDTO=   this.departmentAdminRepository.findByEmployeeEmail(email);
        return optionalEmployeeDTO;
    }

    @Override
    public Optional<EmployeeDTO> findByEmployeePhoneNumber(long phoneNumber) {
        Optional<EmployeeDTO> optionalEmployeeDTO=   this.departmentAdminRepository.findByEmployeePhoneNumber(phoneNumber);
        return optionalEmployeeDTO;
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartmentId(int departmentId) {
        List<EmployeeDTO> employeeDTOS=this.departmentAdminRepository.getEmployeesByDepartmentId(departmentId);
        if (!employeeDTOS.isEmpty()){
            return employeeDTOS;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<ComplaintDTO> fetchByComplaintType(String complaintType) {
        List<ComplaintDTO> complaintDTOList=this.departmentAdminRepository.fetchByComplaintType(complaintType);
        if (!complaintDTOList.isEmpty()){
            return complaintDTOList;
        }else {
            return Collections.emptyList();
        }
    }
}
