package DTO;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride cancel offer response
 *
 * @version October 3, 2018
 */

public class RideViewAcceptedRequestResponse {
    private int result;
    private ArrayList<DtoAcceptedRequest> acceptedrequestlist;

    public RideViewAcceptedRequestResponse(int r, ArrayList<DtoAcceptedRequest> acceptedrequestlist) {
        this.result = r;
        this.acceptedrequestlist = acceptedrequestlist;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    public ArrayList<DtoAcceptedRequest> getAcceptedrequestlist() {
        return acceptedrequestlist;
    }

    @Anno(name="acceptedrequestlist")
    public void setAcceptedrequestlist(ArrayList<DtoAcceptedRequest> acceptedrequestlist) {
        this.acceptedrequestlist = acceptedrequestlist;
    }

    @Override
    public String toString() {
        String str = "result: " + result + ", acceptedrequestlist: ";
        int index = 0;
        for (DtoAcceptedRequest acceptedRequest: acceptedrequestlist){
            str += index + ":[" + acceptedRequest.toString() + "]";
            index++;
        }
        return str;
    }
}
