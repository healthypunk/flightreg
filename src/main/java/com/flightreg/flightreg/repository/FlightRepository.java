package com.flightreg.flightreg.repository;

import com.flightreg.flightreg.model.Flight;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Integer> {

    public List<Flight> findByPrice (Double price);

    public List<Flight> findByDestination(String destination);

    public List<Flight> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
}
