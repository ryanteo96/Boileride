package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user view points request
 *
 * @version October 3, 2018
 */

public class UserViewPointsRequest {
    private int userid;

    public UserViewPointsRequest(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserViewPointsRequest{" +
                "userid=" + userid +
                '}';
    }
}
