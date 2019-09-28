package com.clinicware.controller;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Patient;
import com.clinicware.data.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/newPatient")
@SessionAttributes("user")
public class NewPatientController {

    @GetMapping
    public String newPatient(@SessionAttribute User user, Model model){

        return "newPatient";
    }

    @PostMapping(value = "add")
    public String addNewPatient(Patient patient, RestTemplate rest, RedirectAttributes attr, @SessionAttribute User user, Model model){
        patient.setClinicID(user.getClinicID());
        System.out.println("Patient is : " + patient);
        Map<String, Patient> patientMap = new HashMap<>();
        patientMap.put("patient", patient);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity response = rest.postForEntity("http://localhost:8080/newPatient/{patient}", patient, RegisterValidation.class, patientMap);
        RegisterValidation validation = (RegisterValidation) response.getBody();

        if(validation == null){
            attr.addFlashAttribute("result", "Patient " + patient.getEnglishPatientName() + " is already exist !");
        }else{
            attr.addFlashAttribute("result", "Patient " + patient.getEnglishPatientName() + " has been added successfully");
        }

        return "redirect:/appointments";
    }

}
