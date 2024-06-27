package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.interfaces.AdminRepository;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/adminLogin")
    public String adminLoginCheck(@Valid AdminDTO adminDTO, Model model){
       Optional<AdminDTO> adminResult= this.adminService.findByEmailAndPassword(adminDTO.getEmail(), adminDTO.getPassword());
        System.out.println("Admin DTO from controller..."+adminResult);
       if (adminResult.isPresent()){
           model.addAttribute("success","Hello "+adminResult.get().getCreatedBy()+", Welcome to Admin Home");
           return "AdminHomePage";
       }else {
           model.addAttribute("msg","Enter valid email and password");
           model.addAttribute("dto",adminDTO);
           return "AdminSignIn";
       }
    }

@RequestMapping(value = "/viewDetails",method = {RequestMethod.GET,RequestMethod.POST})
public String fetchAllClientDetails(Model model){
        List<SignupDTO> list= this.adminService.fetchAllClientRecords();
        if (!list.isEmpty()){
            model.addAttribute("dto",list);
        }else {
            model.addAttribute("msg","No Records found");
        }
        return "AdminHomePage";
    }

    @GetMapping("/viewComplaintDetails")
    public String fetchAllComplaintDetails(Model model){
        List<ComplaintDTO> list= this.adminService.fetchAllCompliant();
        if (!list.isEmpty()){
            model.addAttribute("dto",list);
        }else {
            model.addAttribute("msg","No Records found");
        }
        return "AdminHomePage";
    }

    @PostMapping("/findByTypeOrAddress")
    public String findByTypeAddress(@RequestParam String complaintType,@RequestParam String city,Model model){
        if (complaintType!="" && city!=""){
            //findByBoth
         List<ComplaintDTO> complaintDTOList= this.adminService.getAllComplaintDetailsByTypeAndCity(complaintType,city);
         if (!complaintDTOList.isEmpty()){
             model.addAttribute("dto",complaintDTOList);
         }else {
             model.addAttribute("msg","0 Records found");
         }
        }else {
            //findByCity or complaintType
            List<ComplaintDTO> complaintDTOList= this.adminService.fetchByComplaintTypeOrCity(complaintType, city);
            if (!complaintDTOList.isEmpty()){
                model.addAttribute("dto",complaintDTOList);
            }else {
                model.addAttribute("msg","0 Records found");
            }
        }
        return "ViewComplaintDetails";
    }
}
