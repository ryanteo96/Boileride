import DTO.DtoRideOffer;
import DTO.DtoRideRequest;

import java.util.ArrayList;
import java.util.Date;

public class PointCalculator {

    //when passenger confirm pickup (request or joined offer)
    public static int makePayment(int userid, int price){
        // reduce passenger reserve
        int result = 0;
        result = DatabaseCommunicator.updatePointReserve(userid, 0, price*-1);
        return result;
    }

    //when driver confirm pickup (offer or accepted request)
    public static int getPayment(int userid, int price){
        // reduce driver reserve
        // increase driver points by price*2 (price + reserve)
        int result = 0;
        result = DatabaseCommunicator.updatePointReserve(userid, price*2, price*-1);
        return result;
    }

    //when user post request, post offer, accept request, join offer
    public static int reservePoints(int userid, int price){
        // reduce points by price
        // increase reserve by price
        int result = 0;
        result = DatabaseCommunicator.updatePointReserve(userid, price*-1, price);
        return result;
    }

    //when user update request, offer, joined offer
    public static int updatePoints(int userid, int newPrice, int oldPrice){
        // update reserve and points according to change in price
        int result = 0;
        result = DatabaseCommunicator.updatePointReserve(userid, oldPrice-newPrice, newPrice-oldPrice);
        return result;
    }

    //when user cancel request, offer, acceptedrequest, joinedoffer
    public static int chargeCancellationFee(int userid, Date datentime, int price) {
        // reduce user reserve
        // move remaining reserve to points
        int result = 0;
        Date today = new Date();
        long diff = (datentime.getTime() - today.getTime()) / 1000;
        if (diff > 259200) {
            result = DatabaseCommunicator.updatePointReserve(userid, price, price * -1);
        } else if (diff <= 259200 && diff > 172800) {
            result = DatabaseCommunicator.updatePointReserve(userid, (int) (price * 0.5), price * -1);
        } else if (diff <= 172800 && diff > 86400) {
            result = DatabaseCommunicator.updatePointReserve(userid, (int) (price * 0.25), price * -1);
        } else {
            result = DatabaseCommunicator.updatePointReserve(userid, 0, price * -1);
        }
        return result;
    }

