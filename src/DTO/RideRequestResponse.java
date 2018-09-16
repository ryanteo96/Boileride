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
    private String requestid;

    public RideRequestResponse(int r, String re) {
        result = r;
        requestid = re;
    }

    public int getResult() {
        return result;
    }

    public String getRequestid() {
        return requestid;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="requestid")
    public void setRequestid(String re) {
        requestid = re;
    }

    public String toString(){
        return "result: " + result + ", requestid: " + requestid;
    }
}
