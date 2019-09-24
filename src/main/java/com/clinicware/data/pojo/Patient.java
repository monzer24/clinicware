package com.clinicware.data.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "clnc_patient_info")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Patient {

    public Patient(String arabicPatientName, String englishPatientName, String patientPhone, Date birthdate, int gender, int status, int bloodGroup, String spouseName) {
        this.arabicPatientName = arabicPatientName;
        this.englishPatientName = englishPatientName;
        this.patientPhone = patientPhone;
        this.birthDate = birthdate;
        this.gender = gender;
        this.status = status;
        this.bloodGroup = bloodGroup;
        this.spouseName = spouseName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "patient_name_a")
    private String arabicPatientName;
    @Column(name = "patient_name_e")
    private String englishPatientName;
    @Column(name = "patient_phone")
    private String patientPhone;
    @Column(name = "patient_birth_date")
    private Date birthDate;
    @Column(name = "patient_gender")
    private int gender;
    @Column(name = "marital_status")
    private int status;
    @Column(name = "blood_group")
    private int bloodGroup;
    @Column(name = "spouse_name")
    private String spouseName;


}
