package com.flightreg.flightreg.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "flight")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String departurePoint;

    @Column(nullable = false)
    private String airLine;

    @Column(nullable = false)
    private LocalDate departure;

    @Column(nullable = false)
    private LocalDate arrival;

    @Column(nullable = false)
    private LocalTime departureTime;

    @Column(nullable = false)
    private LocalTime arrivalTime;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int availableSeats;

}



