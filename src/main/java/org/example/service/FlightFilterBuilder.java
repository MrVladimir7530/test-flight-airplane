package org.example.service;

import org.example.model.Flight;

import java.util.List;

public interface FlightFilterBuilder {
    /**
     * фильтрует Flight, так чтобы вылетов не было
     * раньше сегодняшнего времени
     */
    FlightFilterBuilder filterDepartureBeforeDateNow();
    /**
     * фильтрует Flight, так чтобы сегментов с
     * датой прилёта раньше даты вылета не было.
     */
    FlightFilterBuilder filterArrivalBeforeDeparture();

    /**
     * фильтрует Flight, так чтобы перелетов, где общее время,
     * проведённое на земле, не превышало больше timeBetweenFlightsInHour
     * (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним).
     * @timeBetweenFlightsInHour: время проведенное на земле
     */
    FlightFilterBuilder filterDifferenceBetweenArrivalAndDeparture(int timeBetweenFlightsInHour);

    /**
     * делает построение из FlightFilterBuilder в Flights
     */
    List<Flight> build();
}
