package com.clinicware.data.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clnc_clinic_info")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Clinic {

    public Clinic(String clinicName, String clinicPhone, String ownerPhone, String address, String city) {
        this.id = "";
        this.clinicName = clinicName;
        this.clinicPhone = clinicPhone;
        this.ownerPhone = ownerPhone;
        this.address = address;
        this.city = city;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "clinic_name")
    private String clinicName;
    @Column(name = "clinic_phone01")
    private String clinicPhone;
    @Column(name = "clinic_phone02")
    private String ownerPhone;
    @Column(name="clinic_adress")
    private String address;
    @Column(name = "city")
    private String city;

}
