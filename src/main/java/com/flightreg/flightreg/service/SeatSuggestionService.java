package com.flightreg.flightreg.service;

import com.flightreg.flightreg.model.Seat;
import com.flightreg.flightreg.model.SeatType;
import com.flightreg.flightreg.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SeatSuggestionService {
    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        List<Seat> seatList = new ArrayList<>();
        this.seatRepository.findAll().forEach(seatList::add);
        return seatList;
    }

    public List<Seat> getSeatsByFlightId(int flightId) {
        return seatRepository.getSeatByFlightIdAndIsOccupiedFalse(flightId);
    }

    private boolean isAdjacent(List<Seat> seats) {
        for (int i = 0; i < seats.size() - 1; i++) {
            Seat curSeat = seats.get(i);
            Seat nextSeat = seats.get(i + 1);

            if (curSeat.getSeatNumber() != nextSeat.getSeatNumber() || (int) curSeat.getSeatLetter() != (int) nextSeat.getSeatLetter()) {
                return false;
            }
        }
        return true;
    }

    public List<Seat> recommendSeats(int flightId, int numOfSeats, List<SeatType> seatTypes) {
        List<Seat> filteredSeats = new ArrayList<>(seatRepository.getSeatByFlightIdAndIsOccupiedFalse(flightId)
                .stream()
                .filter(seat -> seatTypes.contains(seat.getSeatType())).toList());

        filteredSeats.sort(Comparator.comparingInt(Seat::getSeatNumber).thenComparing(Seat::getSeatLetter));

        for (int i = 0; i < filteredSeats.size() - numOfSeats; i++) {
            List<Seat> multSeats = filteredSeats.subList(i, i + numOfSeats);
            if (isAdjacent(multSeats)) {
                return multSeats;
            }
        }

        return filteredSeats.stream().filter(seat -> !seat.isOccupied()).sorted(Comparator.comparingInt(Seat::getSeatNumber)).limit(numOfSeats).toList();
    }

}
