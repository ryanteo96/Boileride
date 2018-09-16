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
    private String offerid;

    public RideOfferResponse(int r, String o) {
        result = r;
        offerid = o;
    }

    public int getResult() {
        return result;
    }

    public String getOfferid() {
        return offerid;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="requestid")
    public void setOfferid(String o) {
        offerid = o;
    }

    public String toString(){
        return "result: " + result + ", offerid: " + offerid;
    }
}
