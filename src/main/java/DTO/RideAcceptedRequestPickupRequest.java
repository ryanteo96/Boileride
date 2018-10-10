package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for accepted ride request pickup response
 *
 * @version October 3, 2018
 */

public class RideAcceptedRequestPickupRequest {
    private int userid;
    private int requestid;

    public RideAcceptedRequestPickupRequest(int userid, int requestid) {
        this.userid = userid;
        this.requestid = requestid;
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

    @Override
    public String toString() {
        return "RideAcceptedRequestPickupRequest{" +
                "userid=" + userid +
                ", requestid=" + requestid +
                '}';
    }
}
