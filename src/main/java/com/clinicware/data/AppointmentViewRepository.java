package com.clinicware.data;

import com.clinicware.data.pojo.Appointment;
import com.clinicware.data.pojo.AppointmentView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentViewRepository extends CrudRepository<AppointmentView, String> {

    List<AppointmentView> findAll();

    AppointmentView findByPatientId(String patientId);

    AppointmentView findEnglishNameByPatientId(String patientId);

}
