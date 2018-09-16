package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride view request response
 *
 * @version September 15, 2018
 */

public class RideViewRequestResponse {
    private int result;
    private RideRequest[] requestlist;

    public RideViewRequestResponse(int r, RideRequest[] re) {
        result = r;
        requestlist = re;
    }

    public int getResult() {
        return result;
    }

    public RideRequest[] getRequestlist() {
        return requestlist;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="requestlist")
    public void setRequestlist(String u) {
        requestlist = u;
    }

    public String toString(){
        String s = "result: " + result + ", requestlist: ";
        for (int i=0; i<requestlist.length; i++){
            s += requestlist[i].toString();
        }
        return s;
    }

}
