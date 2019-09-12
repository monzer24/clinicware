package com.clinicware.data;

import com.clinicware.data.pojo.Clinic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends CrudRepository<Clinic, String> {

    Clinic findClinicByClinicNameAndClinicPhoneAndAddress(String clinicName, String clinicPhone, String Address);
}
