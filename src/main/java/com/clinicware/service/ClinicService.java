package com.clinicware.service;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

@Service
public class ClinicService {

    private EntityManager entity;

    @Autowired
    public ClinicService(EntityManager entity) {
        this.entity = entity;
    }

//    public ResponseEntity<RegisterValidation> addNewClinic(Clinic clinic){
//        StoredProcedureQuery query = entity.createStoredProcedureQuery("INS_CLNC_CLINIC_INFO_P");
//
//    }
}
