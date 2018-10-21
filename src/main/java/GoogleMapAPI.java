import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;

public class GoogleMapAPI {
    private GeoApiContext ctx;

    public GoogleMapAPI() {
        ctx = new GeoApiContext.Builder().apiKey("AIzaSyCv6CgYyQgZsgIBrA4h4QBcb-ZU4Xjfsas").build();
    }

    public int estimate(String address1, String address2) throws InterruptedException, ApiException, IOException {
        if (BoilerideServer.estimateCache.containsKey(address1 + address2)) {
            return BoilerideServer.estimateCache.get(address1 + address2);
        }
        DistanceMatrixApiRequest req = new DistanceMatrixApiRequest(ctx);
        DistanceMatrix res = req.origins(address1)
                .destinations(address2)
                .mode(TravelMode.DRIVING)
                .await();
        if (res == null) {
            System.out.println("not getting result from google map api, check with inputs");
        }
        int est = (int) res.rows[0].elements[0].duration.inSeconds;
        BoilerideServer.estimateCache.put(address1 + address2, est);
        return est;
    }

    public int[] getDistTime(String address1, String address2) throws InterruptedException, ApiException, IOException {
        try {
            DistanceMatrixApiRequest req = new DistanceMatrixApiRequest(ctx);
            DistanceMatrix res = req.origins(address1)
                    .destinations(address2)
                    .mode(TravelMode.DRIVING)
                    .units(Unit.IMPERIAL)
                    .await();
            String distanceWithUnit = res.rows[0].elements[0].distance.humanReadable;
            String distanceInString = distanceWithUnit.split("\\s+")[0];
            int distance = (int) Math.floor(Double.parseDouble(distanceInString));
            int time = (int) res.rows[0].elements[0].duration.inSeconds;
            int[] distTime = {distance, time};
            return distTime;
        } catch (ApiException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public String getCity(String address) throws InterruptedException, ApiException, IOException {
        if (BoilerideServer.cityCache.containsKey(address)) {
            return BoilerideServer.cityCache.get(address);
        }
        String city = "";
        GeocodingApiRequest req = new GeocodingApiRequest(ctx);
        GeocodingResult res = req.address(address).await()[0];
        for (AddressComponent ac : res.addressComponents) {
            for (AddressComponentType act : ac.types) {
                if (act == AddressComponentType.LOCALITY) {
                    city = ac.longName;
                    return city;
                }
            }
        }
        BoilerideServer.cityCache.put(address, city);
        return city;
    }
}
