package DTO;

public class UserSignUpRequest {
    private String email;
    private String password;
    private String nickname;
    private String phone;

    public UserSignUpRequest(String e, String p, String n, String ph) {
        email = e;
        password = p;
        nickname = n;
        phone = ph;
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
        return email + " " + password + " " + nickname + " " + phone;
    }
}
