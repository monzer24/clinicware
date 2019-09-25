package com.clinicware.service;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;

@Service
public class PatientService {

    @Autowired
    private EntityManager entity;

    public ResponseEntity<RegisterValidation> addNewPatient(Patient patient) {
        StoredProcedureQuery query = entity.createStoredProcedureQuery("INS_CLNC_PATIENT_INFO_P")
                .registerStoredProcedureParameter("p_clinic_id", Integer.class, ParameterMode.IN)
                .setParameter("p_clinic_id", patient.getClinicID())

                .registerStoredProcedureParameter("p_patient_name_a", String.class, ParameterMode.IN)
                .setParameter("p_patient_name_a", patient.getArabicPatientName())

                .registerStoredProcedureParameter("p_patient_name_e", String.class, ParameterMode.IN)
                .setParameter("p_patient_name_e", patient.getEnglishPatientName())

                .registerStoredProcedureParameter("p_patient_phone", String.class, ParameterMode.IN)
                .setParameter("p_patient_phone", patient.getPatientPhone())

                .registerStoredProcedureParameter("p_patient_birth_date", Date.class, ParameterMode.IN)
                .setParameter("p_patient_birth_date", patient.getBirthDate())

                .registerStoredProcedureParameter("p_patient_gender", Integer.class, ParameterMode.IN)
                .setParameter("p_patient_gender", patient.getGender())

                .registerStoredProcedureParameter("p_marital_status", Integer.class, ParameterMode.IN)
                .setParameter("p_marital_status", patient.getStatus())

                .registerStoredProcedureParameter("p_blood_group", Integer.class, ParameterMode.IN)
                .setParameter("p_blood_group", patient.getBloodGroup())

                .registerStoredProcedureParameter("P_MANUAL_FILE_NO", String.class, ParameterMode.IN)
                .setParameter("P_MANUAL_FILE_NO", "file-001")

                .registerStoredProcedureParameter("p_spouse_name", String.class, ParameterMode.IN)
                .setParameter("p_spouse_name", patient.getSpouseName())

                .registerStoredProcedureParameter("p_entry_user", Integer.class, ParameterMode.IN)
                .setParameter("p_entry_user", 1)

                .registerStoredProcedureParameter("p_entry_device", String.class, ParameterMode.IN)
                .setParameter("p_entry_device", "PC-001")

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
