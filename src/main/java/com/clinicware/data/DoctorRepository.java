package com.clinicware.data;

import com.clinicware.data.pojo.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, String> {

    Doctor findDoctorByEmail(String email);

}
