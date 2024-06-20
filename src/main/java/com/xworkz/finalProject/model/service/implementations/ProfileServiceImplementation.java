package com.xworkz.finalProject.model.service.implementations;

import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.model.repository.interfaces.ProfileRepository;
import com.xworkz.finalProject.model.service.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
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

    @Override
    public boolean updateProfileDetails(ProfileDTO profileDTO) {
        boolean saveResult=  this.profileRepository.updateProfileDetails(profileDTO);
        if (saveResult){
            return saveResult;
        }else {
            return false;
        }

    }

    @Override
    public boolean updateStatus(ProfileDTO profileDTO) {
      boolean statusResult=  this.profileRepository.updateStatus( profileDTO);
      if (statusResult){
          return statusResult;
      }else {
          return false;
      }
    }

    @Override
    public List<ProfileDTO> findDatasById(int userId) {
        List<ProfileDTO> list=  this.profileRepository.findDatasById(userId);
        if (!list.isEmpty()){
           return list;
        }else {
            return Collections.emptyList();
        }
    }


}
