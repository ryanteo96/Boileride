import com.google.maps.*;
import com.google.maps.model.Duration;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.*;

public class RideOfferSearch {
    private String user;
    private String origin;
    private String destination;
    private DateTime departure;
    private Period duration;
    private long distance;
    private int price;
    private RidePreferences ridePreferences;

    public RideOfferSearch() {

    }
    public RideOfferSearch(String origin, String destination, Period duration) {
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
    }

    public String getUser() {
        return user;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public DateTime getDeparture() {
        return departure;
    }

    public Period getDuration() {
        return duration;
    }

    public long getDistance() {
        return distance;
    }

    public int getPrice() {
        return price;
    }

    public RidePreferences getRidePreferences() {
        return ridePreferences;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDeparture(DateTime departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setDuration(Period duration) {
        this.duration = duration;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRidePreferences(RidePreferences ridePreferences) {
        this.ridePreferences = ridePreferences;
    }

    public String toString() {
        return "Origin: " + origin + ", Destination: " + destination + ", Duration: " + String.valueOf(duration.getMillis());
    }

    public List<Trip> search(String origin, String destination) {
        List<Trip> ret = new ArrayList<>();
        PriorityQueue<Temptrip> heap = new PriorityQueue<>((a, b)->b.total - a.total);
        FakeDB db = new FakeDB();
        Temptrip start = new Temptrip();
        start.trip = new Trip();
        start.trip.setRides(new LinkedList<>());
        start.trip.getRides().add(new RideOfferSearch("", origin, new Period(0)));
        heap.add(start);
        while (!heap.isEmpty()) {
            Temptrip curr = heap.poll();
            if (curr.trip.getRides().getLast().destination.equals(destination)) {
                ret.add(curr.trip);
                continue;
            }
            for (RideOfferSearch r : db.ridesFrom(curr.trip.getRides().getLast().destination)) {
                Temptrip t = new Temptrip();
                t.trip = new Trip();
                t.trip.setRides(new LinkedList<>());
                for (RideOfferSearch a : curr.trip.getRides()) {
                    t.trip.getRides().add(a);
                }

                t.trip.getRides().add(r);
                t.trip.setDuration(curr.trip.getDuration() + r.duration.getMillis());
                t.total = t.trip.getDuration() + db.estimate(r.destination, destination);
                heap.add(t);
            }
        }
        return ret;
    }

    private class Temptrip {
        public Trip trip;
        public int total;
    }
}

