//package com.example.hostel.web;
//
//import com.example.hostel.model.Complaint;
//import com.example.hostel.repository.ComplaintRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    private final ComplaintRepository complaintRepo;
//
//    public AdminController(ComplaintRepository complaintRepo) {
//        this.complaintRepo = complaintRepo;
//    }
//
//    @GetMapping("/dashboard")
//    public String dashboard(Model model) {
//        model.addAttribute("complaints", complaintRepo.findAll());
//        return "admin-dashboard"; // ✅ resources/templates/admin-dashboard.html
//    }
//
//    @PostMapping("/update/{id}")
//    public String update(@PathVariable Long id, @RequestParam String status) {
//        Complaint c = complaintRepo.findById(id).orElseThrow();
//        c.setStatus(status);
//        complaintRepo.save(c);
//        return "redirect:/admin/dashboard";
//    }
//
//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        complaintRepo.deleteById(id);
//        return "redirect:/admin/dashboard";
//    }
//}
package com.example.hostel.web;

import com.example.hostel.model.Complaint;
import com.example.hostel.repository.ComplaintRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ComplaintRepository complaintRepo;

    public AdminController(ComplaintRepository complaintRepo) {
        this.complaintRepo = complaintRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("complaints", complaintRepo.findAll());
        return "admin-dashboard"; // resources/templates/admin-dashboard.html
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestParam String status) {
        Complaint c = complaintRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid complaint Id:" + id));
        c.setStatus(status);
        complaintRepo.save(c);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (complaintRepo.existsById(id)) {
            complaintRepo.deleteById(id);
        }
        return "redirect:/admin/dashboard";
    }
}

