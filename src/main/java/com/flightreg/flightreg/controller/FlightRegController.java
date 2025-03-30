package com.flightreg.flightreg.controller;

import com.flightreg.flightreg.model.*;
import com.flightreg.flightreg.service.FlightRegService;
import com.flightreg.flightreg.service.ReservationService;
import com.flightreg.flightreg.service.SeatSuggestionService;
import com.flightreg.flightreg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class FlightRegController {
    @Autowired
    private FlightRegService flightRegService;

    @Autowired
    private SeatSuggestionService seatSuggestionService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;


    @GetMapping("/flights")
    public List<Flight> getFlights(){
        return flightRegService.getAllFlights();
    }

    @PostMapping("/flights")
    public void saveFlight(@RequestBody Flight flight){
        Flight savedFlight = flightRegService.saveFlight(flight);

        flightRegService.initializeSeats(savedFlight.getId(),90);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {
        System.out.println("grab");
        User newUser = userService.createUser(username, email, password);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam String username){
        return userService.getUser(username);
    }

    @GetMapping("/seats")
    public List<Seat> getUsers(@RequestParam int flightId){
        return seatSuggestionService.getSeatsByFlightId(flightId);
    }

    @GetMapping("/seats/suggestions")
    public ResponseEntity<List<Seat>> recommendSeats(
            @RequestParam int flightId,
            @RequestParam int numOfSeats,
            @RequestParam(required = false) List<SeatType> seatTypes) {

        List<Seat> suggestedSeats = seatSuggestionService.recommendSeats(flightId, numOfSeats, seatTypes);

        return ResponseEntity.ok(suggestedSeats);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getUserReservations(int userId){
        return ResponseEntity.ok(reservationService.getUserReservations(userId));
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> reserveSeats(
            @RequestParam int userId,
            @RequestParam int flightId,
            @RequestParam List<Integer> seatIds) {

        User user = new User();  // TODO: Fetch from DB
        Flight flight = new Flight();  // TODO: Fetch from DB

        List<Seat> seats = seatSuggestionService.getAllSeats()
                .stream()
                .filter(seat -> seatIds.contains(seat.getId()))
                .toList();

        // Reserve seats
        Reservation reservation = reservationService.reserveSeat(user, flight, seats);
        return ResponseEntity.ok(reservation);
    }

    // Cancel a reservation
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }





}
