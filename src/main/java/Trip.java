import java.util.LinkedList;

public class Trip {
    private LinkedList<RideOffer> rides;
    private int duration;

    public LinkedList<RideOffer> getRides() {
        return rides;
    }

    public int getDuration() {
        return duration;
    }

    public void setRides(LinkedList<RideOffer> rides) {
        this.rides = rides;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

