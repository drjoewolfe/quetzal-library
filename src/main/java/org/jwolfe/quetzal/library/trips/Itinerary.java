package org.jwolfe.quetzal.library.trips;

import org.jwolfe.quetzal.library.trips.Trip;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    protected List<Trip> trips;

    public List<Trip> loadTrips() {
        trips = new ArrayList<>();
        return trips;
    }

    public void tourAll() {
        var trips = loadTrips();
        tour(trips);
    }

    public void tour(List<Trip> trips){
        trips.forEach(t -> t.tour());
    }

    public void tour(Trip trip) {
        trip.tour();
    }
}
