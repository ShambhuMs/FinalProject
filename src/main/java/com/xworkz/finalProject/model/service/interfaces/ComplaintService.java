package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;

import java.util.List;

public interface ComplaintService {
 boolean saveComplaintDetails(ComplaintDTO complaintDTO);
 List<SignupDTO> findByUserId(int userId);
}
