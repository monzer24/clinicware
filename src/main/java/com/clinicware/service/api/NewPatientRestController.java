package com.clinicware.service.api;

import com.clinicware.data.PatientRepository;
import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Patient;
import com.clinicware.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/newPatient")
@CrossOrigin("*")
public class NewPatientRestController {

    private final PatientService service;
    private PatientRepository repo;

    public NewPatientRestController(PatientService service, PatientRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @RequestMapping(path = "/{patient}", headers = ("accept=*/*"), produces = "application/xml")
    public ResponseEntity<RegisterValidation> addNewPatient(@RequestBody Patient patient){
        ResponseEntity<RegisterValidation> response = null;
        System.out.println("newPatient Rest Controller : " + patient);
        if(repo.findPatientByEnglishPatientNameAndPatientPhone(patient.getEnglishPatientName(), patient.getPatientPhone()) == null){
            response = service.addNewPatient(patient);
        }
        return response;
    }

}
