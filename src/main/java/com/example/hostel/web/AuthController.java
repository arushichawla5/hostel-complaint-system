//package com.example.hostel.web;
//
//import com.example.hostel.model.User;
//import com.example.hostel.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//
//public class AuthController {
//    private final UserRepository userRepo;
//    private final BCryptPasswordEncoder passwordEncoder;
//    public AuthController(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder){
//        this.userRepo = userRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @GetMapping("/register")
//    public String registerForm(Model m){
//        m.addAttribute("user", new User());
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registerSubmit(@ModelAttribute User user, Model m){
//        if(userRepo.findByUsername(user.getUsername()).isPresent()){
//            m.addAttribute("error", "Username already exists");
//            return "register";
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepo.save(user);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/login")
//    public String login(){ return "login"; }
//}
package com.example.hostel.web;

import com.example.hostel.model.User;
import com.example.hostel.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class AuthController {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm(Model m){
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute User user, BindingResult result, Model m){
        if (result.hasErrors()) {
            return "register";
        }
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            m.addAttribute("error", "Username already exists");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // ✅ default role set
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
