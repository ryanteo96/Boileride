package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user view points response
 *
 * @version October 3, 2018
 */

public class UserViewPointsResponse {
    private int result;

    public UserViewPointsResponse(int result) {
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
