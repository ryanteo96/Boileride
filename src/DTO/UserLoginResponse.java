package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user login response
 *
 * @version September 15, 2018
 */

public class UserLoginResponse {
    private int result;
    private String userid;

    public UserLoginResponse(int r, String u) {
        result = r;
        userid = u;
    }

    public int getResult() {
        return result;
    }

    public String getUserid() {
        return userid;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="userid")
    public void setUserid(String u) {
        userid = u;
    }

    public String toString(){
        return "result: " + result + ", userid: " + userid;
    }
}
