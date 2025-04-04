package com.flightreg.flightreg.service;

import com.flightreg.flightreg.model.Flight;
import com.flightreg.flightreg.model.Seat;
import com.flightreg.flightreg.model.SeatType;
import com.flightreg.flightreg.repository.FlightRepository;
import com.flightreg.flightreg.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightRegService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    public List<Flight> getAllFlights(){
        List<Flight> flightList = new ArrayList<>();
        flightRepository.findAll().forEach(flightList::add);
        return flightList;
    }

    public Flight saveFlight(Flight flight){
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
        return flightRepository.save(builder.build());
    }

    public void initializeSeats(int flightId, int numberOfSeats) {
        List<Seat> seats = new ArrayList<Seat>();

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id: " + flightId));

        int seatsPerRow = 6;
        int totalRows = numberOfSeats / seatsPerRow;

        for (int i = 0; i < numberOfSeats; i++) {
            int rowNumber = (i / seatsPerRow) + 1;
            char seatLetter = (char) ('A' + (i % seatsPerRow));

            SeatType seatType;
            if (rowNumber == 1 || rowNumber == totalRows) {
                seatType = SeatType.NEAR_EXIT;
            } else if (seatLetter == 'A' || seatLetter == 'F') {
                seatType = SeatType.WINDOW;
            } else if (seatLetter == 'C' || seatLetter == 'D') {
                seatType = SeatType.AISLE;
            } else {
                seatType = SeatType.STANDARD;
            }

            Seat seat = Seat.builder()
                    .seatNumber(rowNumber)
                    .seatLetter(seatLetter)
                    .seatType(seatType)
                    .isOccupied(false)
                    .flight(flight).build();
            seats.add(seat);
        }

        seatRepository.saveAll(seats);
    }

    @PostConstruct
    public void initializeFlights() {
        // Using the builder pattern to initialize the flights
        Flight flight1 = Flight.builder()
                .destination("Los Angeles")
                .departurePoint("New York")
                .airLine("Delta Airlines")
                .departure(LocalDate.parse("2025-04-01"))
                .arrival(LocalDate.parse("2025-04-01"))
                .departureTime(LocalTime.parse("10:00"))
                .arrivalTime(LocalTime.parse("13:00"))
                .price(300.0)
                .availableSeats(150)
                .build();

        Flight flight2 = Flight.builder()
                .destination("Miami")
                .departurePoint("Chicago")
                .airLine("United Airlines")
                .departure(LocalDate.parse("2025-04-05"))
                .arrival(LocalDate.parse("2025-04-05"))
                .departureTime(LocalTime.parse("12:00"))
                .arrivalTime(LocalTime.parse("16:00"))
                .price(250.0)
                .availableSeats(120)
                .build();

        Flight flight3 = Flight.builder()
                .destination("Las Vegas")
                .departurePoint("San Francisco")
                .airLine("American Airlines")
                .departure(LocalDate.parse("2025-04-10"))
                .arrival(LocalDate.parse("2025-04-10"))
                .departureTime(LocalTime.parse("15:00"))
                .arrivalTime(LocalTime.parse("17:30"))
                .price(180.0)
                .availableSeats(100)
                .build();

        // Save the flights to the database
        flightRepository.save(flight1);
        flightRepository.save(flight2);
        flightRepository.save(flight3);

        // Initialize seats for each flight (assuming 100 seats per flight for now)
        initializeSeats(flight1.getId(), 60);
        initializeSeats(flight2.getId(), 60);
        initializeSeats(flight3.getId(), 60);
    }
}
