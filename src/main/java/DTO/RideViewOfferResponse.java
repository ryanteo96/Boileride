package DTO;

import java.util.ArrayList;

public class RideViewOfferResponse {
    private int result;
    private ArrayList<DtoRideOffer> offerlist;

    public RideViewOfferResponse(int result, ArrayList<DtoRideOffer> offerlist) {
        this.result = result;
        this.offerlist = offerlist;
    }

    public int getResult() {
        return result;
    }

    @Anno(name="result")
    public void setResult(int r) {
        result = r;
    }

    public ArrayList<DtoRideOffer> getOfferlist() {
        return offerlist;
    }

    @Anno(name="offerlist")
    public void setOfferlist(ArrayList<DtoRideOffer> offerlist) {
        this.offerlist = offerlist;
    }

    public String toString(){
        String str = "result: " + result + ", offerlist: ";
        int index = 0;
        for (DtoRideOffer rideOffer: offerlist){
            str += index + ":[" + rideOffer.toString() + "]";
            index++;
        }
        return str;
    }
}
