import com.google.maps.*;
import com.google.maps.model.Duration;
import org.joda.time.DateTime;
import org.joda.time.Period;

public class RideOffer {
    private String user;
    private String origin;
    private String destination;
    private DateTime departure;
    private Period duration;
    private long distance;
    private int price;
    private RidePreferences ridePreferences;

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
}
