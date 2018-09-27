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
    private String email;
    private String code;
    private String newpassword;

    public UserResetPasswordRequest(String e, String c, String n) {
        email = e;
        code = c;
        newpassword = n;
    }

    public String  getEmail() {
        return email;
    }

    public String getCode() {
        return code;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setUserid(String e) {
        email = e;
    }

    public void setCode(String c) {
        code = c;
    }

    public void setNewpassword(String n) {
        newpassword = n;
    }

    public String toString(){
        return "email: " + email + ", code: " + code + ", newpassword: " + newpassword;
    }

}
