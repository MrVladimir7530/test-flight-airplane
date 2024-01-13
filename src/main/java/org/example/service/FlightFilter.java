package org.example.service;

import org.example.model.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilter {
    List<Flight> flights;

    public FlightFilter(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }


    public List<Flight> filterDepartureBeforeDateNow() {
        flights.removeIf(o -> o.getSegments().stream()
                .allMatch(segment -> segment.getDepartureDate()
                        .isBefore(LocalDateTime.now())));
        return flights;
    }

    public List<Flight> filterArrivalBeforeDeparture() {
        flights.removeIf(o -> o.getSegments().stream()
                .allMatch(segment -> segment.getArrivalDate()
                        .isBefore(segment.getDepartureDate())));
        return flights;
    }

}
