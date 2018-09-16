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
    private String userid;
    private String requestid;

    public RideCancelRequestRequest(String u, String r) {
        userid = u;
        requestid = r;
    }

    public String getUserid() {
        return userid;
    }

    public String getRequestid() {
        return requestid;
    }


    public void setUserid(String u) {
        userid = u;
    }

    public void setRequestid(String re) {
        requestid = re;
    }

    public String toString(){
        return "userid: " + userid + ", requestid: " + requestid;
    }

}
