package com.clinicware.controller;

import com.clinicware.data.pojo.User;
import com.clinicware.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private PasswordEncoder encoder;
    private UserRepository repo;

    @Autowired
    public HomeController(PasswordEncoder encoder, UserRepository repo) {
        this.encoder = encoder;
        this.repo = repo;
    }

    @GetMapping
    public String home(){
        return "home";
    }

    @PostMapping("/login")
    public String logIn(String username, String password){
        System.out.println(username + " " + password);
        User user = repo.findUserByUsername(username);
        System.out.println(user);
        if(encoder.matches(password, user.getPassword())){
            System.out.println("yes");
        }
        return "redirect:/home";
    }

}
