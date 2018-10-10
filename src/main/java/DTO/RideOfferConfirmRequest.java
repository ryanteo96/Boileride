package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride offer confirm pickup request
 *
 * @version October 4, 2018
 */

public class RideOfferConfirmRequest {
    private int userid;
    private int offerid;
    private int joineduserid;
    private int code;

    public RideOfferConfirmRequest(int userid, int offerid, int joineduserid, int code) {
        this.userid = userid;
        this.offerid = offerid;
        this.joineduserid = joineduserid;
        this.code = code;
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

    public int getJoineduserid() {
        return joineduserid;
    }

    public void setJoineduserid(int joineduserid) {
        this.joineduserid = joineduserid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RideOfferConfirmRequest{" +
                "userid=" + userid +
                ", offerid=" + offerid +
                ", joineduserid=" + joineduserid +
                ", code=" + code +
                '}';
    }
}
