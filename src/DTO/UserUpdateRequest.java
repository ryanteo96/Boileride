package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user update request
 *
 * @version September 15, 2018
 */

public class UserUpdateRequest {
    private int userid;
    private String email;
    private String password;
    private String nickname;
    private String phone;

    public UserUpdateRequest(int u, String e, String p, String n, String ph) {
        userid = u;
        email = e;
        password = p;
        nickname = n;
        phone = ph;
    }

    public int getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setPassword(String p) {
        password = p;
    }

    public void setNickname(String n) {
        nickname = n;
    }

    public void setPhone(String ph) {
        phone = ph;
    }

    public String toString(){
        return "userid: " + userid + ", email: " + email + ", password: " + password + ", nickname: " + nickname + ", phone: " + phone;
    }
}
