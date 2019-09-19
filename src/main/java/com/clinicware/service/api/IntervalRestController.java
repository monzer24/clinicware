package com.clinicware.service.api;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Doctor;
import com.clinicware.data.pojo.Interval;
import com.clinicware.service.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping(path = "/interval")
@CrossOrigin("*")
public class IntervalRestController {

    private final IntervalService service;

    public IntervalRestController(IntervalService service) {
        this.service = service;
    }

    @RequestMapping(path = "/{interval}", headers = ("accept=*/*"), produces = "application/xml")
    public ResponseEntity<Map<String, String>> updateInterval(@RequestBody Interval interval) throws ParseException {
        ResponseEntity<Map<String, String>> response  = null;
        System.out.println("here" + interval);
        response = service.updateInterval(interval);

        return response;
    }
}
