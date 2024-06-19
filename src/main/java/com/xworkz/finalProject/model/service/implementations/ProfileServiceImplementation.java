package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.model.repository.interfaces.ProfileRepository;
import com.xworkz.finalProject.model.service.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProfileServiceImplementation implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public boolean saveProfileDetails(ProfileDTO profileDTO) {
      boolean saveResult=  this.profileRepository.saveProfileDetails(profileDTO);
      if (saveResult){
          return saveResult;
      }else {
          return false;
      }
    }

    @Override
    public Optional<ProfileDTO> findByUserId(int userId) {
       Optional<ProfileDTO> optionalProfileDTO=this.profileRepository.findByUserId(userId);
       if (optionalProfileDTO.isPresent()){
           return optionalProfileDTO;
       }else {
          return Optional.empty();
       }
    }
}
