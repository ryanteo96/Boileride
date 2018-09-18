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
    private int userid;
    private String code;

    public UserForgotPasswordResponse(int r, int u) {
        result = r;
        userid = u;
    }

    public int getResult() {
        return result;
    }

    public int getUserid() {
        return userid;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="userid")
    public void setUserid(int u) {
        userid = u;
    }

    public void setCode(String c) {code = c;}

    public String toString(){
        return "result: " + result + ", userid: " + userid;
    }

}
