package com.clinicware.controller;

import com.clinicware.data.pojo.User;
import com.clinicware.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
@SessionAttributes("user")
public class HomeController {

    private PasswordEncoder encoder;
    private UserRepository repo;

    @Autowired
    public HomeController(PasswordEncoder encoder, UserRepository repo) {
        this.encoder = encoder;
        this.repo = repo;
    }

    @GetMapping
    public String home(@ModelAttribute User user, Model model){
        System.out.println(user);
        return "home";
    }

    @PostMapping("/login")
    public String logIn(String username, String password, @ModelAttribute User user, Model model, RedirectAttributes attr){
        System.out.println(username + " " + password);
        user = repo.findUserByUsername(username);
        System.out.println(user);
        if(user != null){
            if(encoder.matches(password, user.getPassword())){
                System.out.println("yes");
                model.addAttribute("user", user);
                if (user.getUserType().equals("0")) {
                    return "redirect:/register";
                } else if (user.getUserType().equals("1") || user.getUserType().equals("2")) {
                    return "redirect:/appointments";
                }
            }else{
                attr.addFlashAttribute("result", "Wrong password for this user");
                return "redirect:/home";
            }
        }else{
            System.out.println("no");
            attr.addFlashAttribute("result", "This UserName is not exist");
            return "redirect:/home";
        }
        return "/";
    }

}
