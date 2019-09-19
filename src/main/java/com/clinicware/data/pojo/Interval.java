package com.clinicware.data.pojo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@Entity
@Table(name = "clnc_time_intervals")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Interval {

    public Interval(String clinicID, String doctorID, String date, String intervalStart, String intervalEnd, String intervalStatus, String period) throws ParseException {
        this.intervalId = "1";
        this.clinicId = clinicID;
        this.doctorId = doctorID;
        this.date = Date.valueOf(date);
        System.out.println(this.date);
        this.start = intervalStart;
        this.end = intervalEnd;
        this.intervalStatus = intervalStatus;
        this.period = period;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String intervalId;
    @Column(name = "interval_clinic_id")
    private String clinicId;
    @Column(name = "interval_doctor_id")
    private String doctorId;
    @Column(name = "interval_date")
    private Date date;
    @Column(name = "interval_start_time")
    private String start;
    @Column(name = "interval_end_time")
    private String end;
    @Column(name = "interval_status")
    private String intervalStatus;
    private String period;

}
