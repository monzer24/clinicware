package com.clinicware.service.api;

import com.clinicware.data.AppointmentViewRepository;
import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Appointment;
import com.clinicware.data.pojo.AppointmentView;
import com.clinicware.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/create")
@CrossOrigin("*")
public class AppointmentRestController  {

    private AppointmentViewRepository repo;
    private AppointmentService service;

    public AppointmentRestController(AppointmentViewRepository repo, AppointmentService service) {
        this.repo = repo;
        this.service = service;
    }

    @RequestMapping(path = "/{appointment}", headers = ("accept=*/*"), produces = "application/xml")
    public ResponseEntity<RegisterValidation> createAppointment(@RequestBody Appointment appointment){
        ResponseEntity<RegisterValidation> response = null;
        System.out.println("RestController" + appointment);
        AppointmentView temp = repo.findByPatientId(appointment.getPatientID());
        if(temp == null){
            response = service.addAppointment(appointment);
        }
        System.out.println("response : " + response);
        return response;
    }

}
