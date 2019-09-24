package com.clinicware.controller;

import com.clinicware.data.ClinicRepository;
import com.clinicware.data.pojo.Clinic;
import com.clinicware.data.pojo.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/interval")
public class IntervalController {

    @Autowired
    private ClinicRepository repo;

    @GetMapping
    public void toInterval(Model model){
        List<Clinic> clinics = (List<Clinic>) repo.findAll();
        System.out.println("Controller : " + clinics);
        model.addAttribute("clinics", clinics);
//        return "interval";
    }

    @PostMapping("update")
    public String updateInterval(@ModelAttribute("interval") Interval interval, RedirectAttributes model, RestTemplate rest){
        System.out.println("right");

        interval.setIntervalStatus("1");
        System.out.println(interval);
        Map<String, Interval> intervalMap = new HashMap<>();
        intervalMap.put("interval", interval);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity response = rest.postForEntity("http://localhost:8080/interval/{interval}", interval, HashMap.class, intervalMap);
        Map<String, String> validation = (Map<String, String>) response.getBody();
        if(validation == null){
            model.addFlashAttribute("result", "Interval " + interval.getIntervalStatus() + " is already exist!");
        }else{
            model.addFlashAttribute("result", "Interval " + interval.getIntervalStatus() + " has been added successfully!");
        }
        return "redirect:/appointments";
    }

}
