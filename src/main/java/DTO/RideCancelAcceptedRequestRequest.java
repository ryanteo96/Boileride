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
    private int requestID;
    public RideCancelAcceptedRequestRequest(int userid, int getUserid, int requestID) {
        this.userid = userid;
        this.getUserid = getUserid;
        this.requestID = requestID;
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

    public int getRequestID()
    {
        return requestID;
    }
    public void setRequestID(int requestID)
    {
        this.requestID = requestID;
    }

    @Override
    public String toString() {
        return "RideCancelAcceptedRequestRequest{" +
                "userid=" + userid +
                ", getUserid=" + getUserid +
                ", requestID=" + requestID +
                '}';
    }
}
