import org.joda.time.DateTime;

public class RideRequestObj {
    private String requester;
    private String origin;
    private String destination;
    private DateTime departure;
    private RidePreferences ridePreferences;
    private int price;
    private int duration;
    private double distance;

    public RideRequestObj() {

    }

    public RideRequestObj(String requester,
                        String origin,
                        String destination,
                        DateTime departure,
                        RidePreferences ridePreferences,
                        int price,
                        int duration,
                        double distance) {
        this.requester = requester;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.ridePreferences = ridePreferences;
        this.price = price;
        this.duration = duration;
        this.distance = distance;
    }

    public String getRequester() {
        return requester;
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

    public int getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }

    public int getPrice() {
        return price;
    }

    public RidePreferences getRidePreferences() {
        return ridePreferences;
    }

    public void setRequester(String requester) {
        this.requester = requester;
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

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRidePreferences(RidePreferences ridePreferences) {
        this.ridePreferences = ridePreferences;
    }

    public String toString() {
        return "Requester: " + requester +
                ", Origin: " + origin +
                ", Destination: " + destination +
                ", Departure: " + departure.toString() +
                ", Ride Preferences: " + ridePreferences.toString() +
                ", Price: " + price +
                ", Duration: " + duration +
                ", Distance: " + distance;
    }
}
