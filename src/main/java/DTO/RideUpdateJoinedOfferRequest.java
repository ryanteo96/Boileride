package DTO;

import java.util.Arrays;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride update join offer request
 *
 * @version October 3, 2018
 */

public class RideUpdateJoinedOfferRequest {
    private int userid;
    private int[] offeridlist;
    private int passenger;
    private int luggage;

    public RideUpdateJoinedOfferRequest(int userid, int[] offeridlist, int passenger, int luggage) {
        this.userid = userid;
        this.offeridlist = offeridlist;
        this.passenger = passenger;
        this.luggage = luggage;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int[] getOfferidlist() {
        return offeridlist;
    }

    public void setOfferidlist(int[] offeridlist) {
        this.offeridlist = offeridlist;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    @Override
    public String toString() {
        return "RideUpdateJoinedOfferRequest{" +
                "userid=" + userid +
                ", offeridlist=" + Arrays.toString(offeridlist) +
                ", passenger=" + passenger +
                ", luggage=" + luggage +
                '}';
    }
}
