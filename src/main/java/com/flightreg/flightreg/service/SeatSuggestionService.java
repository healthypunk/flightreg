package com.flightreg.flightreg.service;

import com.flightreg.flightreg.model.Seat;
import com.flightreg.flightreg.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatSuggestionService {
    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats(){
        List<Seat> seatList = new ArrayList<>();
        this.seatRepository.findAll().forEach(seatList::add);
        return seatList;
    }

}
