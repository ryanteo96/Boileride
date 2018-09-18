package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride cancel offer request
 *
 * @version September 16, 2018
 */

public class RideCancelOfferRequest {
    private int userid;
    private int offerid;

    public RideCancelOfferRequest(int userid, int offerid) {
        this.userid = userid;
        this.offerid = offerid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setOfferid(int offerid) {
        this.offerid = offerid;
    }

    public int getUserid() {
        return userid;
    }

    public int getOfferid() {
        return offerid;
    }

    public String toString(){
        return "userid: " + userid + ", offerid: " + offerid;
    }

}
