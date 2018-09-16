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
    private String userid;
    private String offerid;

    public RideCancelOfferRequest(String u, String o) {
        userid = u;
        offerid = o;
    }

    public String getUserid() {
        return userid;
    }

    public String getOfferid() {
        return offerid;
    }

    public void setUserid(String u) {
        userid = u;
    }

    public void setOfferid(String o) {
        offerid = o;
    }

    public String toString(){
        return "userid: " + userid + ", offerid: " + offerid;
    }

}
