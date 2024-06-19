package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.service.interfaces.ProfileService;
import com.xworkz.finalProject.model.service.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class FileUploadController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private ProfileService profileService;

    private static final String UPLOAD_DIR = "C:\\Users\\shamb\\Desktop\\ImageUpload\\";

    public FileUploadController() {
        System.out.println("Created FileUploadController");
    }

    @PostMapping("/editProfile")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, SignupDTO signupDTO,
                             Model model) {
        if (multipartFile.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "EditProfile";
        }


        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String newFilename = signupDTO.getEmail() + "_" + originalFilename;
            Path path = Paths.get(UPLOAD_DIR, newFilename);
            Files.write(path, multipartFile.getBytes());


            Optional<SignupDTO> optionalUser = signUpService.findByemail(signupDTO.getEmail());
            if (!optionalUser.isPresent()) {
                model.addAttribute("message", "User not found.");
                return "ProfileEdit";
            }

            SignupDTO user = optionalUser.get();


            Optional<ProfileDTO> optionalProfileDTO = profileService.findByUserId(user.getId());
            if (optionalProfileDTO.isPresent()){
                optionalProfileDTO.get().setImageName(newFilename);
                optionalProfileDTO.get().setImageType(multipartFile.getContentType());
                optionalProfileDTO.get().setImageSize(multipartFile.getSize());
                optionalProfileDTO.get().setCreatedBy(signupDTO.getFirstName()+" "+signupDTO.getLastName());
                optionalProfileDTO.get().setCreatedDate(LocalDateTime.now());
            }else {
                ProfileDTO profileDTO=new ProfileDTO();
                profileDTO.setImageName(newFilename);
                profileDTO.setImageType(multipartFile.getContentType());
                profileDTO.setImageSize(multipartFile.getSize());
                profileDTO.setCreatedBy(signupDTO.getFirstName()+" "+signupDTO.getLastName());
                profileDTO.setCreatedDate(LocalDateTime.now());
                profileDTO.setUserId(user.getId());
              boolean saved=  profileService.saveProfileDetails(profileDTO);
              if (saved){
                  model.addAttribute("successMessage", "Profile saved successfully!");
              }
            }
            // Assuming there's a service to handle user profile logic
            // userProfileService.updateUserProfile(email, firstName, lastName, phone, newFilename);
            model.addAttribute("successMessage", "Profile updated successfully!");

        } catch (IOException e) {
            model.addAttribute("failedMessage", "File upload failed: " + e.getMessage());
        }

        return "EditProfile";
    }
}
