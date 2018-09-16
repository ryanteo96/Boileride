package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride cancel offer response
 *
 * @version September 16, 2018
 */

public class RideCancelOfferResponse {
    private int result;

    public RideCancelOfferResponse(int r) {
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
