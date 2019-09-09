package com.clinicware.controller;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Clinic;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/newClinic")
public class NewClinicController {

    @GetMapping
    public String toNewClinic(){
        return "newClinic";
    }

    @PostMapping("/add")
    public String addClinic(Clinic clinic, RedirectAttributes model, RestTemplate rest){
        System.out.println(clinic);
        Map<String, Clinic> clinicMap = new HashMap<>();
        clinicMap.put("clinic", clinic);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity response = rest.postForEntity("http://localhost:8080/newClinic/{clinic}", clinic, RegisterValidation.class, clinicMap);
        RegisterValidation validation = (RegisterValidation) response.getBody();
        return "";
    }
}
