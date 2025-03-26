package com.flightreg.flightreg.service;

import com.flightreg.flightreg.model.Flight;
import com.flightreg.flightreg.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightRegService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights(){
        List<Flight> flightList = new ArrayList<>();
        this.flightRepository.findAll().forEach(flightList::add);
        return flightList;
    }
}
