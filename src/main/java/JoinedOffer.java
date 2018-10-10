import DTO.*;

import java.util.Date;
import java.util.Random;

public class JoinedOffer {
    int userid;
    int requestid;
    int triporder;
    int passenger;
    int luggage;
    int offerusercode;
    int joinedusercode;
    int offeruserstatus;
    int joineduserstatus;
    int offeredby;
    Date datentime;
    int status;

    public JoinedOffer() {
    }

    public JoinedOffer(int userid, int requestid, int triporder, int passenger, int luggage, int offerusercode, int joinedusercode, int offeruserstatus, int joineduserstatus, int offeredby, Date datentime, int status) {
        this.userid = userid;
        this.requestid = requestid;
        this.triporder = triporder;
        this.passenger = passenger;
        this.luggage = luggage;
        this.offerusercode = offerusercode;
        this.joinedusercode = joinedusercode;
        this.offeruserstatus = offeruserstatus;
        this.joineduserstatus = joineduserstatus;
        this.offeredby = offeredby;
        this.datentime = datentime;
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public int getTriporder() {
        return triporder;
    }

    public void setTriporder(int triporder) {
        this.triporder = triporder;
    }

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

    @Override
    public String toString() {
        return "JoinedOffer{" +
                "userid=" + userid +
                ", requestid=" + requestid +
                ", triporder=" + triporder +
                ", passenger=" + passenger +
                ", luggage=" + luggage +
                ", offerusercode=" + offerusercode +
                ", joinedusercode=" + joinedusercode +
                ", offeruserstatus=" + offeruserstatus +
                ", joineduserstatus=" + joineduserstatus +
                ", offeredby=" + offeredby +
                ", datentime=" + datentime +
                ", status=" + status +
                '}';
    }

    public RideJoinedOfferPickupResponse getJoinedOfferPickupCode(RideJoinedOfferPickupRequest request){
        int result = 0;
        int code = 0;
        Date today = new Date();
        JoinedOffer joinedOffer = DatabaseCommunicator.selectJoinedOffer(request.getUserid(), request.getOfferid());
        if (joinedOffer == null || joinedOffer.getUserid() != request.getUserid() || joinedOffer.getStatus() != 1
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
        } else if (joinedOffer.getUserid() != request.getUserid() || joinedOffer.getStatus() != 1
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
            }
        }
        RideJoinedOfferConfirmResponse res = new RideJoinedOfferConfirmResponse(result);
        return res;
    }
}

