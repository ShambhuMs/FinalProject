package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.*;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/departmentAdmin")
public class DepartmentAdminController {
    @Autowired
    private DepartmentAdminService departmentAdminService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/adminLogin")
    private String checkForm(Model model){
       model.addAttribute("departmentAdmin",true);
       return "AdminSignIn";
    }
    @PostMapping("/adminLogin")
    public String departmentLogin(@Valid DepartmentAdminDTO departmentAdminDTO, Model model){
        Optional<DepartmentAdminDTO> departmentAdminResult = this.departmentAdminService.findByAdminEmailAndPassword
                (departmentAdminDTO.getEmail(), departmentAdminDTO.getPassword());
        if (departmentAdminResult.isPresent()){
            model.addAttribute("success","Welcome to Department Admin Home");
            return "DepartmentAdminHome";
        }else {
            model.addAttribute("msg","Enter valid email and password");
            model.addAttribute("dto", departmentAdminDTO);
            model.addAttribute("departmentAdmin", true);
            return "AdminSignIn";
        }
    }

    @GetMapping("/viewComplaintsForDepAdmin")
    public String fetchAllRecords(Model model){
        List<ComplaintDTO> list= this.adminService.fetchAllCompliant();
        if (!list.isEmpty()){
            Map<Long, List<EmployeeDTO>> employeesByDepartment = new HashMap<>();
            for (ComplaintDTO complaint : list) {
                Integer departmentId = complaint.getDepartmentId();
                List<EmployeeDTO> departmentEmployees = this.departmentAdminService.getEmployeesByDepartmentId(departmentId);
                employeesByDepartment.put(complaint.getId(), departmentEmployees);
            }
            // Add complaints and employees by department to the model
            model.addAttribute("dto",list);
            model.addAttribute("employeesByDepartment", employeesByDepartment);
        }else {
            model.addAttribute("msg","No Records found");
        }
        return "ViewComplaintsForDepAdmin";
    }

    @GetMapping("/addEmployee")
    public String getAllDepartmentType(Model model){
        List<DepartmentDTO> departmentDTOList=this.departmentAdminService.fetchAllDepartments();
        if (!departmentDTOList.isEmpty()){
            model.addAttribute("department",departmentDTOList);
            model.addAttribute("selectedType","select");
        }else {
            model.addAttribute("errorMessage","department Not found");
        }
        return "AddEmployee";
    }


    @PostMapping("/addEmployee")
    public String addEmployee(@Valid EmployeeDTO employeeDTO, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errorMsg",bindingResult.getAllErrors());
            model.addAttribute("employeeDTO",employeeDTO);
        }else {
            boolean saved=this.departmentAdminService.addEmployee(employeeDTO);
            if (saved){
                model.addAttribute("msg","Employee details saved...");
            }else {
                model.addAttribute("errorMsg","Enter valid details..");
            }
        }
        return "AddEmployee";
    }

    @PostMapping("/findByComplaintType")
    public String findByType(@RequestParam String complaintType,Model model){
         if(!complaintType.equals("")){
             List<ComplaintDTO> complaintDTOList=this.departmentAdminService.fetchByComplaintType(complaintType);
             if (!complaintDTOList.isEmpty()){
                 Map<Long, List<EmployeeDTO>> employeesByDepartment = new HashMap<>();
                 for (ComplaintDTO complaint : complaintDTOList) {
                     int departmentId = complaint.getDepartmentId();
                     List<EmployeeDTO> departmentEmployees = this.departmentAdminService.getEmployeesByDepartmentId(departmentId);
                     employeesByDepartment.put(complaint.getId(), departmentEmployees);
                 }
                 // Add complaints and employees by department to the model
                 model.addAttribute("employeesByDepartment", employeesByDepartment);
                 model.addAttribute("dto",complaintDTOList);
             }else {
                 model.addAttribute("msg","No Records found");
             }
         }else {
             model.addAttribute("msg","choose department...");
         }
        return "ViewComplaintsForDepAdmin";
    }

   @RequestMapping( value = "/updateStatusOrAssign",method = {RequestMethod.GET,RequestMethod.POST})
   public String updateStatusOrAssign(@RequestParam(required = false,defaultValue = "0") long employeeId,@RequestParam(required = false,defaultValue ="")
   String complaintStatus,@RequestParam(required = false,defaultValue = "0") long id,Model model){ // id=complaintId
         if ( id != 0){
         Optional<ComplaintDTO> complaintDTOOptional= this.complaintService.findById(id);
           if (!complaintStatus.equals("")) {
                 complaintDTOOptional.get().setComplaintStatus(complaintStatus);
           } else if (employeeId!=0) {
               complaintDTOOptional.get().setEmployeeId(employeeId);
           }else {
               model.addAttribute("errorMsg","Enter valid details....");
           }
           if (employeeId!=0 || !complaintStatus.equals("")) {
               boolean update = this.complaintService.updateComplaint(complaintDTOOptional.get());
               if (update) {
                   model.addAttribute("updateMsg", "updated successfully");
               } else {
                   model.addAttribute("errorMsg", "update failed");
               }
           }
         }else {
             model.addAttribute("errorMsg","Enter valid data..");
         }
         return "redirect:/departmentAdmin/viewComplaintsForDepAdmin";
   }
}
