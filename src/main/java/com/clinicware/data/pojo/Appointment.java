package com.clinicware.data.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "clnc_appointments")
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class Appointment {

    @Id
    private String id;
    @Column(name = "interval_id")
    private String intervalID;
    @Column(name = "patient_id")
    private String patientID;
    @Column(name = "appt_status")
    private String appointmentStatus;
    @Column(name = "patient_complaint")
    private String patientComplaint;
    @Column(name = "cancel_reason")
    private String cancelReason;

}
