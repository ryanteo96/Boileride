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
    private String userid;

    public RideViewOfferRequest(String u){
        userid = u;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String toString(){
        return "userid: " + userid;
    }
}
