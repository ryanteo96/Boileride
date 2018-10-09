package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for user view points response
 *
 * @version October 3, 2018
 */

public class UserViewPointsResponse {
    private int result;
    private int points;
    private int reserve;

    public UserViewPointsResponse(int result, int points, int reserve) {
        this.result = result;
        this.points = points;
        this.reserve = reserve;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    public int getPoints() {
        return points;
    }

    @Anno(name="points")
    public void setPoints(int points) {
        this.points = points;
    }

    public int getReserve() {
        return reserve;
    }

    @Anno(name="reserve")
    public void setReserve(int reserve) {
        this.reserve = reserve;
    }

    public String toString(){
        return "result: " + result + ", points: " + points + ", reserve: " + reserve;
    }
}
