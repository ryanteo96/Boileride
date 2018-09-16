package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user view account request
 *
 * @version September 15, 2018
 */

public class UserViewAccountRequest {
    private String userid;

    public UserViewAccountRequest(String u){
        userid = u;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String toString(){
        return "userid: " + userid;
    }
}
