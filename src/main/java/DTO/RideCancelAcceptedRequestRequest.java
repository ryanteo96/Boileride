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
    private int requestid;

    public RideCancelAcceptedRequestRequest(int userid, int getUserid, int requestID) {
        this.userid = userid;
        this.requestid = requestID;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRequestid()
    {
        return requestid;
    }
    public void setRequestid(int requestID)
    {
        this.requestid = requestID;
    }

    @Override
    public String toString() {
        return "RideCancelAcceptedRequestRequest{" +
                "userid=" + userid +
                ", requestid=" + requestid +
                '}';
    }
}
