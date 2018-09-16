package DTO;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride view offer response
 *
 * @version September 15, 2018
 */

public class RideViewOfferResponse {
    private int result;
    private RideOffer[] offerlist;

    public RideViewOfferResponse(int r, RideOffer[] o) {
        result = r;
        offerlist = o;
    }

    public int getResult() {
        return result;
    }

    public RideOffer[] getRequestlist() {
        return offerlist;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    @Anno(name="requestlist")
    public void setRequestlist(RideOffer[] o) {
        offerlist = o;
    }

    public String toString(){
        String s = "result: " + result + ", offerlist: ";
        for (int i=0; i<offerlist.length; i++){
            s += offerlist[i].toString();
        }
        return s;
    }

}
