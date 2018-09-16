package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user logout request
 *
 * @version September 15, 2018
 */

public class UserLogoutRequest {
    private String userid;

    public UserLogoutRequest(String u) {
        userid = u;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String u) {
        userid = u;
    }

    public String toString(){
        return "userid: " + userid;
    }

}
