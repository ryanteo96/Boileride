package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride request pickup request
 *
 * @version October 3, 2018
 */

public class RideRequestPickupRequest {
    private int userid;
    private int requestid;
    private String location;

    public RideRequestPickupRequest(int userid, int requestid, String location) {
        this.userid = userid;
        this.requestid = requestid;
        this.location = location;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "RideRequestPickupRequest{" +
                "userid=" + userid +
                ", requestid=" + requestid +
                ", location='" + location + '\'' +
                '}';
    }
}
