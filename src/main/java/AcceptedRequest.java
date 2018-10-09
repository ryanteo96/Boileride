import DTO.RideAcceptedRequestConfirmRequest;
import DTO.RideAcceptedRequestConfirmResponse;
import DTO.RideAcceptedRequestPickupRequest;
import DTO.RideAcceptedRequestPickupResponse;

import java.util.Random;

public class AcceptedRequest {
    int userid;
    int requestid;
    int requestusercode;
    int acceptedusercode;
    int pickupstatus;
    int requestedby;
    String pickuplocation;
    int status;

    public AcceptedRequest() {
    }

    public AcceptedRequest(int userid, int requestid, int requestusercode, int acceptedusercode, int pickupstatus, int requestedby, String pickuplocation, int status) {
        this.userid = userid;
        this.requestid = requestid;
        this.requestusercode = requestusercode;
        this.acceptedusercode = acceptedusercode;
        this.pickupstatus = pickupstatus;
        this.requestedby = requestedby;
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

    public int getPickupstatus() {
        return pickupstatus;
    }

    public void setPickupstatus(int pickupstatus) {
        this.pickupstatus = pickupstatus;
    }

    public int getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(int requestedby) {
        this.requestedby = requestedby;
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
        return "AcceptedRequest{" +
                "userid=" + userid +
                ", requestid=" + requestid +
                ", requestusercode=" + requestusercode +
                ", acceptedusercode=" + acceptedusercode +
                ", pickupstatus=" + pickupstatus +
                ", requestedby=" + requestedby +
                ", pickuplocation='" + pickuplocation + '\'' +
                ", status=" + status +
                '}';
    }

    public RideAcceptedRequestPickupResponse getAcceptedRequestPickupCode(RideAcceptedRequestPickupRequest request){
        int result = 0;
        int code = 0;
        AcceptedRequest acceptedRequest = DatabaseCommunicator.selectAcceptedRequest(request.getRequestid());
        if (acceptedRequest == null || acceptedRequest.getUserid() != request.getUserid() || acceptedRequest.getStatus() > 1) {
            result = 3;
        } else if (acceptedRequest.getPickuplocation() != request.getLocation()) {
            result = 4;
        } else {
            Random rand = new Random();
            code = rand.nextInt(99999) + 10000;
            result = DatabaseCommunicator.addAcceptedReqPickup(request.getUserid(), request.getRequestid(), code);
        }
        RideAcceptedRequestPickupResponse res = new RideAcceptedRequestPickupResponse(result, code);
        return res;
    }

    public RideAcceptedRequestConfirmResponse confirmAcceptedRequestPickup(RideAcceptedRequestConfirmRequest request){
        int result = 0;
        AcceptedRequest acceptedRequest = DatabaseCommunicator.selectAcceptedRequest(request.getRequestid());
        if (acceptedRequest == null) {
            result = 3;
        } else if (acceptedRequest.getUserid() != request.getUserid() || acceptedRequest.getStatus() != 1
                || acceptedRequest.getPickupstatus() == 2 || acceptedRequest.getPickupstatus() == 3
                || acceptedRequest.getRequestusercode() == 0 || acceptedRequest.getAcceptedusercode() == 0) {
            result = 4;
        }
        else if (acceptedRequest.getRequestusercode() != request.getCode()){
            result = 5;
        }
        else {
            if (acceptedRequest.getPickupstatus() == 0){
                result = DatabaseCommunicator.updateRequestPickupStatus(request.getRequestid(), 1, 3);
            }
            else if (acceptedRequest.getPickupstatus() == 2){
                result = DatabaseCommunicator.updateRequestPickupStatus(request.getRequestid(), 3, 4);
            }
        }
        RideAcceptedRequestConfirmResponse res = new RideAcceptedRequestConfirmResponse(result);
        return res;
    }
}
