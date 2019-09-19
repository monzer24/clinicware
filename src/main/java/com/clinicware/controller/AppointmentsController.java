package com.clinicware.controller;

import com.clinicware.data.AppointmentRepository;
import com.clinicware.data.ClinicRepository;
import com.clinicware.data.pojo.Appointment;
import com.clinicware.data.pojo.Clinic;
import com.clinicware.data.pojo.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/appointments")
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

}
