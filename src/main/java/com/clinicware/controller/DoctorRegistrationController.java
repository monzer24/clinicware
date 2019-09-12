package com.clinicware.controller;

import com.clinicware.data.ClinicRepository;
import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Clinic;
import com.clinicware.data.pojo.Doctor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/newDoctor")
public class DoctorRegistrationController {

    private ClinicRepository repo;

    public DoctorRegistrationController(ClinicRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String toNewDoctor(RedirectAttributes model){
        List<Clinic> clinics = (List<Clinic>) repo.findAll();
        System.out.println("all : " + clinics);
        model.addFlashAttribute("clinics", clinics);
        return "/newDoctor";
    }

    @PostMapping("/new")
    public String addNewDoctor(Doctor doctor, RedirectAttributes model, RestTemplate rest){
        System.out.println(doctor);
        Map<String, Doctor> doctorMap = new HashMap<>();
        doctorMap.put("doctor", doctor);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity response = rest.postForEntity("http://localhost:8080/newDoctor/{doctor}", doctor, RegisterValidation.class, doctorMap);
        RegisterValidation validation = (RegisterValidation) response.getBody();
        if(validation == null){
            model.addFlashAttribute("clinic", "Doctor " + doctor.getArabicName() + " is already exist!");
        }else{
            model.addFlashAttribute("clinic", "Doctor " + doctor.getArabicName() + " has been added successfully!");
        }
        return "redirect:/newDoctor";
    }

}
