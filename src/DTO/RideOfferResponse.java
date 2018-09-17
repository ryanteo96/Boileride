package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride offer response
 *
 * @version September 16, 2018
 */

public class RideOfferResponse {
    private int result;
    private int offerid;

    public RideOfferResponse(int r, int o) {
        result = r;
        offerid = o;
    }

    public int getResult() {
        return result;
    }

    public int getOfferid() {
        return offerid;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="requestid")
    public void setOfferid(int o) {
        offerid = o;
    }

    public String toString(){
        return "result: " + result + ", offerid: " + offerid;
    }
}
