package DTO;

public class UserSignUpResponse {
    private int result;
    private int userId;

    public UserSignUpResponse(int r, int u) {
        result = r;
        userId = u;
    }

    public int getResult() {
        return result;
    }

    public int getUserId() {
        return userId;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="userid")
    public void setUserId(int u) {
        userId = u;
    }
}
