package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride view request request
 *
 * @version September 15, 2018
 */

public class RideViewRequestRequest {
    private int userid;

    public RideViewRequestRequest(int u){
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
