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
    private int userid;

    public UserVerifyEmailResponse(int result, int userid) {
        this.result = result;
        this.userid = userid;

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
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String toString(){
        return "result: " + result + ", userid: " + userid;
    }

}