package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository {
 boolean saveComplaintDetails(ComplaintDTO complaintDTO);
 List<ComplaintDTO> findByUserId(int userId);
 default Optional<ComplaintDTO> findById(long id){
       return Optional.empty();
 }
 boolean updateComplaint(ComplaintDTO complaintDTO);
}
