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
    private String email;
    private String code;

    public UserVerifyEmailRequest(String e, String c)
    {
        email = e;
        code = c;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() { return code; }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString(){
        return "email" + email + ", code: " + code;
    }
}
