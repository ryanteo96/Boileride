import org.joda.time.DateTime;
import java.util.*;

public class RideOfferSearchObj {
    private String user;
    private String origin;
    private double originProximity;
    private String destination;
    private double destinationProximity;
    private DateTime departure;
    private int departureProximity;
    private RidePreferences ridePreferences;
    private int nrides;

    public RideOfferSearchObj() {

    }

    public RideOfferSearchObj(String user,
                              String origin,
                              double originProximity,
                              String destination,
                              double destinationProximity,
                              DateTime departure,
                              int departureProximity,
                              RidePreferences ridePreferences,
                              int nrides) {
        this.user = user;
        this.origin = origin;
        this.originProximity = originProximity;
        this.destination = destination;
        this.destinationProximity = destinationProximity;
        this.departure = departure;
        this.departureProximity = departureProximity;
        this.ridePreferences = ridePreferences;
        this.nrides = nrides;
    }

    public String getUser() {
        return user;
    }

    public String getOrigin() {
        return origin;
    }

    public double getOriginProximity() {
        return originProximity;
    }

    public String getDestination() {
        return destination;
    }

    public double getDestinationProximity() {
        return destinationProximity;
    }

    public DateTime getDeparture() {
        return departure;
    }

    public int getDepartureProximity() {
        return departureProximity;
    }

    public RidePreferences getRidePreferences() {
        return ridePreferences;
    }

    public int getNrides() {
        return nrides;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setOriginProximity(double originProximity) {
        this.originProximity = originProximity;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDestinationProximity(double destinationProximity) {
        this.destinationProximity = destinationProximity;
    }

    public void setDeparture(DateTime departure) {
        this.departure = departure;
    }

    public void setDepartureProximity(int departureProximity) {
        this.departureProximity = departureProximity;
    }

    public void setRidePreferences(RidePreferences ridePreferences) {
        this.ridePreferences = ridePreferences;
    }

    public void setNrides(int nrides) {
        this.nrides = nrides;
    }

    public String toString() {
        return "Origin: " + origin +
                ", Origin Proximity: " + originProximity +
                ", Destination: " + destination +
                ", Destination Proximity: " + destinationProximity +
                ", Departure: " + departure.toString() +
                ", Departure Proximity: " + departureProximity +
                ", Ride Preferences: " + ridePreferences.toString() +
                ", Number of Rides: " + nrides;
    }

    public static List<Trip> search(RideOfferSearchObj query) {
        List<Trip> ret = new ArrayList<>();
        PriorityQueue<Temptrip> heap = new PriorityQueue<>((a, b)->b.total - a.total);
        FakeDB db = new FakeDB();
        Temptrip start = new Temptrip();
        start.trip = new Trip();
        start.trip.setRides(new LinkedList<>());
        start.trip.getRides().add(new RideOfferObj(query.origin, 0));
        heap.add(start);
        while (!heap.isEmpty()) {
            Temptrip curr = heap.poll();
            if (curr.trip.getRides().getLast().getDestination().equals(query.destination)) {
                if (curr.trip.getRides().size() <= query.nrides) {
                    ret.add(curr.trip);
                }
                continue;
            }
            for (RideOfferObj r : db.rideOfferFrom(curr.trip.getRides().getLast().getDestination())) {
                Temptrip t = new Temptrip();
                t.trip = new Trip();
                t.trip.setRides(new LinkedList<>());
                for (RideOfferObj a : curr.trip.getRides()) {
                    t.trip.getRides().add(a);
                }
                t.trip.getRides().add(r);
                t.trip.setDuration(curr.trip.getDuration() + r.getDuration());
                t.total = t.trip.getDuration() + FakeGoogleMapAPI.estimate(r.getDestination(), query.destination);
                heap.add(t);
            }
        }
        return ret;
    }

    private static class Temptrip {
        private Trip trip;
        private int total;
    }
}

