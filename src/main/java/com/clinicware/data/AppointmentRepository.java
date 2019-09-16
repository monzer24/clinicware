package com.clinicware.data;

import com.clinicware.data.pojo.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, String> {

    List<Appointment> findAll();

}
