package com.flightreg.flightreg.controller;

import com.flightreg.flightreg.model.Flight;
import com.flightreg.flightreg.service.FlightRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightRegController {
    @Autowired
    private FlightRegService flightRegService;

    @GetMapping
    public List<Flight> getFlights(){
        return flightRegService.getAllFlights();
    }

    @PostMapping
    public void saveFlight(@RequestBody Flight flight){
        flightRegService.saveFlight(flight);
    }



}
