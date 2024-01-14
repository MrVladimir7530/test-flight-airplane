package service;

import org.example.model.Flight;
import org.example.model.Segment;
import org.example.service.FlightFilterBuilder;
import org.example.service.FlightFilterBuilderImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightFilterBuilderImplTest {
    FlightFilterBuilder flightFilterBuilder;
    List<Flight> flights;
    Flight flight1;
    Flight flight2;
    Flight flight3;
    Flight flight4;

    LocalDateTime dep1 = LocalDateTime.now().plusDays(1);
    LocalDateTime arr1 = LocalDateTime.now().plusDays(1).plusHours(2);

    LocalDateTime dep2 = LocalDateTime.now().minusDays(2);
    LocalDateTime arr2 = LocalDateTime.now().plusHours(2);

    LocalDateTime dep3 = LocalDateTime.now().plusDays(3);
    LocalDateTime arr3 = LocalDateTime.now().plusDays(2).plusHours(2);

    LocalDateTime dep4 = LocalDateTime.now().plusDays(2).plusHours(3);
    LocalDateTime arr4 = LocalDateTime.now().plusDays(2).plusHours(5);

    Segment segment1 = new Segment(dep1, arr1);
    Segment segment2 = new Segment(dep2, arr2);
    Segment segment3 = new Segment(dep3, arr3);
    Segment segment4 = new Segment(dep4, arr4);



    @Before
    public void init() {
        List<Segment> segments1 = new ArrayList<>(List.of(segment1));
        List<Segment> segments2 = new ArrayList<>(List.of(segment1, segment2));
        List<Segment> segments3 = new ArrayList<>(List.of(segment1, segment3));
        List<Segment> segments4 = new ArrayList<>(List.of(segment1, segment4));

        flight1 = new Flight(segments1);
        flight2 = new Flight(segments2);
        flight3 = new Flight(segments3);
        flight4 = new Flight(segments4);

        flights = new ArrayList<>(List.of(flight1, flight2, flight3, flight4));

        flightFilterBuilder = new FlightFilterBuilderImpl(flights);
    }

    @Test
    public void filterDepartureBeforeDateNowTest() {
        List<Flight> expectedFlights = new ArrayList<>(List.of(flight1, flight3, flight4));
        List<Flight> expectedBuild = new FlightFilterBuilderImpl(expectedFlights).build();
        List<Flight> resultBuild = flightFilterBuilder.filterDepartureBeforeDateNow().build();

        assertEquals(expectedBuild, resultBuild);
    }

    @Test
    public void filterArrivalBeforeDepartureTest() {
        List<Flight> expectedFlights = new ArrayList<>(List.of(flight1, flight2, flight4));
        List<Flight> expectedBuild = new FlightFilterBuilderImpl(expectedFlights).build();
        List<Flight> resultBuild = flightFilterBuilder.filterArrivalBeforeDeparture().build();

        assertEquals(expectedBuild, resultBuild);
    }

    @Test
    public void filterDifferenceBetweenArrivalAndDepartureTest() {
        List<Flight> expectedFlights = new ArrayList<>(List.of(flight1, flight2));
        List<Flight> expectedBuild = new FlightFilterBuilderImpl(expectedFlights).build();
        List<Flight> resultBuild = flightFilterBuilder.filterDifferenceBetweenArrivalAndDeparture(2).build();

        assertEquals(expectedBuild, resultBuild);
    }


}
