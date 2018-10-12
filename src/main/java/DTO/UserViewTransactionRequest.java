package DTO;

public class UserViewTransactionRequest {
    private int userid;

    public UserViewTransactionRequest(int userid) {
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
        return "UserViewTransactionRequest{" +
                "userid=" + userid +
                '}';
    }
}
