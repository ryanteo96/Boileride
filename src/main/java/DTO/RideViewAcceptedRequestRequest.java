package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride view accepted request request
 *
 * @version October 3, 2018
 */

public class RideViewAcceptedRequestRequest {
    private int userid;

    public RideViewAcceptedRequestRequest(int userid) {
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
