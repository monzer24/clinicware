package com.clinicware.controller;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.ValidationEvent;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    ObjectMapper mapper;

    @GetMapping
    public String register(){
        return "register";
    }

    @PostMapping("/regist")
    public String regist(User user, RedirectAttributes model, RestTemplate rest) throws JsonProcessingException {
        System.out.println(user);
        Map<String, User> userMap = new HashMap<>();
        userMap.put("user", user);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity response = rest.postForEntity("http://localhost:8080/register/{user}", user, RegisterValidation.class, userMap);
//        System.out.println(response.getBody().getClass().toString());
        RegisterValidation validation = (RegisterValidation) response.getBody();
        System.out.println(validation);
        if(validation == null){
            model.addAttribute("user", "User is already exists");
        }else{
            model.addAttribute("user", "User " + user.getFirstName() + " has been registered successfully");
        }
        return "redirect:/register";
    }

}
