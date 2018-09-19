import java.util.LinkedList;

public class Trip {
    private LinkedList<RideOfferSearch> rides;
    private int duration;

    public LinkedList<RideOfferSearch> getRides() {
        return rides;
    }

    public int getDuration() {
        return duration;
    }

    public void setRides(LinkedList<RideOfferSearch> rides) {
        this.rides = rides;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

