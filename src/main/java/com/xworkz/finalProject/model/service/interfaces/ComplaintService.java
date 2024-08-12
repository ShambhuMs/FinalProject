package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ComplaintService {
 boolean saveComplaintDetails(ComplaintDTO complaintDTO);
 List<ComplaintDTO> findByUserId(int userId);
 default Optional<ComplaintDTO> findById(long id){
  return Optional.empty();
 }
 boolean updateComplaint(ComplaintDTO complaintDTO);
  Date convertToDateViaInstant(LocalDateTime dateToConvert);
 String formatNotificationDate(Date date);
}
