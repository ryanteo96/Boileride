package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user view account response
 *
 * @version September 14, 2018
 */

public class UserViewAccountResponse {
    private int result;
    private String nickname;
    private String email;
    private String phone;

    public UserViewAccountResponse(int r, String n, String e, String p) {
        result = r;
        nickname = n;
        email = e;
        phone = p;
    }

    public int getResult() {
        return result;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="nickname")
    public void setNickname(String n) {
        nickname = n;
    }

    @Anno(name="email")
    public void setEmail(String e) {
        email = e;
    }

    @Anno(name="phone")
    public void setPhone(String p) {
        phone = p;
    }

    public String toString(){
        return "result: " + result + ", nickname: " + nickname + ", email: " + email + ", phone" + phone;
    }
}
