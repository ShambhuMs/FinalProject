package com.xworkz.finalProject.model.service.interfaces;

import com.xworkz.finalProject.dto.ProfileDTO;

import java.util.Optional;

public interface ProfileService {
    boolean saveProfileDetails(ProfileDTO profileDTO);
   default Optional<ProfileDTO> findByUserId(int userId){
        return Optional.empty();
    }
}
