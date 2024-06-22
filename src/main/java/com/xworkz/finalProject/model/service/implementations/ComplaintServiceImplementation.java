package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.interfaces.ComplaintRepository;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ComplaintServiceImplementation implements ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;
    @Override
    public boolean saveComplaintDetails(ComplaintDTO complaintDTO) {
       boolean saved= this.complaintRepository.saveComplaintDetails(complaintDTO);
        return saved;
    }

    @Override
    public List<SignupDTO> findByUserId(int userId) {
        List<SignupDTO> list=  this.complaintRepository.findByUserId(userId);
        if (!list.isEmpty()){
            return list;
        }else {
            return Collections.emptyList();
        }
    }
}
