package org.example;

import org.example.dao.FlightBuilder;
import org.example.model.Flight;
import org.example.service.FlightFilter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> flights1 = new FlightFilter(flights).filterDepartureBeforeDateNow();
        List<Flight> flights2 = new FlightFilter(flights).filterArrivalBeforeDeparture();


        System.out.println(flights);
        System.out.println(flights1);
        System.out.println(flights2);
    }

}
