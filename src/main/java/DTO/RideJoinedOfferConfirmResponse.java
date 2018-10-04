package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride joined offer confirm pickup response
 *
 * @version October 4, 2018
 */

public class RideJoinedOfferConfirmResponse {
    private int result;

    public RideJoinedOfferConfirmResponse(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Override
    public String toString() {
        return "RideJoinedOfferConfirmResponse{" +
                "result=" + result +
                '}';
    }
}
