package com.clinicware.controller;

import com.clinicware.data.AppointmentRepository;
import com.clinicware.data.ClinicRepository;
import com.clinicware.data.pojo.Appointment;
import com.clinicware.data.pojo.Clinic;
import com.clinicware.data.pojo.Interval;
import com.clinicware.data.pojo.User;
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
@SessionAttributes("user")
public class AppointmentsController {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private ClinicRepository clinicRepo;

    @GetMapping
    public String toAppointments(@SessionAttribute User user, Model model, RedirectAttributes attr){
        List<Appointment> appointments = appointmentRepo.findAll().stream().sorted(Comparator.comparing(Appointment::getCtiID)).collect(Collectors.toList());
        List<Clinic> clinics = (List<Clinic>) clinicRepo.findAll();
        System.out.println(user);
        model.addAttribute("appointments", appointments);
        model.addAttribute("clinics", clinics);
//        attr.addFlashAttribute("username", user.getFirstName());
        return "appointments";
    }

//    @GetMapping(value = "/interval")
//    public String toInterval(RedirectAttributes model){
//        System.out.println("interval fragment");
//        model.addAttribute("fragment", "interval");
//        return "redirect:/appointments";
//    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String bookAppointment(@SessionAttribute User user, @RequestParam(name = "ctiID") String ctiID, Model model){
        System.out.println("ID is : " + ctiID);
        Appointment appointment = appointmentRepo.findById(ctiID).get();
        System.out.println("User is : " + user);
        System.out.println("Appointment is : " + appointment);
        return "/";
    }

}
