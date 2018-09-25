import java.util.HashMap;
import java.util.Map;

public class FakeGoogleMapAPI {
    public static int estimate(String origin, String destination) {
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
