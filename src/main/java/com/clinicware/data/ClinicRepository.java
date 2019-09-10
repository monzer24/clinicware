package com.clinicware.data;

import com.clinicware.data.pojo.Clinic;
import org.springframework.data.repository.CrudRepository;

public interface ClinicRepository extends CrudRepository<Clinic, String> {

    Clinic findClinicByClinicNameAndClinicPhoneAndAddress(String clinicName, String clinicPhone, String Address);
}
