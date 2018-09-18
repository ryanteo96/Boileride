package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride view offer request
 *
 * @version September 15, 2018
 */

public class RideViewOfferRequest {
    private int userid;

    public RideViewOfferRequest(int u){
        userid = u;
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
