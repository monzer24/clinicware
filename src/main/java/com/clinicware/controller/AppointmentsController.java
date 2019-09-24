package com.clinicware.controller;

import com.clinicware.data.AppointmentRepository;
import com.clinicware.data.ClinicRepository;
import com.clinicware.data.pojo.Appointment;
import com.clinicware.data.pojo.Clinic;
import com.clinicware.data.pojo.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private ClinicRepository clinicRepo;

    @GetMapping
    public String toAppointments(Model model){
        List<Appointment> appointments = appointmentRepo.findAll().stream().sorted(Comparator.comparing(Appointment::getCtiID)).collect(Collectors.toList());
        List<Clinic> clinics = (List<Clinic>) clinicRepo.findAll();
        model.addAttribute("appointments", appointments);
        model.addAttribute("clinics", clinics);
        return "appointments";
    }

//    @GetMapping(value = "/interval")
//    public String toInterval(RedirectAttributes model){
//        System.out.println("interval fragment");
//        model.addAttribute("fragment", "interval");
//        return "redirect:/appointments";
//    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String bookAppointment(@RequestParam(name = "ctiID") String ctiID, Model model){
        System.out.println("ID is : " + ctiID);
        Optional<Appointment> appointment = appointmentRepo.findById(ctiID);
        if(appointment.get() == null){
            System.out.println("null");
        }
        System.out.println("Appointment is : " + appointment);
        return "/";
    }

}
