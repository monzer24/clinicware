package com.clinicware.service;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Service
public class AppointmentService {
    // TODO:: Appointment Service and AppointmentRestController

    @Autowired
    private EntityManager entity;

    public ResponseEntity<RegisterValidation> addAppointment(Appointment appointment){
        StoredProcedureQuery query = entity.createStoredProcedureQuery("INS_CLNC_APPOINTMENTS_P")
                .registerStoredProcedureParameter("p_interval_id", Integer.class, ParameterMode.IN)
                .setParameter("p_interval_id", Integer.valueOf(appointment.getIntervalID()))

                .registerStoredProcedureParameter("p_patient_id", Integer.class, ParameterMode.IN)
                .setParameter("p_patient_id", Integer.valueOf(appointment.getPatientID()))

                .registerStoredProcedureParameter("p_patient_complaint", String.class, ParameterMode.IN)
                .setParameter("p_patient_complaint", appointment.getPatientComplaint())

                .registerStoredProcedureParameter("p_cancel_reason", String.class, ParameterMode.IN)
                .setParameter("p_cancel_reason", appointment.getCancelReason())

                .registerStoredProcedureParameter("p_notes1", String.class, ParameterMode.IN)
                .setParameter("p_notes1", "")

                .registerStoredProcedureParameter("p_notes2", String.class, ParameterMode.IN)
                .setParameter("p_notes2", "")

                .registerStoredProcedureParameter("p_notes3", String.class, ParameterMode.IN)
                .setParameter("p_notes3", "")

                .registerStoredProcedureParameter("p_entry_user", Integer.class, ParameterMode.IN)
                .setParameter("p_entry_user", 1)

                .registerStoredProcedureParameter("p_entry_device", String.class, ParameterMode.IN)
                .setParameter("p_entry_device", "PC-001")

                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_trx_status", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_error_message", String.class, ParameterMode.OUT);

        query.execute();

        RegisterValidation validation = new RegisterValidation();
        validation.setId((Integer) query.getOutputParameterValue("p_id"));
        validation.setStatus((Integer) query.getOutputParameterValue("p_trx_status"));
        validation.setErrorMessage((String) query.getOutputParameterValue("p_error_message"));

        return new ResponseEntity<>(validation, HttpStatus.CREATED);

    }
}
