import org.joda.time.Period;

import java.util.*;

public class FakeDB {
    private RideOfferSearch[] all;
    public FakeDB() {
        all = new RideOfferSearch[10];
        all[0] = new RideOfferSearch("A", "B", new Period((int)(Math.random() * 100)));
        all[1] = new RideOfferSearch("A", "C", new Period((int)(Math.random() * 100)));
        all[2] = new RideOfferSearch("A", "D", new Period((int)(Math.random() * 100)));
        all[3] = new RideOfferSearch("B", "C", new Period((int)(Math.random() * 100)));
        all[4] = new RideOfferSearch("B", "D", new Period((int)(Math.random() * 100)));
        all[5] = new RideOfferSearch("C", "D", new Period((int)(Math.random() * 100)));
        all[6] = new RideOfferSearch("D", "E", new Period((int)(Math.random() * 100)));
        all[7] = new RideOfferSearch("A", "E", new Period((int)(Math.random() * 100)));
        all[8] = new RideOfferSearch("B", "E", new Period((int)(Math.random() * 100)));
        all[9] = new RideOfferSearch("C", "E", new Period((int)(Math.random() * 100)));
    }
    public List<RideOfferSearch> ridesFrom(String origin) {
        List<RideOfferSearch> ret = new ArrayList<>();
        for (RideOfferSearch r : all) {
            if (r.getOrigin().equals(origin)) {
                ret.add(r);
            }
        }
        return ret;

    }
    public int estimate(String origin, String destination) {
        Map<String, Map<String, Integer>> table = new HashMap<>();
        String s[] = {"A", "B", "C", "D", "E"};
        for (int i = 0; i < s.length; i++) {
            Map<String, Integer> t = new HashMap<>();
            for (int j = 0; j < s.length; j++) {
                t.put(s[j], (i + j) % s.length);
            }
            table.put(s[i], t);
        }
        return table.get(origin).get(destination);
    }
}
