package com.xworkz.finalProject.controller;

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

@Controller
@RequestMapping("/")
public class FileUploadController {

    private static final String UPLOAD_DIR = "C:\\Users\\shamb\\Desktop\\ImageUpload\\";

    public FileUploadController() {
        System.out.println("Created FileUploadController");
    }

    @PostMapping("/editProfile")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             Model model) {
        if (multipartFile.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "ProfileEdit";
        }

        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String newFilename = email + "_" + originalFilename;
            Path path = Paths.get(UPLOAD_DIR, newFilename);
            Files.write(path, multipartFile.getBytes());

            // Assuming there's a service to handle user profile logic
            // userProfileService.updateUserProfile(email, firstName, lastName, phone, newFilename);

            model.addAttribute("message", "Profile updated successfully!");

        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }

        return "ProfileEdit";
    }
}
