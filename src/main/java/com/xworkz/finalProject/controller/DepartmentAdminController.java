package com.xworkz.finalProject.controller;

import com.xworkz.finalProject.dto.*;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departmentAdmin")
public class DepartmentAdminController {
    @Autowired
    private DepartmentAdminService departmentAdminService;
    @Autowired
    private AdminService adminService;

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
            model.addAttribute("dto",list);
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
}
