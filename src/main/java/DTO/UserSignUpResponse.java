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
    private int userid;

    public UserSignUpResponse(int r, int u) {
        result = r;
        userid = u;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    public int getUserid() {
        return userid;
    }

    @Anno(name="userid")
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String toString(){
        return "result: " + result;
    }
}
