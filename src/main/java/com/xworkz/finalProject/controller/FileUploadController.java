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

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
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
                             HttpSession httpSession, Model model) {
        if (multipartFile.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "EditProfile";
        }
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String newFilename = signupDTO.getEmail() + "_" + originalFilename;
            Path path = Paths.get(UPLOAD_DIR, newFilename);
            Files.write(path, multipartFile.getBytes());
            Optional<SignupDTO> optionalSignupDTO = signUpService.findByEmail(signupDTO.getEmail());
            if (!optionalSignupDTO.isPresent()) {
                model.addAttribute("message", "User not found.");
                return "ProfileEdit";
            }
            List<ProfileDTO> profileDTOList=this.profileService.findDatasById(optionalSignupDTO.get().getId());
            if (!profileDTOList.isEmpty()){
                profileDTOList.forEach(profileDTO -> {
                    profileDTO.setStatus("InActive");
                  boolean  value= this.profileService.updateStatus(profileDTO);
                });
                 ProfileDTO profileDTO = new ProfileDTO();
                 profileDTO.setImageName(newFilename);
                 profileDTO.setImageType(multipartFile.getContentType());
                 profileDTO.setImageSize(multipartFile.getSize());
                 profileDTO.setCreatedBy(signupDTO.getFirstName() + " " + signupDTO.getLastName());
                 profileDTO.setCreatedDate(LocalDateTime.now());
                 profileDTO.setUserId(optionalSignupDTO.get().getId());
                 profileDTO.setStatus("Active");
                 boolean saved = profileService.saveProfileDetails(profileDTO);
                 optionalSignupDTO.get().setFirstName(signupDTO.getFirstName());
                 optionalSignupDTO.get().setLastName(signupDTO.getLastName());
                 optionalSignupDTO.get().setPhoneNumber(signupDTO.getPhoneNumber());
                 boolean updateDetails = signUpService.update(optionalSignupDTO.get());
                 if (saved || updateDetails) {
                     model.addAttribute("successMessage", "Profile saved successfully!");
                 }
                List<ProfileDTO> profileDTOList0=this.profileService.findDatasById(optionalSignupDTO.get().getId());
                 if (!profileDTOList0.isEmpty()){
                     profileDTOList0.forEach(data->{
                         if (data.getStatus()=="Active" || data.getStatus().equals("Active")){
                             String ref = "/profile/" + data.getImageName();
                             System.err.println(data.getImageName());
                             httpSession.setAttribute("profileDTO", ref);
                         }
                     });
                 }
            }else {
                  ProfileDTO profileDTO = new ProfileDTO();
                  profileDTO.setImageName(newFilename);
                  profileDTO.setImageType(multipartFile.getContentType());
                  profileDTO.setImageSize(multipartFile.getSize());
                  profileDTO.setCreatedBy(signupDTO.getFirstName() + " " + signupDTO.getLastName());
                  profileDTO.setCreatedDate(LocalDateTime.now());
                  profileDTO.setUserId(optionalSignupDTO.get().getId());
                  profileDTO.setStatus("Active");
                  boolean saved = profileService.saveProfileDetails(profileDTO);

                  optionalSignupDTO.get().setFirstName(signupDTO.getFirstName());
                  optionalSignupDTO.get().setLastName(signupDTO.getLastName());
                  optionalSignupDTO.get().setPhoneNumber(signupDTO.getPhoneNumber());
                  boolean updateDetails = signUpService.update(optionalSignupDTO.get());
                  if (saved || updateDetails) {
                      model.addAttribute("successMessage", "Profile saved successfully!");
                  }
                List<ProfileDTO> profileDTOList1=this.profileService.findDatasById(profileDTO.getUserId());
                if (!profileDTOList1.isEmpty()){
                    profileDTOList1.forEach(data->{
                        if (data.getStatus()=="Active" || data.getStatus().equals("Active")){
                            String ref = "/profile/" + data.getImageName();
                            httpSession.setAttribute("profileDTO", ref);
                        }
                    });
                }
            }
        } catch (IOException e) {
            model.addAttribute("failedMessage", "File upload failed: " + e.getMessage());
        }
        return "EditProfile";
    }
}
