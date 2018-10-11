import DTO.RideAcceptedRequestConfirmRequest;
import DTO.RideAcceptedRequestConfirmResponse;
import DTO.RideAcceptedRequestPickupRequest;
import DTO.RideAcceptedRequestPickupResponse;

import java.util.Date;
import java.util.Random;

public class AcceptedRequest {
    int userid;
    int requestid;
    int requestusercode;
    int acceptedusercode;
    int requestuserstatus;
    int accepteduserstatus;
    int acceptedstatus;
    int requestedby;
    Date datentime;
    int price;
    int status;

    public AcceptedRequest() {
    }

    public AcceptedRequest(int userid, int requestid, int requestusercode, int acceptedusercode, int requestuserstatus, int accepteduserstatus, int acceptedstatus, int requestedby, Date datentime, int price, int status) {
        this.userid = userid;
        this.requestid = requestid;
        this.requestusercode = requestusercode;
        this.acceptedusercode = acceptedusercode;
        this.requestuserstatus = requestuserstatus;
        this.accepteduserstatus = accepteduserstatus;
        this.acceptedstatus = acceptedstatus;
        this.requestedby = requestedby;
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

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public int getRequestusercode() {
        return requestusercode;
    }

    public void setRequestusercode(int requestusercode) {
        this.requestusercode = requestusercode;
    }

    public int getAcceptedusercode() {
        return acceptedusercode;
    }

    public void setAcceptedusercode(int acceptedusercode) {
        this.acceptedusercode = acceptedusercode;
    }

    public int getRequestuserstatus() {
        return requestuserstatus;
    }

    public void setRequestuserstatus(int requestuserstatus) {
        this.requestuserstatus = requestuserstatus;
    }

    public int getAccepteduserstatus() {
        return accepteduserstatus;
    }

    public void setAccepteduserstatus(int accepteduserstatus) {
        this.accepteduserstatus = accepteduserstatus;
    }

    public int getAcceptedstatus() {
        return acceptedstatus;
    }

    public void setAcceptedstatus(int acceptedstatus) {
        this.acceptedstatus = acceptedstatus;
    }

    public int getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(int requestedby) {
        this.requestedby = requestedby;
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
        return "AcceptedRequest{" +
                "userid=" + userid +
                ", requestid=" + requestid +
                ", requestusercode=" + requestusercode +
                ", acceptedusercode=" + acceptedusercode +
                ", requestuserstatus=" + requestuserstatus +
                ", accepteduserstatus=" + accepteduserstatus +
                ", acceptedstatus=" + acceptedstatus +
                ", requestedby=" + requestedby +
                ", datentime=" + datentime +
                ", status=" + status +
                ", price=" + price +
                '}';
    }

    public RideAcceptedRequestPickupResponse getAcceptedRequestPickupCode(RideAcceptedRequestPickupRequest request){
        int result = 0;
        int code = 0;
        Date today = new Date();
        AcceptedRequest acceptedRequest = DatabaseCommunicator.selectAcceptedRequest(request.getRequestid());
        if (acceptedRequest == null || acceptedRequest.getUserid() != request.getUserid() || acceptedRequest.getStatus() != 1
                || Math.abs(today.getTime()-acceptedRequest.getDatentime().getTime())/1000 > 1800) {
            result = 3;
        } else {
            Random rand = new Random();
            code = rand.nextInt(899999) + 100000;
            result = DatabaseCommunicator.addAcceptedReqPickup(request.getUserid(), request.getRequestid(), code);
        }
        RideAcceptedRequestPickupResponse res = new RideAcceptedRequestPickupResponse(result, code);
        return res;
    }

    public RideAcceptedRequestConfirmResponse confirmAcceptedRequestPickup(RideAcceptedRequestConfirmRequest request){
        int result = 0;
        Date today = new Date();
        AcceptedRequest acceptedRequest = DatabaseCommunicator.selectAcceptedRequest(request.getRequestid());
        if (acceptedRequest == null) {
            result = 3;
        } else if (acceptedRequest.getUserid() != request.getUserid() || acceptedRequest.getStatus() != 1
                || acceptedRequest.getRequestusercode() == 0 || acceptedRequest.getAcceptedusercode() == 0
                || Math.abs(today.getTime()-acceptedRequest.getDatentime().getTime())/1000 > 1800) {
            result = 4;
        } else if (acceptedRequest.getAccepteduserstatus() == 1) {
            result = 5;
        }else if (acceptedRequest.getRequestusercode() != request.getCode()){
            result = 6;
        }
        else {
            result = DatabaseCommunicator.updateAcceptedUserStatus(request.getRequestid(), 1);
            if (result == 0){
                //Get payment
            }
        }
        RideAcceptedRequestConfirmResponse res = new RideAcceptedRequestConfirmResponse(result);
        return res;
    }
}
