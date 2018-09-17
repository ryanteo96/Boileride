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
    private int userid;
    private String code;
    private String email;
    public UserVerifyEmailRequest(int u, String c, String e)
    {
        userid = u;
        code = c;
        email = e;
    }

    public int getUserid() {
        return userid;
    }

    public String getCode() { return code; }

    public void setUserid(int u) {
        this.userid = u;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString(){
        return "userid: " + userid + ", code: " + code;
    }
}
