package com.clinicware.service;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class ClinicService {

    private EntityManager entity;

    @Autowired
    public ClinicService(EntityManager entity) {
        this.entity = entity;
    }

    public ResponseEntity<RegisterValidation> addNewClinic(Clinic clinic){
        StoredProcedureQuery query = entity.createStoredProcedureQuery("INS_CLNC_CLINIC_INFO_P")
                .registerStoredProcedureParameter("P_CLINIC_NAME", String.class, ParameterMode.IN)
                .setParameter("P_CLINIC_NAME", clinic.getClinicName())
                .registerStoredProcedureParameter("P_CLINIC_ADRESS", String.class, ParameterMode.IN)
                .setParameter("P_CLINIC_ADRESS", clinic.getAddress())
                .registerStoredProcedureParameter("P_CITY", String.class, ParameterMode.IN)
                .setParameter("P_CITY", clinic.getCity())
                .registerStoredProcedureParameter("P_CLINIC_PHONE01",String.class, ParameterMode.IN)
                .setParameter("P_CLINIC_PHONE01", clinic.getClinicPhone())
                .registerStoredProcedureParameter("P_CLINIC_PHONE02",String.class, ParameterMode.IN)
                .setParameter("P_CLINIC_PHONE02", clinic.getOwnerPhone())
                .registerStoredProcedureParameter("P_ENTRY_USER", Integer.class, ParameterMode.IN)
                .setParameter("P_ENTRY_USER", 0)
                .registerStoredProcedureParameter("P_ENTRY_DEVICE", String.class, ParameterMode.IN)
                .setParameter("P_ENTRY_DEVICE", "all")
                .registerStoredProcedureParameter("P_ID", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("P_TRX_STATUS", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("P_ERROR_MESSAGE", String.class, ParameterMode.OUT);

        query.execute();

        RegisterValidation validation = new RegisterValidation();
        validation.setId((Integer) query.getOutputParameterValue("P_ID"));
        validation.setStatus((Integer) query.getOutputParameterValue("P_TRX_STATUS"));
        validation.setErrorMessage((String) query.getOutputParameterValue("P_ERROR_MESSAGE"));
        return new ResponseEntity<>(validation, HttpStatus.CREATED);

    }
}
