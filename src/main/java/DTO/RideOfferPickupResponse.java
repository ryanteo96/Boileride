package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride offer pickup response
 *
 * @version October 3, 2018
 */

public class RideOfferPickupResponse {
    private int result;
    private int code;

    public RideOfferPickupResponse(int result, int code) {
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
        return "RideOfferPickupResponse{" +
                "result=" + result +
                ", code=" + code +
                '}';
    }
}
