package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride accepted request pickup response
 *
 * @version October 3, 2018
 */

public class RideAcceptedRequestPickupResponse {
    private int result;
    private int code;

    public RideAcceptedRequestPickupResponse(int result, int code) {
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
        return "RideAcceptedRequestPickupResponse{" +
                "result=" + result +
                ", code=" + code +
                '}';
    }
}
