package DTO;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride view joined offer response
 *
 * @version October 3, 2018
 */

public class RideViewJoinedOfferResponse {
    private int result;
    private ArrayList<DtoJoinedOffer> joinedofferlist;

    public RideViewJoinedOfferResponse() {
    }

    public RideViewJoinedOfferResponse(int result, ArrayList<DtoJoinedOffer> joinedofferlist) {
        this.result = result;
        this.joinedofferlist = joinedofferlist;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<DtoJoinedOffer> getJoinedofferlist() {
        return joinedofferlist;
    }

    @Anno(name="joinedofferlist")
    public void setJoinedofferlist(ArrayList<DtoJoinedOffer> joinedofferlist) {
        this.joinedofferlist = joinedofferlist;
    }

    @Override
    public String toString() {
        String str = "result: " + result + ", joinedofferlist: ";
        int index = 0;
        for (DtoJoinedOffer joinedOffer: joinedofferlist){
            str += index + ":[" + joinedOffer.toString() + "]";
            index++;
        }
        return str;
    }
}