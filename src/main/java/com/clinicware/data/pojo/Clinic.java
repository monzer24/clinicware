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
    private String clinicName;
    private String clinicPhone;
    private String ownerPhone;
    private String address;
    private String city;

}