    //when user view points, post request, post offer, accept request, join offer, update request, offer, joined offer
    public static int chargeFailConfirmationFee(int userid){
        // get all requests with status not equal 'over' of the user and check if date pass today+30mins,
            // if accepted request == null, reduce reserve and increase points
            // if accepted request != null
                // if requestuserstatus == 0 && requestusercode != 0, reduce reserve and increase points
                // else if requestuserstatus == 0 && requestusercode == 0, reduce reserve
            // set request as over
        Date today = new Date();
        ArrayList<DtoRideRequest> rideRequests = DatabaseCommunicator.selectCurrentRequestList(userid);
        if (rideRequests != null) {
            for (DtoRideRequest rideRequest : rideRequests) {
                if ((today.getTime() - rideRequest.getDatentime().getTime()) / 1000 > 1800) {
                    AcceptedRequest acceptedRequest = DatabaseCommunicator.selectAcceptedRequest(rideRequest.getRequestid());
                    if (acceptedRequest == null) {
                        DatabaseCommunicator.updatePointReserve(userid, rideRequest.getPrice(), rideRequest.getPrice() * -1);
                    } else {
                        if (acceptedRequest.getRequestuserstatus() == 0 && acceptedRequest.getRequestusercode() != 0) {
                            DatabaseCommunicator.updatePointReserve(userid, rideRequest.getPrice(), rideRequest.getPrice() * -1);
                        } else if (acceptedRequest.getRequestuserstatus() == 0 && acceptedRequest.getRequestusercode() == 0) {
                            DatabaseCommunicator.updatePointReserve(userid, 0, rideRequest.getPrice() * -1);
                        }
                    }
                    DatabaseCommunicator.updateRequestStatus(rideRequest.getRequestid(), 3);
                }
            }
        }
        // get all offers with status not equal 'over' of the user and check if date pass today+30mins
            // if joined offer == null, reduce reserve and increase points
            // if joined offer != null, iterate through joined offer list
                // if offeruserstatus == 0 && offerusercode != 0, reduce reserve and increase points
                // else if offeruserstatus == 0 && offerusercode == 0, reduce reserve
            // set offer as over
        ArrayList<DtoRideOffer> rideOffers = DatabaseCommunicator.selectCurrentOfferList(userid);
        if (rideOffers != null) {
            for (DtoRideOffer rideOffer : rideOffers) {
                if ((today.getTime() - rideOffer.getDatentime().getTime()) / 1000 > 1800) {
                    JoinedOffer joinedOffer = DatabaseCommunicator.selectJoinedOffer(userid, rideOffer.getOfferid());
                    if (joinedOffer == null) {
                        DatabaseCommunicator.updatePointReserve(userid, rideOffer.getPrice(), rideOffer.getPrice() * -1);
                    } else {
                        if (joinedOffer.getJoineduserstatus() == 0 && joinedOffer.getJoinedusercode() != 0) {
                            DatabaseCommunicator.updatePointReserve(userid, rideOffer.getPrice(), rideOffer.getPrice() * -1);
                        } else if (joinedOffer.getJoineduserstatus() == 0 && joinedOffer.getJoinedusercode() == 0) {
                            DatabaseCommunicator.updatePointReserve(userid, 0, rideOffer.getPrice() * -1);
                        }
                    }
                    DatabaseCommunicator.updateOfferStatus(rideOffer.getOfferid(), userid, 3);
                }
            }
        }
        // get all accepted requests with acceptedstatus not equal 'over' and check if date pass today+30mins,
            // if accepteduserstatus == 0 && acceptedusercode != 0, reduce reserve and increase points
            // else if accepteduserstatus == 0 && acceptedusercode == 0, reduce reserve
            // set acceptedstatus as over
        ArrayList<AcceptedRequest> acceptedRequests = DatabaseCommunicator.selectCurrentAcceptedRequestList(userid);
        if (acceptedRequests != null) {
            for (AcceptedRequest acceptedRequest : acceptedRequests) {
                if ((today.getTime() - acceptedRequest.getDatentime().getTime()) / 1000 > 1800) {
                    if (acceptedRequest.getAcceptedstatus() == 0 && acceptedRequest.getAcceptedusercode() != 0){
                        DatabaseCommunicator.updatePointReserve(userid, acceptedRequest.getPrice(), acceptedRequest.getPrice()*-1);
                    }
                    else if (acceptedRequest.getAcceptedstatus() == 0 && acceptedRequest.getAcceptedusercode() == 0){
                        DatabaseCommunicator.updatePointReserve(userid, 0, acceptedRequest.getPrice()*-1);
                    }
                    DatabaseCommunicator.updateAcceptedStatus(acceptedRequest.requestid, 1);
                }
            }
        }
        // get all joined offers with joinedstatus not equal 'over' and check if date pass today+30mins,
            // if joineduserstatus == 0 && joinedusercode != 0, reduce reserve and increase points
            // else if joineduserstatus == 0 && joinedusercode == 0, reduce reserve
            // set joinedstatus as over
        ArrayList<JoinedOffer> joinedOffers = DatabaseCommunicator.selectCurrentJoinedOfferList(userid);
        if (joinedOffers != null){
            for (JoinedOffer joinedOffer: joinedOffers) {
                if ((today.getTime() - joinedOffer.getDatentime().getTime()) / 1000 > 1800) {
                    if (joinedOffer.getJoinedstatus() == 0 && joinedOffer.getJoinedusercode() != 0){
                        DatabaseCommunicator.updatePointReserve(userid, joinedOffer.getPrice(), joinedOffer.getPrice()*-1);
                    }
                    else if (joinedOffer.getJoinedstatus() == 0 && joinedOffer.getJoinedusercode() == 0){
                        DatabaseCommunicator.updatePointReserve(userid, 0, joinedOffer.getPrice()*-1);
                    }
                    DatabaseCommunicator.updateJoinedStatus(joinedOffer.offerid, userid, 1);
                }
            }
        }
        return 0;
    }


}
