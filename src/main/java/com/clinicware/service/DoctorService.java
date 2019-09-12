package com.clinicware.service;

import com.clinicware.data.DoctorRepository;
import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Doctor;
import org.hibernate.jpa.spi.ParameterRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class DoctorService {

    private EntityManager entity;

    public DoctorService(EntityManager entity) {
        this.entity = entity;
    }


    public ResponseEntity<RegisterValidation> addNewDoctor(Doctor doctor){

        StoredProcedureQuery query = entity.createStoredProcedureQuery("INS_CLNC_DOCTOR_INFO_P")
                .registerStoredProcedureParameter("P_CLNC_ID",  Integer.class, ParameterMode.IN)
                .setParameter("P_CLNC_ID", doctor.getClinic())
                .registerStoredProcedureParameter("P_DOCTOR_NAME_A", String.class, ParameterMode.IN)
                .setParameter("P_DOCTOR_NAME_A", doctor.getArabicName())
                .registerStoredProcedureParameter("P_DOCTOR_NAME_E", String.class, ParameterMode.IN)
                .setParameter("P_DOCTOR_NAME_E", doctor.getEnglishName())
                .registerStoredProcedureParameter("P_DOCTOR_PHONE1", String.class, ParameterMode.IN)
                .setParameter("P_DOCTOR_PHONE1", doctor.getPhoneNo1())
                .registerStoredProcedureParameter("P_DOCTOR_PHONE2", String.class, ParameterMode.IN)
                .setParameter("P_DOCTOR_PHONE2", doctor.getPhoneNo2())
                .registerStoredProcedureParameter("P_DOCTOR_EMAIL", String.class, ParameterMode.IN)
                .setParameter("P_DOCTOR_EMAIL", doctor.getEmail())
                .registerStoredProcedureParameter("P_NOTES1", String.class, ParameterMode.IN)
                .setParameter("P_NOTES1", "")
                .registerStoredProcedureParameter("P_NOTES2", String.class, ParameterMode.IN)
                .setParameter("P_NOTES2", "")
                .registerStoredProcedureParameter("P_NOTES3", String.class, ParameterMode.IN)
                .setParameter("P_NOTES3", "")
                .registerStoredProcedureParameter("P_ENTRY_USER", Integer.class, ParameterMode.IN)
                .setParameter("P_ENTRY_USER", 1)
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
