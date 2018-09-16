package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user reset password response
 *
 * @version September 15, 2018
 */

public class UserResetPasswordResponse {
    private int result;

    public UserResetPasswordResponse(int r) {
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
