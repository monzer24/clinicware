package com.clinicware.controller;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/newPatient")
public class NewPatientController {

    @GetMapping
    public String newPatient(){
        return "newPatient";
    }

    @PostMapping(value = "add")
    public String addNewPatient(Patient patient, RestTemplate rest, RedirectAttributes model){
        System.out.println(patient);
        Map<String, Patient> patientMap = new HashMap<>();
        patientMap.put("patient", patient);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity response = rest.postForEntity("http://localhost:8080/newPatient/{patient}", patient, RegisterValidation.class, patientMap);
        RegisterValidation validation = (RegisterValidation) response.getBody();

        if(validation == null){
            model.addFlashAttribute("result", "Patient " + patient.getEnglishPatientName() + " is already exist !");
        }else{
            model.addFlashAttribute("result", "Patient " + patient.getEnglishPatientName() + " has been added successfully");
        }

        return "redirect:/appointments";
    }

}
