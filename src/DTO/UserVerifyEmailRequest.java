package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user verify email request
 *
 * @version September 15, 2018
 */

public class UserVerifyEmailRequest {
    private String userid;
    private String code;

    public UserVerifyEmailRequest(String u, String c) {
        userid = u;
        code = c;
    }

    public String getUserid() {
        return userid;
    }

    public String getCode() { return code; }

    public void setUserid(String u) {
        this.userid = u;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString(){
        return "userid: " + userid + ", code: " + code;
    }
}
