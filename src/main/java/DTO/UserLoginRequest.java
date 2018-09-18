package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user login request
 *
 * @version September 15, 2018
 */

public class UserLoginRequest {
    private String email;
    private String password;

    public UserLoginRequest(String e, String p, String n, String ph) {
        email = e;
        password = p;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setPassword(String p) {
        password = p;
    }

    public String toString(){
        return "email: " + email + ", password: " + password;
    }
}
