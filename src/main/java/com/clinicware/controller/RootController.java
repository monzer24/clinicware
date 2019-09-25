package com.clinicware.controller;

import com.clinicware.data.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/")
@SessionAttributes("user")
public class RootController {

//    @ModelAttribute
//    public User newUser(){
//        return new User();
//    }

    @GetMapping
    public String root(User user, Model model){
        user = new User();
        model.addAttribute("user", user);
        return "redirect:home";
    }

}

