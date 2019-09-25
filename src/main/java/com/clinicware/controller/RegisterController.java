package com.clinicware.controller;

import com.clinicware.data.ClinicRepository;
import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Clinic;
import com.clinicware.data.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/register")
@SessionAttributes("user")
public class RegisterController {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    ClinicRepository clinicRepo;

    @GetMapping
    public String register(@SessionAttribute("user") User user, Model model){
        if(user == null){
            return "redirect:/home";
        }
        List<Clinic> clinics = (List<Clinic>) clinicRepo.findAll();
        model.addAttribute("clinics", clinics);
        return "register";
    }

    @PostMapping("/regist")
    public String regist(User user, RedirectAttributes model, RestTemplate rest) {
        System.out.println(user);
        Map<String, User> userMap = new HashMap<>();
        userMap.put("user", user);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity response = rest.postForEntity("http://localhost:8080/register/{user}", user, RegisterValidation.class, userMap);
//        System.out.println(response.getBody().getClass().toString());
        RegisterValidation validation = (RegisterValidation) response.getBody();
        System.out.println(validation);
        if(validation == null){
            model.addFlashAttribute("user", "User is already exists");
        }else{
            model.addFlashAttribute("user", "User " + user.getFirstName() + " has been registered successfully");
        }
        return "redirect:/register";
    }

}
