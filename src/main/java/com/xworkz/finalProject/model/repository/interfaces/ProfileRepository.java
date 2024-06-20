package com.xworkz.finalProject.model.repository.interfaces;

import com.xworkz.finalProject.dto.ProfileDTO;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    boolean saveProfileDetails(ProfileDTO profileDTO);
   default Optional<ProfileDTO> findByUserId(int userId){
        return Optional.empty();
    }
    boolean updateProfileDetails(ProfileDTO profileDTO);
    boolean updateStatus( ProfileDTO profileDTO);
    List<ProfileDTO> findDatasById(int userId);
}
