package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride offer pickup request
 *
 * @version October 3, 2018
 */

public class RideOfferPickupRequest {
    private int userid;
    private int offerid;
    private String location;

    public RideOfferPickupRequest(int userid, int offerid, String location) {
        this.userid = userid;
        this.offerid = offerid;
        this.location = location;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOfferid() {
        return offerid;
    }

    public void setOfferid(int offerid) {
        this.offerid = offerid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "RideOfferPickupRequest{" +
                "userid=" + userid +
                ", offerid=" + offerid +
                ", location='" + location + '\'' +
                '}';
    }
}
