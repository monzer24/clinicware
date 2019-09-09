package com.clinicware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newClinic")
public class NewClinicController {

    @GetMapping
    public String toNewClinic(){
        return "newClinic";
    }


}
