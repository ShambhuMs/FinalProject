package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.*;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/departmentAdmin")
@Slf4j
public class DepartmentAdminController {
    @Autowired
    private DepartmentAdminService departmentAdminService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/adminLogin")
    private String checkForm(Model model){
       model.addAttribute("departmentAdmin",true);
       return "AdminSignIn";
    }
    @GetMapping("/resetPasswordAnyTime")
    private String checkResetPassword(Model model){
        model.addAttribute("departmentAdmin",true);
        return "ResetPasswordAnyTime";
    }
    @PostMapping("/adminLogin")
    public String departmentLogin(@Valid DepartmentAdminDTO departmentAdminDTO, Model model, HttpSession session){
        model.addAttribute("departmentAdmin", true);
        if (departmentAdminDTO.getEmail().equals("") || departmentAdminDTO.getPassword().equals("")) {
            model.addAttribute("msg", "Enter email or password");
            return "AdminSignIn";
        }
        Optional<DepartmentAdminDTO> departmentAdminResult = this.adminService.fetchByDepAdminEmail(departmentAdminDTO.getEmail());
        if (!departmentAdminResult.isPresent()) {
            model.addAttribute("msg", "Enter email or password");
            return "AdminSignIn";
        }
        if (passwordEncoder.matches(departmentAdminDTO.getPassword(),departmentAdminResult.get().getPassword())){
            model.addAttribute("success","Welcome to Department Admin Home");
            session.setAttribute("dto",departmentAdminResult.get());
            return "DepartmentAdminHome";
        }else {
            model.addAttribute("msg","Enter valid email and password");
            model.addAttribute("dto", departmentAdminDTO);
            return "AdminSignIn";
        }
    }
    @PostMapping("/resetPasswordAnyTime")
    public String resetDepartmentAdminPassword(@Valid PasswordResetDTO passwordResetDTO, Model model,HttpSession session){
        DepartmentAdminDTO departmentAdminDTO=(DepartmentAdminDTO) session.getAttribute("dto");
        Optional<DepartmentAdminDTO> optionalDepartmentAdminDTO=  this.adminService.fetchByDepAdminEmail(departmentAdminDTO.getEmail());
        if (passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmNewPassword())){
            String encryptPassword=passwordEncoder.encode(passwordResetDTO.getNewPassword());
            optionalDepartmentAdminDTO.get().setPassword(encryptPassword);
            boolean updateValue=this.departmentAdminService.updateDepartmentAdminDTO(optionalDepartmentAdminDTO.get());
            if (updateValue){
                model.addAttribute("msg","Password Reset Success..");
            }else {
                model.addAttribute("errorMsg","Password update failed..\n\n Enter valid password");
            }
        } else {
            model.addAttribute("errorMsg","NewPassword and Confirm Password should be same");
        }
        model.addAttribute("departmentAdmin",true);
        return "ResetPasswordAnyTime";
    }

    @GetMapping("/viewComplaintsForDepAdmin")
    public String fetchAllRecords(Model model,HttpSession session){
        DepartmentAdminDTO departmentAdminDTO=(DepartmentAdminDTO) session.getAttribute("dto");
        if (departmentAdminDTO==null){
            model.addAttribute("msg","Please Re-Login Your Account");
            return "ViewComplaintsForDepAdmin";
        }
        Optional<DepartmentAdminDTO> departmentAdminDTOOptional= this.adminService.fetchByDepAdminEmail(departmentAdminDTO.getEmail());
      List<ComplaintDTO> complaintDTOList=this.departmentAdminService.getComplaintsByDepartmentId
              (departmentAdminDTOOptional.get().getDepartmentId());
      if (!complaintDTOList.isEmpty()){
          model.addAttribute("complaintDTO",complaintDTOList);
          List<ComplaintDTO> list= this.adminService.fetchAllCompliant();
          if (!list.isEmpty()){
              Map<Long, List<EmployeeDTO>> employeesByDepartment = new HashMap<>();
              for (ComplaintDTO complaint : list) {
                  Integer departmentId = complaint.getDepartmentId();
                  List<EmployeeDTO> departmentEmployees = this.departmentAdminService.getEmployeesByDepartmentId(departmentId);
                  employeesByDepartment.put(complaint.getId(), departmentEmployees);
              }
              // Add complaints and employees by department to the model
              model.addAttribute("employeesByDepartment", employeesByDepartment);
          }else {
              model.addAttribute("msg","No Records found");
          }
      }else {
          model.addAttribute("msg","No Records found");
      }
      model.addAttribute("departmentAdmin", true);
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

    @GetMapping(value = "/updateStatusOrAssign")
    public String updateStatusOrAssign(@RequestParam(required = false, defaultValue = "0") long employeeId,
                                       @RequestParam(required = false, defaultValue = "") String complaintStatus,
                                       @RequestParam(required = false, defaultValue = "0") long id,
                                       Model model, RedirectAttributes redirectAttributes) { // Added RedirectAttributes
        if (id != 0) {
            Optional<ComplaintDTO> complaintDTOOptional = this.complaintService.findById(id);
            if (complaintDTOOptional.isPresent()) {
                if (!complaintStatus.equals("")) {
                    complaintDTOOptional.get().setComplaintStatus(complaintStatus);
                } else if (employeeId != 0) {
                    complaintDTOOptional.get().setEmployeeId(employeeId);
                } else {
                    redirectAttributes.addFlashAttribute("errorMsg", "Enter valid details....");
                    return "redirect:/departmentAdmin/viewComplaintsForDepAdmin";
                }
                if (employeeId != 0 || !complaintStatus.equals("")) {
                    boolean update = this.complaintService.updateComplaint(complaintDTOOptional.get());
                    if (update) {
                        redirectAttributes.addFlashAttribute("updateMsg", "Updated successfully");
                    } else {
                        redirectAttributes.addFlashAttribute("errorMsg", "Update failed");
                    }
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMsg", "Complaint not found");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMsg", "Enter valid data..");
        }
        return "redirect:/departmentAdmin/viewComplaintsForDepAdmin";
    }

}
