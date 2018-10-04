package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride accepted request confirm pickup request
 *
 * @version October 4, 2018
 */

public class RideAcceptedRequestConfirmRequest {
    private int userid;
    private int requestid;
    private int code;

    public RideAcceptedRequestConfirmRequest(int userid, int requestid, int code) {
        this.userid = userid;
        this.requestid = requestid;
        this.code = code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RideAcceptedRequestConfirmRequest{" +
                "userid=" + userid +
                ", requestid=" + requestid +
                ", code=" + code +
                '}';
    }
}
