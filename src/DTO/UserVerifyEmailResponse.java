package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user verify email response
 *
 * @version September 15, 2018
 */

public class UserVerifyEmailResponse {
    private int result;

    public UserVerifyEmailResponse(int r) {
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
