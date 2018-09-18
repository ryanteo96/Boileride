package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride request response
 *
 * @version September 16, 2018
 */

public class RideRequestResponse {
    private int result;
    private int requestid;

    public RideRequestResponse(int r, int re) {
        result = r;
        requestid = re;
    }

    public int getResult() {
        return result;
    }

    public int getRequestid() {
        return requestid;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="requestid")
    public void setRequestid(int re) {
        requestid = re;
    }

    public String toString(){
        return "result: " + result + ", requestid: " + requestid;
    }
}
