package com.clinicware.service.api;

import com.clinicware.data.ClinicRepository;
import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Clinic;
import com.clinicware.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/newClinic")
@CrossOrigin("*")
public class ClinicRestController {

    private ClinicService clinicService;
    private ClinicRepository repo;

    @Autowired
    public ClinicRestController(ClinicService clinicService, ClinicRepository repo) {
        this.clinicService = clinicService;
        this.repo = repo;
    }

    @RequestMapping(path = "/{clinic}", headers = ("accept=*/*"), produces = "application/xml")
    public ResponseEntity<RegisterValidation> addNewClinic(@RequestBody Clinic clinic){
        ResponseEntity<RegisterValidation> response = null;
        System.out.println(clinic);
        System.out.println("dataSource " + repo.findClinicByClinicNameAndClinicPhoneAndAddress(clinic.getClinicName(), clinic.getClinicPhone(), clinic.getAddress()));
        if(repo.findClinicByClinicNameAndClinicPhoneAndAddress(clinic.getClinicName(), clinic.getClinicPhone(), clinic.getAddress()) == null){
            response = clinicService.addNewClinic(clinic);
        }
        System.out.println(response);
        return response;
    }
}
