package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user forgot password response
 *
 * @version September 15, 2018
 */

public class UserForgotPasswordResponse {
    private int result;
    private String code;

    public UserForgotPasswordResponse(int r) {
        result = r;
    }

    public int getResult() {
        return result;
    }



    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }



    public void setCode(String c) {code = c;}

    public String toString(){
        return "result: " + result + ", code: " + code;
    }

}
