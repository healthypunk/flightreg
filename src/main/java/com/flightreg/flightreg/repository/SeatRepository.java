package com.flightreg.flightreg.repository;

import com.flightreg.flightreg.model.Flight;
import com.flightreg.flightreg.model.Seat;
import com.flightreg.flightreg.model.SeatType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeatRepository extends CrudRepository<Seat, Integer> {
    public List<Seat> getSeatBySeatType(SeatType seatType);

    public List<Seat> getSeatsBySeatLetterAndSeatNumber(String seatLetter, int seatNumber);

    public List<Seat> getSeatByFlightIdAndIsOccupiedFalse(int flightId);

}
