package com.clinicware.data.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "clnc_interval_vw")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class AppointmentView {

    public AppointmentView(String date, String start, String end, String status, String appointmentId, String patientId, String englishName) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.status = Integer.parseInt(status);
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.englishName = englishName;
    }

    @Id
    @Column(name = "CTI_ID")
    private String ctiID;
    @Column(name = "CTI_INTERVAL_DATE")
    private String date;
    @Column(name = "CTI_INTERVAL_START_TIME")
    private String start;
    @Column(name = "CTI_INTERVAL_END_TIME")
    private String end;
    @Column(name = "CTI_INTERVAL_STATUS")
    private int status;
    @Column(name = "APT_ID")
    private String appointmentId;
    @Column(name = "APT_PATIENT_ID")
    private String patientId;
    @Column(name = "APT_PATIENT_NAME_E")
    private String englishName;

}
