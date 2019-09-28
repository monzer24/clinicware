package com.clinicware.controller;

import com.clinicware.data.AppointmentViewRepository;
import com.clinicware.data.ClinicRepository;
import com.clinicware.data.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "appointments")
@SessionAttributes("user")
public class AppointmentsController {

    @Autowired
    private AppointmentViewRepository appointmentRepo;

    @Autowired
    private ClinicRepository clinicRepo;

    @GetMapping
    public String toAppointments(@SessionAttribute User user, Model model){
        List<AppointmentView> appointments = appointmentRepo.findAll().stream().sorted(Comparator.comparing(AppointmentView::getCtiID)).collect(Collectors.toList());
        List<Clinic> clinics = (List<Clinic>) clinicRepo.findAll();
        System.out.println(user);
        model.addAttribute("appointments", appointments);
        model.addAttribute("clinics", clinics);
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
    public String newAppointment(@SessionAttribute User user, Appointment appointment, Model model){
        System.out.println(appointment);
        return "redirect:/appointments";
    }

}
