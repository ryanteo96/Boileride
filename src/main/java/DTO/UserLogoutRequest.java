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
    private int userid;

    public UserLogoutRequest(int u) {
        userid = u;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int u) {
        userid = u;
    }

    public String toString(){
        return "userid: " + userid;
    }

}
