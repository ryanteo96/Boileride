package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user forgot password request
 *
 * @version September 15, 2018
 */

public class UserForgotPasswordRequest {
    private String email;

    public UserForgotPasswordRequest(String e) {
        email = e;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        email = e;
    }

    public String toString() {
        return "email: " + email;
    }
}
