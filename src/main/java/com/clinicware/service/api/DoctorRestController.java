package com.clinicware.service.api;

import com.clinicware.data.DoctorRepository;
import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Doctor;
import com.clinicware.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/newDoctor")
@CrossOrigin("*")
public class DoctorRestController {

    private DoctorService docService;
    private DoctorRepository repo;

    public DoctorRestController(DoctorService docService, DoctorRepository repo) {
        this.docService = docService;
        this.repo = repo;
    }

    @RequestMapping(path = "/{doctor}", headers = ("accept=*/*"), produces = "application/xml")
    public ResponseEntity<RegisterValidation> addNewDoctor(@RequestBody Doctor doctor){
        ResponseEntity<RegisterValidation> response  = null;
        System.out.println("here" + doctor);
        System.out.println(repo.findDoctorByEmail(doctor.getEmail()));
        if(repo.findDoctorByEmail(doctor.getEmail()) == null){
            response = docService.addNewDoctor(doctor);
        }
        System.out.println(response);
        return response;
    }
}
