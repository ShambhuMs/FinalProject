package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.*;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    default Optional<AdminDTO> findByEmailAndPassword(String email, String password){
        return Optional.empty();
    }
    List<SignupDTO> fetchAllClientRecords();
    List<ComplaintDTO> fetchAllCompliant();
    List<ComplaintDTO> fetchByComplaintTypeOrCity( String complaintType,String city);
    List<ComplaintDTO> getAllComplaintDetailsByTypeAndCity(String complaintType,String city);
    default Optional<DepartmentDTO> findByDepartmentType(String departmentType){
        return Optional.empty();
    }
    boolean updateStatus(long id,String status);
    default Optional<DepartmentAdminDTO> fetchByDepAdminPhone(long phoneNumber){
        return Optional.empty();
    }
    default Optional<DepartmentAdminDTO> fetchByDepAdminEmail(String email){
        return Optional.empty();
    }
    boolean AddDepartmentAdminDTO(DepartmentAdminDTO departmentAdminDTO);

    public List<ComplaintDTO> getUnreadNotifications();
    boolean markAsRead(long id);
}
