package com.clinicware.controller;

import com.clinicware.data.AppointmentViewRepository;
import com.clinicware.data.ClinicRepository;
import com.clinicware.data.PatientRepository;
import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "appointments")
@SessionAttributes("user")
@Slf4j
public class AppointmentsController {

    private final AppointmentViewRepository appointmentRepo;

    private final ClinicRepository clinicRepo;

    private PatientRepository patientRepo;

    public AppointmentsController(AppointmentViewRepository appointmentRepo, ClinicRepository clinicRepo, PatientRepository patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.clinicRepo = clinicRepo;
        this.patientRepo = patientRepo;
    }

    @GetMapping
    public String toAppointments(@SessionAttribute User user, Model model){
        List<AppointmentView> appointments = appointmentRepo.findAll().stream().sorted(Comparator.comparing(AppointmentView::getCtiID)).collect(Collectors.toList());
        List<Clinic> clinics = (List<Clinic>) clinicRepo.findAll();
        List<Patient> patients = patientRepo.findAllByClinicID(user.getClinicID());
        System.out.println(user);
        model.addAttribute("appointments", appointments);
        model.addAttribute("clinics", clinics);
        model.addAttribute("patients", patients);
        if(!model.asMap().containsKey("createAppointment")){
            model.addAttribute("hide", "inline-block");
            model.addAttribute("createAppointment", "none");
        }else{
            model.addAttribute("createAppointment", "inline-block");
            model.addAttribute("hide", "none");
        }
        return "appointments";
    }

//    @GetMapping(value = "/interval")
//    public String toInterval(RedirectAttributes model){
//        System.out.println("interval fragment");
//        model.addAttribute("fragment", "interval");
//        return "redirect:/appointments";
//    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String bookAppointment(@SessionAttribute User user, @RequestParam(name = "ctiID") String ctiID, Model model, RedirectAttributes attr){
        System.out.println("ID is : " + ctiID);
        AppointmentView appointment = appointmentRepo.findById(ctiID).get();
        System.out.println("User is : " + user);
        System.out.println("AppointmentView is : " + appointment);
        attr.addFlashAttribute("createAppointment", "inline-block");
        attr.addFlashAttribute("intervalID", ctiID);
        return "redirect:/appointments";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String newAppointment(@SessionAttribute User user, Appointment appointment, Model model, RestTemplate rest ,RedirectAttributes attr){
        System.out.println(appointment);
        Map<String, Appointment> appointmentMap = new HashMap<>();
        appointmentMap.put("appointment", appointment);
        rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        ResponseEntity<RegisterValidation> response = rest.postForEntity("http://localhost:8080/create/{appointment}", appointment, RegisterValidation.class, appointmentMap);
        RegisterValidation validation = response.getBody();
        if(validation == null){
            attr.addFlashAttribute("result", "Appointment for " + appointment.getPatientID() + " is already exist!");
        }else{
            AppointmentView name = appointmentRepo.findEnglishNameByPatientId(appointment.getPatientID());
            attr.addFlashAttribute("result", "Appointment for " + name.getEnglishName() + " has been added successfully!");
        }
        return "redirect:/appointments";
    }

}
