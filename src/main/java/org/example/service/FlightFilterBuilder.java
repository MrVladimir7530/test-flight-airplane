package org.example.service;

import org.example.model.Flight;

import java.util.List;

public interface FlightFilterBuilder {
    FlightFilterBuilder filterDepartureBeforeDateNow();

    FlightFilterBuilder filterArrivalBeforeDeparture();

    FlightFilterBuilder filterDifferenceBetweenArrivalAndDeparture(int timeBetweenFlightsInHour);

    List<Flight> build();
}
