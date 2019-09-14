package com.clinicware.data.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clnc_doctor_info")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "doctor_name_a")
    private String arabicName;
    @Column(name = "doctor_name_e")
    private String englishName;
    @Column(name = "doctor_phone1")
    private String phoneNo1;
    @Column(name = "doctor_phone2")
    private String phoneNo2;
    @Column(name = "doctor_email")
    private String email;
    @Column(name = "clnc_id")
    private int clinic;

    public Doctor(String arabicName, String englishName, String phoneNo1, String phoneNo2, String email, String clinic) {
        System.out.println(clinic);
        this.arabicName = arabicName;
        this.englishName = englishName;
        this.phoneNo1 = phoneNo1;
        this.phoneNo2 = phoneNo2;
        this.email = email;
        this.clinic = Integer.parseInt(clinic);
    }

}
