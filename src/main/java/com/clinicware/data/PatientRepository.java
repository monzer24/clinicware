package com.clinicware.data;

import com.clinicware.data.pojo.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends CrudRepository<Patient, String> {

    Patient findPatientByEnglishPatientNameAndPatientPhone(String englishPatientName, String patientPhone);

    List<Patient> findAllByClinicID(int clinicID);

}
