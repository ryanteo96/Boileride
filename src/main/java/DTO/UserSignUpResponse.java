package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user signup response
 *
 * @version September 14, 2018
 */

public class UserSignUpResponse {
    private int result;

    public UserSignUpResponse(int r, int u) {
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
