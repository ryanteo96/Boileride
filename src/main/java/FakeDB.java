import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.*;

public class FakeDB {
    private RideOfferObj[] offers;
    private RideRequestObj[] requests;
    public FakeDB() {
        String user = "Jack";
        //double originProximity = 5;
        //double destinationProximity = 5;
        DateTime departure = new DateTime(2018, 9, 30, 10, 30, 0);
        //int departureProximity = 5;
        RidePreferences pref = new RidePreferences(4, 4, true, true, true, true);

        offers = new RideOfferObj[10];
        offers[0] = new RideOfferObj(user, "A", "B", departure, pref, 100, 100, 100);
        offers[1] = new RideOfferObj(user, "A", "C", departure, pref, 100, 100, 100);
        offers[2] = new RideOfferObj(user, "A", "D", departure, pref, 100, 100, 100);
        offers[3] = new RideOfferObj(user, "B", "C", departure, pref, 100, 100, 100);
        offers[4] = new RideOfferObj(user, "B", "D", departure, pref, 100, 100, 100);
        offers[5] = new RideOfferObj(user, "C", "D", departure, pref, 100, 100, 100);
        offers[6] = new RideOfferObj(user, "D", "E", departure, pref, 100, 100, 100);
        offers[7] = new RideOfferObj(user, "A", "E", departure, pref, 100, 100, 100);
        offers[8] = new RideOfferObj(user, "B", "E", departure, pref, 100, 100, 100);
        offers[9] = new RideOfferObj(user, "C", "E", departure, pref, 100, 100, 100);

        requests = new RideRequestObj[10];
        requests[0] = new RideRequestObj(user, "A", "B", departure, pref, 100, 100, 100);
        requests[1] = new RideRequestObj(user, "A", "C", departure, pref, 100, 100, 100);
        requests[2] = new RideRequestObj(user, "A", "D", departure, pref, 100, 100, 100);
        requests[3] = new RideRequestObj(user, "B", "C", departure, pref, 100, 100, 100);
        requests[4] = new RideRequestObj(user, "B", "D", departure, pref, 100, 100, 100);
        requests[5] = new RideRequestObj(user, "C", "D", departure, pref, 100, 100, 100);
        requests[6] = new RideRequestObj(user, "D", "E", departure, pref, 100, 100, 100);
        requests[7] = new RideRequestObj(user, "A", "E", departure, pref, 100, 100, 100);
        requests[8] = new RideRequestObj(user, "B", "E", departure, pref, 100, 100, 100);
        requests[9] = new RideRequestObj(user, "C", "E", departure, pref, 100, 100, 100);
    }
    public List<RideOfferObj> rideOfferFrom(String origin) {
        List<RideOfferObj> ret = new ArrayList<>();
        for (RideOfferObj r : offers) {
            if (r.getOrigin().equals(origin)) {
                ret.add(r);
            }
        }
        return ret;

    }

    public List<RideRequestObj> rideRequestFrom(String origin, String destination) {
        List<RideRequestObj> ret = new ArrayList<>();
        for (RideRequestObj r : requests) {
            if (r.getOrigin().equals(origin) && r.getDestination().equals(destination)) {
                ret.add(r);
            }
        }
        return ret;
    }
}
