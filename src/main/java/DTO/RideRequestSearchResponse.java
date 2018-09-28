package DTO;

import java.util.ArrayList;

public class RideRequestSearchResponse {
    private int result;
    private ArrayList<DtoRideRequest> rides;

    public RideRequestSearchResponse() {

    }

    public RideRequestSearchResponse(int result) {
        this.result = result;
    }

    public RideRequestSearchResponse(int result, ArrayList<DtoRideRequest> rides) {
        this.result = result;
        this.rides = rides;
    }

    public int getResult() {
        return result;
    }

    public ArrayList<DtoRideRequest> getRides() {
        return rides;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setRides(ArrayList<DtoRideRequest> rides) {
        this.rides = rides;
    }

}
