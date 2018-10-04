package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride cancel accepted request response
 *
 * @version October 3, 2018
 */

public class RideCancelAcceptedRequestResponse {
    private int result;

    public RideCancelAcceptedRequestResponse(int result) {
        this.result = result;
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
