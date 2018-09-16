package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride update request response
 *
 * @version September 16, 2018
 */

public class RideUpdateOfferResponse {
    private int result;

    public RideUpdateOfferResponse(int r) {
        result = r;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    public String toString(){
        return "result: " + result;
    }
}
