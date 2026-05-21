//package com.example.hostel.web;
//
//import com.example.hostel.model.Complaint;
//import com.example.hostel.repository.ComplaintRepository;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/complaints")
//public class ComplaintController {
//    private final ComplaintRepository repo;
//
//    public ComplaintController(ComplaintRepository repo) {
//        this.repo = repo;
//    }
//
//    @GetMapping
//    public String list(Authentication auth, Model m) {
//        String user = auth.getName();
//        List<Complaint> complaints = repo.findByCreatedBy(user);
//        m.addAttribute("complaints", complaints);
//        m.addAttribute("newComplaint", new Complaint());
//        return "complaints"; // complaints.html template render karega
//    }
//
//    @PostMapping("/add")
//    public String add(@ModelAttribute Complaint complaint, Authentication auth) {
//        complaint.setCreatedBy(auth.getName());
//        repo.save(complaint);
//        return "redirect:/complaints";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editForm(@PathVariable Long id, Authentication auth, Model m) {
//        Complaint c = repo.findById(id).orElse(null);
//
//        // Agar complaint nahi mili ya dusre user ki hai → redirect
//        if (c == null || !c.getCreatedBy().equals(auth.getName())) {
//            return "redirect:/complaints";
//        }
//
//        m.addAttribute("complaint", c);
//        return "edit";  // edit.html render hoga
//    }
//
//
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute Complaint complaint, Authentication auth) {
//        Complaint existing = repo.findById(complaint.getId()).orElseThrow();
//        if (!existing.getCreatedBy().equals(auth.getName())) return "redirect:/complaints";
//        existing.setTitle(complaint.getTitle());
//        existing.setDescription(complaint.getDescription());
//        existing.setStatus(complaint.getStatus());
//        repo.save(existing);
//        return "redirect:/complaints";
//    }
//
//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable Long id, Authentication auth) {
//        Complaint c = repo.findById(id).orElseThrow();
//        if (c.getCreatedBy().equals(auth.getName())) {
//            repo.deleteById(id);
//        }
//        return "redirect:/complaints"; // ✅ fix
//    }
//}
package com.example.hostel.web;

import com.example.hostel.model.Complaint;
import com.example.hostel.repository.ComplaintRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {
    private final ComplaintRepository repo;

    public ComplaintController(ComplaintRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String list(Authentication auth, Model m) {
        String username = auth.getName();
        List<Complaint> complaints = repo.findByCreatedBy(username);
        m.addAttribute("complaints", complaints);
        m.addAttribute("newComplaint", new Complaint());
        return "complaints"; // complaints.html
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("newComplaint") Complaint complaint,
                      BindingResult bindingResult,
                      Authentication auth,
                      Model model) {
        if (bindingResult.hasErrors()) {
            String username = auth.getName();
            model.addAttribute("complaints", repo.findByCreatedBy(username));
            return "complaints";
        }
        complaint.setCreatedBy(auth.getName());
        repo.save(complaint);
        return "redirect:/complaints";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Authentication auth, Model m) {
        Complaint c = repo.findById(id).orElse(null);

        if (c == null || !c.getCreatedBy().equals(auth.getName())) {
            return "redirect:/complaints";
        }

        m.addAttribute("complaint", c);
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Complaint complaint, Authentication auth) {
        Complaint existing = repo.findById(complaint.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid complaint Id:" + complaint.getId()));

        if (!existing.getCreatedBy().equals(auth.getName())) {
            return "redirect:/complaints";
        }

        existing.setTitle(complaint.getTitle());
        existing.setDescription(complaint.getDescription());
        existing.setStatus(complaint.getStatus());
        repo.save(existing);

        return "redirect:/complaints";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Authentication auth) {
        Complaint c = repo.findById(id).orElse(null);
        if (c != null && c.getCreatedBy().equals(auth.getName())) {
            repo.deleteById(id);
        }
        return "redirect:/complaints";
    }
}
