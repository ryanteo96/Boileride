package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride cancel request request
 *
 * @version September 16, 2018
 */

public class RideCancelRequestRequest {
    private int userid;
    private int requestid;

    public RideCancelRequestRequest(int userid, int requestid) {
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

    public String toString(){
        return "userid: " + userid + ", requestid: " + requestid;
    }

}
