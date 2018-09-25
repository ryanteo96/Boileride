package DTO;

import java.util.ArrayList;

public class RideViewRequestResponse {
    private int result;
    private ArrayList<DtoRideRequest> requestlist;

    public RideViewRequestResponse(int r, ArrayList<DtoRideRequest> requestlist) {
        this.result = r;
        this.requestlist = requestlist;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    public ArrayList<DtoRideRequest> getRequestlist() {
        return requestlist;
    }

    @Anno(name="requestlist")
    public void setRequestlist(ArrayList<DtoRideRequest> requestlist) {
        this.requestlist = requestlist;
    }

    public String toString(){
        String str = "result: " + result + ", requestlist: ";
        int index = 0;
        for (DtoRideRequest rideRequest: requestlist){
            str += index + ":[" + rideRequest.toString() + "]";
            index++;
        }
        return str;
    }
}
