package com.flightreg.flightreg.repository;

import com.flightreg.flightreg.model.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

}
