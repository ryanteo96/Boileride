package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user reset password request
 *
 * @version September 15, 2018
 */

public class UserResetPasswordRequest {
    private String userid;
    private String code;
    private String newpassword;

    public UserResetPasswordRequest(String u, String c, String n) {
        userid = u;
        code = c;
        newpassword = n;
    }

    public String getUserid() {
        return userid;
    }

    public String getCode() {
        return code;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setUserid(String u) {
        userid = u;
    }

    public void setCode(String c) {
        code = c;
    }

    public void setNewpassword(String n) {
        newpassword = n;
    }

    public String toString(){
        return "userid: " + userid + ", code: " + code + ", newpassword: " + newpassword;
    }

}
