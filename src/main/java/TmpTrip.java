import DTO.DtoRideOffer;
import DTO.Trip;

import java.util.LinkedList;

public class TmpTrip {
    private LinkedList<RideOffer> rides;
    private int duration;
    private int estimation;

    public TmpTrip() {

    }

    public TmpTrip(LinkedList<RideOffer> rides, int duration, int estimation) {
        this.rides = rides;
        this.duration = duration;
        this.estimation = estimation;
    }

    public LinkedList<RideOffer> getRides() {
        return rides;
    }

    public int getDuration() {
        return duration;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setRides(LinkedList<RideOffer> rides) {
        this.rides = rides;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public Trip toTrip() {
        Trip t = new Trip();
        t.setDuration(this.duration);
        LinkedList<DtoRideOffer> dro = new LinkedList<>();
        for (RideOffer r : this.rides) {
            dro.addLast(r.toDtoRideOffer());
        }
        t.setRides(dro);
        return t;
    }
}
