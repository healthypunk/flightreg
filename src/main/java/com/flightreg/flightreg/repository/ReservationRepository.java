package com.flightreg.flightreg.repository;

import com.flightreg.flightreg.model.Reservation;
import com.flightreg.flightreg.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    public List<Reservation> findByUserId(int userId);
}
