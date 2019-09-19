package com.clinicware.data;

import com.clinicware.data.pojo.Interval;
import org.springframework.data.repository.CrudRepository;

public interface IntervalRepository  extends CrudRepository<Interval, String> {

}
