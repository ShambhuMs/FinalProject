package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.implementations.SignupRepository;
import com.xworkz.finalProject.model.repository.interfaces.ComplaintRepository;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public List<ComplaintDTO> findByUserId(int userId) {
        List<ComplaintDTO> list=  this.complaintRepository.findByUserId(userId);
        if (!list.isEmpty()){
            return list;
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<ComplaintDTO> findById(long id) {
        Optional<ComplaintDTO> optionalComplaintDTO=  this.complaintRepository.findById(id);
        return optionalComplaintDTO;
    }

    @Override
    public boolean updateComplaint(ComplaintDTO complaintDTO) {
       boolean update= this.complaintRepository.updateComplaint(complaintDTO);
        return update;
    }
}
