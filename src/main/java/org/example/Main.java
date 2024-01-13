package org.example;

import org.example.dao.FlightBuilder;
import org.example.model.Flight;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.printf(flights.toString());
    }
}
