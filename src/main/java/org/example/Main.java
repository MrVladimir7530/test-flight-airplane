package org.example;

import org.example.service.FlightBuilder;
import org.example.model.Flight;
import org.example.service.FlightFilterBuilderImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> flights1 = new FlightFilterBuilderImpl(flights).filterDepartureBeforeDateNow().build();
        List<Flight> flights2 = new FlightFilterBuilderImpl(flights).filterArrivalBeforeDeparture().build();
        List<Flight> flights3 = new FlightFilterBuilderImpl(flights).filterDifferenceBetweenArrivalAndDeparture(2).build();


        System.out.println("Все рейсы:\n" + flights);
        System.out.println("Рейсы, с фильтром на \"Вылет до текущего момента времени\":\n" + flights1);
        System.out.println("Рейсы, с фильтром на \"Сегменты с датой прилёта раньше даты вылета\":\n" + flights2);
        System.out.println("Рейсы, с фильтром на \"Перелеты, где общее время, проведённое на земле, превышает два часа\":\n" + flights3);
    }

}
