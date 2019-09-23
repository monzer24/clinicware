package com.clinicware.service;

import com.clinicware.data.RegisterValidation;
import com.clinicware.data.pojo.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class IntervalService {

    @Autowired
    private EntityManager entity;

    public ResponseEntity<Map<String, String>> updateInterval(Interval interval) throws ParseException {
        long start = new SimpleDateFormat("hh:mm").parse(interval.getStart()).getTime()+ 1000*2*60*60;

        long end = new SimpleDateFormat("hh:mm").parse(interval.getEnd()).getTime()+ 1000*2*60*60;
        Calendar c = Calendar.getInstance();
        c.setTime(interval.getDate());
        c.add(Calendar.DATE, 1);
        System.out.println(new Time(start) + " " + new Time(end ));

        StoredProcedureQuery query = entity.createStoredProcedureQuery("CREATE_APPT_INTREVALS")
                .registerStoredProcedureParameter("P_INTERVAL_CLINIC_ID", Integer.class, ParameterMode.IN)
                .setParameter("P_INTERVAL_CLINIC_ID", Integer.parseInt(interval.getClinicId()))

                .registerStoredProcedureParameter("P_INTERVAL_DOCTOR_ID", Integer.class, ParameterMode.IN)
                .setParameter("P_INTERVAL_DOCTOR_ID", Integer.parseInt(interval.getDoctorId()))

                .registerStoredProcedureParameter("P_INTERVAL_DATE", Date.class, ParameterMode.IN)
                .setParameter("P_INTERVAL_DATE", c.getTime())

                .registerStoredProcedureParameter("P_INTERVAL_START_TIME", Time.class, ParameterMode.IN)
                .setParameter("P_INTERVAL_START_TIME",new Time(start) )

                .registerStoredProcedureParameter("P_INTERVAL_END_TIME", Time.class, ParameterMode.IN)
                .setParameter("P_INTERVAL_END_TIME", new Time(end))

                .registerStoredProcedureParameter("P_INTERVAL_LENGTH", Integer.class, ParameterMode.IN)
                .setParameter("P_INTERVAL_LENGTH", Integer.parseInt(interval.getPeriod()))

                .registerStoredProcedureParameter("P_ENTRY_USER", Integer.class, ParameterMode.IN)
                .setParameter("P_ENTRY_USER", 1)

                .registerStoredProcedureParameter("P_ENTRY_DEVICE", String.class, ParameterMode.IN)
                .setParameter("P_ENTRY_DEVICE", "all")

                .registerStoredProcedureParameter("V_TRX_STATUS", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("V_ERROR_MESSAGE", String.class, ParameterMode.OUT);

        query.execute();

        Map<String, String> result = new HashMap<>();
        result.put("trxResult", (String) query.getOutputParameterValue("V_TRX_STATUS").toString());
        result.put("errorMessage", (String) query.getOutputParameterValue("V_ERROR_MESSAGE"));

        return new ResponseEntity<Map<String, String>>(result, HttpStatus.ACCEPTED);
    }

}
