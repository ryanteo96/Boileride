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
        DistanceMatrixApiRequest req = new DistanceMatrixApiRequest(ctx);
        DistanceMatrix res = req.origins(address1)
                .destinations(address2)
                .mode(TravelMode.DRIVING)
                .await();
        return (int) res.rows[0].elements[0].duration.inSeconds / 60;
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
        return city;
    }
}
