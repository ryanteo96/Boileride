package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride cancel joined offer request
 *
 * @version October 3, 2018
 */

public class RideCancelJoinedOfferRequest {
    int userid;
    int offerid;

    public RideCancelJoinedOfferRequest(int userid, int offerid) {
        this.userid = userid;
        this.offerid = offerid;
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

    @Override
    public String toString() {
        return "RideCancelJoinedOfferRequest{" +
                "userid=" + userid +
                ", offerid=" + offerid +
                '}';
    }
}
