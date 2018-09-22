import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.List;

public class RideRequestSearchObj {
    private String user;
    private String origin;
    private double originProximity;
    private String destination;
    private double destinationProximity;
    private DateTime departure;
    private int departureProximity;
    private RidePreferences ridePreferences;

    public RideRequestSearchObj() {

    }

    public RideRequestSearchObj(String user,
                                String origin,
                                double originProximity,
                                String destination,
                                double destinationProximity,
                                DateTime departure,
                                int departureProximity,
                                RidePreferences ridePreferences) {
        this.user = user;
        this.origin = origin;
        this.originProximity = originProximity;
        this.destination = destination;
        this.destinationProximity = destinationProximity;
        this.departure = departure;
        this.departureProximity = departureProximity;
        this.ridePreferences = ridePreferences;
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

    public String toString() {
        return "Origin: " + origin +
                ", Origin Proximity: " + originProximity +
                ", Destination: " + destination +
                ", Destination Proximity: " + destinationProximity +
                ", Departure: " + departure.toString() +
                ", Departure Proximity: " + departureProximity +
                ", Ride Preferences: " + ridePreferences.toString();
    }

    public static List<RideRequestObj> search (RideRequestSearchObj query) {
        List<RideRequestObj> ret = new ArrayList<>();
        FakeDB db = new FakeDB();
        for (RideRequestObj r : db.rideRequestFrom(query.origin, query.destination)) {
            if (FakeGoogleMapAPI.estimate(r.getOrigin(), query.origin) <= query.originProximity &&
            FakeGoogleMapAPI.estimate(r.getDestination(), query.destination) <= query.destinationProximity &&
            new Interval(query.departure, r.getDeparture()).toDurationMillis() <= query.departureProximity * 60 * 1000 &&
            r.getRidePreferences().nseats >= query.ridePreferences.nseats &&
            r.getRidePreferences().nluggage >= query.ridePreferences.nluggage &&
            r.getRidePreferences().smoking == query.ridePreferences.smoking &&
            r.getRidePreferences().eating == query.ridePreferences.eating &&
            r.getRidePreferences().pet == query.ridePreferences.pet &&
            r.getRidePreferences().ac == query.ridePreferences.ac) {
                ret.add(r);
            }
        }
        return ret;
    }
}
