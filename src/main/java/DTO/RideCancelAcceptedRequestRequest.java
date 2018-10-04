package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride cancel accepted request request
 *
 * @version October 3, 2018
 */

public class RideCancelAcceptedRequestRequest {
    private int userid;
    private int getUserid;

    public RideCancelAcceptedRequestRequest(int userid, int getUserid) {
        this.userid = userid;
        this.getUserid = getUserid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getGetUserid() {
        return getUserid;
    }

    public void setGetUserid(int getUserid) {
        this.getUserid = getUserid;
    }

    @Override
    public String toString() {
        return "RideCancelAcceptedRequestRequest{" +
                "userid=" + userid +
                ", getUserid=" + getUserid +
                '}';
    }
}
