package DTO;

public class RideViewOfferResponse {
    private int result;
    private DtoRideOffer[] offerlist;

    public RideViewOfferResponse(int result, DtoRideOffer[] offerlist) {
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

    public DtoRideOffer[] getOfferlist() {
        return offerlist;
    }

    @Anno(name="offerlist")
    public void setOfferlist(DtoRideOffer[] offerlist) {
        this.offerlist = offerlist;
    }

    public String toString(){
        String str = "result: " + result + ", offerlist: ";
        for (int i=0; i<offerlist.length;i++){
            str += i + ":[" + offerlist[i].toString() + "]";
        }
        return str;
    }
}
