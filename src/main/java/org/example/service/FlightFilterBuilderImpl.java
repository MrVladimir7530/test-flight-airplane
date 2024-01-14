package org.example.service;

import org.example.model.Flight;
import org.example.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterBuilderImpl implements FlightFilterBuilder {
    List<Flight> flights;

    public FlightFilterBuilderImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }


    public FlightFilterBuilder filterDepartureBeforeDateNow() {
        flights.removeIf(o -> o.getSegments().stream()
                .allMatch(segment -> segment.getDepartureDate()
                        .isBefore(LocalDateTime.now())));
        return this;
    }

    public FlightFilterBuilder filterArrivalBeforeDeparture() {
        flights.removeIf(o -> o.getSegments().stream()
                .allMatch(segment -> segment.getArrivalDate()
                        .isBefore(segment.getDepartureDate())));
        return this;
    }

    public FlightFilterBuilder filterDifferenceBetweenArrivalAndDeparture(int timeBetweenFlightsInHour) {
        flights.removeIf(x -> {
            List<Segment> segments = x.getSegments();
            if (segments.size() < 2) {
                return false;
            }

            for (int i = 1; i < segments.size(); i++) {
                LocalDateTime arrivalDate = segments.get(i - 1).getArrivalDate();
                LocalDateTime departureDate = segments.get(i).getDepartureDate();

                departureDate = departureDate.minusHours(timeBetweenFlightsInHour);
                if (departureDate.isAfter(arrivalDate)) {
                    return true;
                }
            }
            return false;
        });
        return this;
    }

    public List<Flight> build() {
        return flights;
    }

}
