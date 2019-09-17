package com.clinicware.data.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "clnc_time_intervals")
public class Interval {

    public Interval(String clinicID, String doctorID, String date, String intervalStart, String intervalEnd, String intervalStatus, String period) {
        this.clinicID = clinicID;
        this.doctorID = doctorID;
        this.date = date;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.intervalStatus = intervalStatus;
        this.period = period;
    }

    @Id
    private String intervalID;
    @Column(name = "interval_clinic_id")
    private String clinicID;
    @Column(name = "interval_doctor_id")
    private String doctorID;
    @Column(name = "interval_date")
    private String date;
    @Column(name = "interval_start_time")
    private String intervalStart;
    @Column(name = "interval_end_time")
    private String intervalEnd;
    @Column(name = "interval_status")
    private String intervalStatus;
    private String period;

}
