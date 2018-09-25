import java.util.LinkedList;

public class Trip {
    private LinkedList<RideOfferObj> rides;
    private int duration;

    public LinkedList<RideOfferObj> getRides() {
        return rides;
    }

    public int getDuration() {
        return duration;
    }

    public void setRides(LinkedList<RideOfferObj> rides) {
        this.rides = rides;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

