import org.joda.time.Period;

import java.util.*;

public class FakeDB {
    private RideOffer[] all;
    public FakeDB() {
        all = new RideOffer[10];
        all[0] = new RideOffer("A", "B", new Period((int)(Math.random() * 100)));
        all[1] = new RideOffer("A", "C", new Period((int)(Math.random() * 100)));
        all[2] = new RideOffer("A", "D", new Period((int)(Math.random() * 100)));
        all[3] = new RideOffer("B", "C", new Period((int)(Math.random() * 100)));
        all[4] = new RideOffer("B", "D", new Period((int)(Math.random() * 100)));
        all[5] = new RideOffer("C", "D", new Period((int)(Math.random() * 100)));
        all[6] = new RideOffer("D", "E", new Period((int)(Math.random() * 100)));
        all[7] = new RideOffer("A", "E", new Period((int)(Math.random() * 100)));
        all[8] = new RideOffer("B", "E", new Period((int)(Math.random() * 100)));
        all[9] = new RideOffer("C", "E", new Period((int)(Math.random() * 100)));
    }
    public List<RideOffer> ridesFrom(String origin) {
        List<RideOffer> ret = new ArrayList<>();
        for (RideOffer r : all) {
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
