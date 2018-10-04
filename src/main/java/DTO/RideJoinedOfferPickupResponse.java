package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride joined offer pickup response
 *
 * @version October 3, 2018
 */

public class RideJoinedOfferPickupResponse {
    private int result;
    private int code;

    public RideJoinedOfferPickupResponse(int result, int code) {
        this.result = result;
        this.code = code;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    public int getCode() {
        return code;
    }

    @Anno(name="code")
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RideJoinedOfferPickupResponse{" +
                "result=" + result +
                ", code=" + code +
                '}';
    }
}
