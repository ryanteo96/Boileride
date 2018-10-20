import DTO.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class JoinedOffer {
    int userid;
    int offerid;
    int triporder;
    Date joindate;
    int passenger;
    int luggage;
    int offerusercode;
    int joinedusercode;
    int offeruserstatus;
    int joineduserstatus;
    int joinedstatus;
    int offeredby;
    Date datentime;
    int price;
    int status;

    public JoinedOffer() {
    }

    public JoinedOffer(int userid, int offerid, int triporder, Date joindate, int passenger, int luggage, int offerusercode, int joinedusercode, int offeruserstatus, int joineduserstatus, int joinedstatus, int offeredby, Date datentime, int price, int status) {
        this.userid = userid;
        this.offerid = offerid;
        this.triporder = triporder;
        this.joindate = joindate;
        this.passenger = passenger;
        this.luggage = luggage;
        this.offerusercode = offerusercode;
        this.joinedusercode = joinedusercode;
        this.offeruserstatus = offeruserstatus;
        this.joineduserstatus = joineduserstatus;
        this.joinedstatus = joinedstatus;
        this.offeredby = offeredby;
        this.datentime = datentime;
        this.price = price;
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOfferid() {
        return offerid;
    }

    public void setOfferid(int offerid) {
        this.offerid = offerid;
    }

    public int getTriporder() {
        return triporder;
    }

    public void setTriporder(int triporder) {
        this.triporder = triporder;
    }

    public Date getJoindate() { return joindate; }

    public void setJoindate(Date joindate) { this.joindate = joindate; }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public int getOfferusercode() {
        return offerusercode;
    }

    public void setOfferusercode(int offerusercode) {
        this.offerusercode = offerusercode;
    }

    public int getJoinedusercode() {
        return joinedusercode;
    }

    public void setJoinedusercode(int joinedusercode) {
        this.joinedusercode = joinedusercode;
    }

    public int getOfferuserstatus() {
        return offeruserstatus;
    }

    public void setOfferuserstatus(int offeruserstatus) {
        this.offeruserstatus = offeruserstatus;
    }

    public int getJoineduserstatus() {
        return joineduserstatus;
    }

    public void setJoineduserstatus(int joineduserstatus) {
        this.joineduserstatus = joineduserstatus;
    }

    public int getJoinedstatus() {
        return joinedstatus;
    }

    public void setJoinedstatus(int joinedstatus) {
        this.joinedstatus = joinedstatus;
    }

    public int getOfferedby() {
        return offeredby;
    }

    public void setOfferedby(int offeredby) {
        this.offeredby = offeredby;
    }

    public Date getDatentime() {
        return datentime;
    }

    public void setDatentime(Date datentime) {
        this.datentime = datentime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "JoinedOffer{" +
                "userid=" + userid +
                ", offerid=" + offerid +
                ", triporder=" + triporder +
                ", joindate=" + joindate +
                ", passenger=" + passenger +
                ", luggage=" + luggage +
                ", offerusercode=" + offerusercode +
                ", joinedusercode=" + joinedusercode +
                ", offeruserstatus=" + offeruserstatus +
                ", joineduserstatus=" + joineduserstatus +
                ", joinedstatus=" + joinedstatus +
                ", offeredby=" + offeredby +
                ", datentime=" + datentime +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    public RideViewJoinedOfferResponse viewJoinedOfferfromDB(RideViewJoinedOfferRequest request){
        int result = 0;
        ArrayList<DtoJoinedOffer> joinedofferlist = new ArrayList<DtoJoinedOffer>();
        joinedofferlist = DatabaseCommunicator.selectJoinedOfferList(request.getUserid());
        RideViewJoinedOfferResponse res = new RideViewJoinedOfferResponse(result, joinedofferlist);
        return res;
    }

    public RideJoinedOfferPickupResponse getJoinedOfferPickupCode(RideJoinedOfferPickupRequest request){
        int result = 0;
        int code = 0;
        Date today = new Date();
        JoinedOffer joinedOffer = DatabaseCommunicator.selectJoinedOffer(request.getUserid(), request.getOfferid());
        if (joinedOffer == null || joinedOffer.getUserid() != request.getUserid() || joinedOffer.getStatus() == 0 || joinedOffer.getStatus() == 2
                || Math.abs(today.getTime()-joinedOffer.getDatentime().getTime())/1000 > 1800) {
            result = 3;
        } else {
            Random rand = new Random();
            code = rand.nextInt(899999) + 100000;
            result = DatabaseCommunicator.addJoinedOfferPickup(request.getUserid(), request.getOfferid(), code);
        }
        RideJoinedOfferPickupResponse res = new RideJoinedOfferPickupResponse(result, code);
        return res;
    }

    public RideJoinedOfferConfirmResponse confirmJoinedOfferPickup(RideJoinedOfferConfirmRequest request) {
        int result = 0;
        Date today = new Date();
        JoinedOffer joinedOffer = DatabaseCommunicator.selectJoinedOffer(request.getUserid(), request.getOfferid());
        if (joinedOffer == null) {
            result = 3;
        } else if (joinedOffer.getUserid() != request.getUserid() || joinedOffer.getStatus() == 0 || joinedOffer.getStatus() == 2 || joinedOffer.getJoinedstatus() == 1
                || joinedOffer.getOfferusercode() == 0 || joinedOffer.getJoinedusercode() == 0
                || Math.abs(today.getTime()-joinedOffer.getDatentime().getTime())/1000 > 1800) {
            result = 4;
        } else if (joinedOffer.getJoineduserstatus() == 1){
            result = 5;
        } else if (joinedOffer.getOfferusercode() != request.getCode()) {
            result = 6;
        } else {
            result = DatabaseCommunicator.updateJoinedUserStatus(request.getUserid(), request.getOfferid(), 1);
            if (result == 0) {
                //Make payment
                result = PointCalculator.makePayment(request.getUserid(), joinedOffer.getOfferedby(), joinedOffer.getPrice(), "Make payment for joined ride offer");
                DatabaseCommunicator.updateJoinedStatus(request.getOfferid(), request.getUserid(), 1);
            }
        }
        RideJoinedOfferConfirmResponse res = new RideJoinedOfferConfirmResponse(result);
        return res;
    }
}

