package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride view joined offer request
 *
 * @version October 3, 2018
 */

public class RideViewJoinedOfferRequest {
    private int userid;

    public RideViewJoinedOfferRequest(int userid) {
        this.userid = userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public String toString(){
        return "userid: " + userid;
    }

}
