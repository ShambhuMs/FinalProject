package com.xworkz.finalProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xworkz.finalProject.dto.*;
import com.xworkz.finalProject.model.service.interfaces.AdminService;
import com.xworkz.finalProject.model.service.interfaces.ComplaintService;
import com.xworkz.finalProject.model.service.interfaces.DepartmentAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
@Slf4j
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
            model.addAttribute("usersDTO",list);
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
                boolean saved=this.adminService.AddDepartmentAdminDTO(departmentAdminDTO);
                if (saved){
                    model.addAttribute("msg","DepartmentAdmin details saved...");
                }else {
                    model.addAttribute("errorMsg","Enter valid details..");
                }
            }
            return "AddDepartmentAdmin";
        }

        @GetMapping("/getNotification")
        @ResponseBody
     public void getNotifications(AdminDTO adminDTO, HttpSession session, HttpServletResponse response) {
            try {
                List<ComplaintDTO> notifications = adminService.getUnreadNotifications();
                int notificationCount = notifications.size();

//            // Format dates and times
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

                List<Map<String, Object>> formattedNotifications = new ArrayList<>();
                Map<Long, String> formattedDates = new HashMap<>();
                notifications.forEach(notification -> {
                    Date createdAtDate = complaintService.convertToDateViaInstant(notification.getCreatedDate());

                    String formattedDate = complaintService.formatNotificationDate(createdAtDate);
                    formattedDates.put(notification.getId(), formattedDate);
                });
                for (ComplaintDTO notification : notifications) {
                    Map<String, Object> notificationMap = new HashMap<>();
                    notificationMap.put("id", notification.getId());
                    notificationMap.put("type", notification.getComplaintType());
                    notificationMap.put("area", notification.getCity());
                    notificationMap.put("address", notification.getAddress());
//                notificationMap.put("formattedDate", dateFormat.format(notification.getModifiedAt()));

                    notificationMap.put("formattedTime", formattedDates);
                    notificationMap.put("read", notification.isAdminRead());
                    formattedNotifications.add(notificationMap);
                }

                Map<String, Object> result = new HashMap<>();
                result.put("notificationCount", notificationCount);
                result.put("notifications", formattedNotifications);

                notifications.sort((n1, n2) -> n2.getCreatedDate().compareTo(n1.getCreatedDate()));
                session.setAttribute("notifications", notifications);
                session.setAttribute("notificationCount", notificationCount);
//                model.addAttribute("formattedDates", formattedDates);
                session.setAttribute("formattedDates", formattedDates);
                response.setContentType("application/json");
                response.getWriter().write(new ObjectMapper().writeValueAsString(result));
            } catch (Exception e) {
                log.error("Error fetching notifications", e);
                try {
                    response.getWriter().write("{}");
                } catch (IOException ioException) {
                    log.error("Error writing response", ioException);
                }
            }
        }
    @GetMapping("/markNotificationAsRead")
    @ResponseBody
    public String markNotificationAsRead(@RequestParam int notificationId, HttpSession session) {
        log.info("Marking notification as read: {}", notificationId);

        boolean success = adminService.markAsRead(notificationId);
        if (success) {
            // Update session attributes
            List<ComplaintDTO> notifications = (List<ComplaintDTO>) session.getAttribute("notifications");
            notifications.removeIf(notification -> notification.getId() == notificationId);
            session.setAttribute("notifications", notifications);
            session.setAttribute("notificationCount", notifications.size());
            return "success";
        }
        return "error";
    }

    @GetMapping("/viewNotificationComplaints")
    public String viewComplaint(@RequestParam("id") long complaintId, Model model) {
       Optional<ComplaintDTO> complaint = complaintService.findById(complaintId);
        model.addAttribute("complaint", complaint.get());
        return "AdminNotification"; // Ensure this JSP exists and matches your view
}
}
