package com.clinicware.controller;

import com.clinicware.data.AppointmentRepository;
import com.clinicware.data.pojo.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentRepository repo;

    @GetMapping
    public String toAppointments(Model model){
        List<Appointment> appointments = repo.findAll();
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

}
