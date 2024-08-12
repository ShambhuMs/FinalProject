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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
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
    // for formatting Local date to date format
    @Override
    public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }

    //formatting LocalDate to Date to display the date and time only
    @Override
    public String formatNotificationDate(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        SimpleDateFormat todayFormat = new SimpleDateFormat("yyyyMMdd");

        Date now = new Date();
        if (todayFormat.format(now).equals(todayFormat.format(date))) {
            return timeFormat.format(date);
        } else {
            return dateFormat.format(date);
        }
    }
}
