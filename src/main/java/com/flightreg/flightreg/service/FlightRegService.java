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
        flightRepository.findAll().forEach(flightList::add);
        return flightList;
    }

    public void saveFlight(Flight flight){
        var builder = Flight.builder()
                .destination(flight.getDestination())
                        .departurePoint(flight.getDeparturePoint())
                                .airLine(flight.getAirLine())
                                        .departure(flight.getDeparture())
                                                .arrival(flight.getArrival())
                                                        .departureTime(flight.getDepartureTime())
                                                                .arrivalTime(flight.getArrivalTime())
                                                                        .price(flight.getPrice())
                                                                                .availableSeats(flight.getAvailableSeats());
        flightRepository.save(builder.build());
    }

}
