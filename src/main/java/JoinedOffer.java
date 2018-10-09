import DTO.RideJoinedOfferPickupRequest;
import DTO.RideJoinedOfferPickupResponse;
import DTO.RideOfferPickupRequest;
import DTO.RideOfferPickupResponse;

import java.util.Random;

public class JoinedOffer {
    int userid;
    int requestid;
    int triporder;
    int passenger;
    int luggage;
    int offerusercode;
    int requestusercode;
    int pickupstatus;
    String pickuplocation;
    int status;

    public JoinedOffer() {
    }

    public JoinedOffer(int userid, int requestid, int triporder, int passenger, int luggage, int offerusercode, int requestusercode, int pickupstatus, String pickuplocation, int status) {
        this.userid = userid;
        this.requestid = requestid;
        this.triporder = triporder;
        this.passenger = passenger;
        this.luggage = luggage;
        this.offerusercode = offerusercode;
        this.requestusercode = requestusercode;
        this.pickupstatus = pickupstatus;
        this.pickuplocation = pickuplocation;
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

    public int getRequestusercode() {
        return requestusercode;
    }

    public void setRequestusercode(int requestusercode) {
        this.requestusercode = requestusercode;
    }

    public int getPickupstatus() {
        return pickupstatus;
    }

    public void setPickupstatus(int pickupstatus) {
        this.pickupstatus = pickupstatus;
    }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
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
                ", requestusercode=" + requestusercode +
                ", pickupstatus=" + pickupstatus +
                ", pickuplocation='" + pickuplocation + '\'' +
                ", status=" + status +
                '}';
    }

    public RideJoinedOfferPickupResponse getJoinedOfferPickupCode(RideJoinedOfferPickupRequest request){
        int result = 0;
        int code = 0;
        JoinedOffer joinedOffer = DatabaseCommunicator.selectJoinedOffer(request.getUserid(), request.getOfferid());
        if (joinedOffer == null || joinedOffer.getUserid() != request.getUserid() || joinedOffer.getStatus() > 1) {
            result = 3;
        } else if (joinedOffer.getPickuplocation() != request.getLocation()) {
            result = 4;
        } else {
            Random rand = new Random();
            code = rand.nextInt(99999) + 10000;
            result = DatabaseCommunicator.addJoinedOfferPickup(request.getUserid(), request.getOfferid(), code);
        }
        RideJoinedOfferPickupResponse res = new RideJoinedOfferPickupResponse(result, code);
        return res;
    }
}

