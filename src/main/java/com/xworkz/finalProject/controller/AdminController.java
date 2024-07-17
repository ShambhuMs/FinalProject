package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.*;
import com.xworkz.finalProject.model.repository.interfaces.AdminRepository;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private DepartmentAdminService departmentAdminService;

    @PostMapping("/adminLogin")
    public String adminLoginCheck(@Valid AdminDTO adminDTO, Model model){
       Optional<AdminDTO> adminResult= this.adminService.findByEmailAndPassword(adminDTO.getEmail(), adminDTO.getPassword());
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
        return "ViewComplaintDetails";
    }


    @PostMapping(value = "/findByTypeOrAddress")
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

    @PostMapping(value = "/complaintAssign")
    public String assign(@RequestParam long id,@RequestParam String assign,Model model){
      Optional<ComplaintDTO> complaintDTOOptional=  this.complaintService.findById(id);
      if (assign!=null){
          Optional<DepartmentDTO> departmentDTO= this.adminService.findByDepartmentType(assign);
          complaintDTOOptional.get().setDepartmentId(departmentDTO.get().getDepartment_id());
          this.complaintService.updateComplaint(complaintDTOOptional.get());
      }
      return "redirect:/viewComplaintDetails";
    }

    @PostMapping(value = "/updateComplaintStatus")
    public String updateComplaintStatus(@RequestParam long id,@RequestParam String status,Model model){
        Optional<ComplaintDTO> optionalComplaintDTO = this.complaintService.findById(id);
        optionalComplaintDTO.get().setComplaintStatus(status);
        this.complaintService.updateComplaint(optionalComplaintDTO.get());
        return "redirect:/viewComplaintDetails";
    }
    @GetMapping("/addDepartmentAdmin")
    public String getAllDepartmentType(Model model){
        List<DepartmentDTO> departmentDTOList=this.departmentAdminService.fetchAllDepartments();
        if (!departmentDTOList.isEmpty()){
            model.addAttribute("department",departmentDTOList);
            model.addAttribute("selectedType","select");
        }else {
            model.addAttribute("errorMessage","department Not found");
        }
        return "AddDepartmentAdmin";
    }

    @PostMapping("/addDepartmentAdmin")
    public String saveDepartmentAdmin(@Valid DepartmentAdminDTO departmentAdminDTO, BindingResult bindingResult,
                                      Model model){
            if (bindingResult.hasErrors()){
                model.addAttribute("errorMsg",bindingResult.getAllErrors());
                model.addAttribute("employeeDTO",departmentAdminDTO);
            }else {
                boolean saved=this.adminService.updateDepartmentAdminDTO(departmentAdminDTO);
                if (saved){
                    model.addAttribute("msg","DepartmentAdmin details saved...");
                }else {
                    model.addAttribute("errorMsg","Enter valid details..");
                }
            }
            return "AddDepartmentAdmin";
        }
}
