package com.flightreg.flightreg.service;

import com.flightreg.flightreg.model.Flight;
import com.flightreg.flightreg.model.Reservation;
import com.flightreg.flightreg.model.Seat;
import com.flightreg.flightreg.model.User;
import com.flightreg.flightreg.repository.ReservationRepository;
import com.flightreg.flightreg.repository.SeatRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;

    @Autowired
    private SeatRepository seatRepository;

    public List<Reservation> getUserReservations(int userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Reservation reserveSeat(User user, Flight flight, List<Seat> seats) {
        for (Seat seat : seats) {
            if (seat.isOccupied()){
                throw new IllegalStateException("Seat is occupied");
            }
        }

        for (Seat seat : seats) {
            seat.setOccupied(true);
            seatRepository.save(seat);
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .flight(flight)
                .seats(seats).build();

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(int id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        for (Seat seat : reservation.getSeats()) {
            seat.setOccupied(false);
            seatRepository.save(seat);
        }

        reservationRepository.delete(reservation);

    }
}
