package com.clinicware.controller;

import com.clinicware.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping
    public String register(){
        return "/register";
    }

    @PostMapping("/regist")
    public String regist(Model model, String username, String password, String first, String last, String title, int userType){
        User user = new User(username, password, first, last, title, userType);
        System.out.println(user);
        model.addAttribute("user", user);
        return "redirect:/register/regist/"+user;
    }

}
