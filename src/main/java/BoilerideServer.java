import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.*;


import java.io.*;
import java.net.InetSocketAddress;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import DTO.*;
import org.joda.time.DateTime;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * Http Server that accepts request from front-end client and sends JSON response back
 * Connect to mySQL database
 *
 * @version September 10, 2018
 */

public class BoilerideServer {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://142.93.19.7:3306/boileridedb?useSSL=false";

    static final String USER = "backend";
    static final String PASS = "Boileride18!";

    static Connection conn;

    public class BoilerideServlet extends HttpServlet {
        private String uri;
        public BoilerideServlet(String uri){
            this.uri = uri;
        }

        private String handleSignup(Gson gson, JsonObject request){
            UserSignUpRequest req = null;
            UserSignUpResponse res = null;
            boolean isRightFormat = true;
            try {
                req = gson.fromJson(request, UserSignUpRequest.class);
            }catch (JsonSyntaxException e){
                isRightFormat = false;
            }
            if (isRightFormat) {
                System.out.println("Received: " + req.toString());
                User user = new User();
                res = user.signUp(req);
            }
            else{
                res = new UserSignUpResponse(97, -1);
            }
            return gson.toJson(res);
        }

        private String handleVerifyemail(Gson gson, JsonObject request, HttpServletRequest servletReq){
            UserVerifyEmailRequest req = null;
            UserVerifyEmailResponse res = null;
            boolean isRightFormat = true;
            try {
                req = gson.fromJson(request, UserVerifyEmailRequest.class);
            }catch (JsonSyntaxException e){
                isRightFormat = false;
            }
            if (isRightFormat) {
                System.out.println("Received: " + req.toString());
                User user = new User();
                res = user.verifyEmailCode(req);
                if (res.getResult() == 0) {
                    HttpSession session = servletReq.getSession();
                    session.setAttribute("name",1);
                    session.setMaxInactiveInterval(60*60);
                }
            }
            else{
                res = new UserVerifyEmailResponse(97, -1);
            }
            return gson.toJson(res);
        }

        private String handleLogin(Gson gson, JsonObject request, HttpServletRequest servletReq){
            UserLoginRequest req = null;
            UserLoginResponse res = null;
            boolean isRightFormat = true;
            try {
                req = gson.fromJson(request, UserLoginRequest.class);
            }catch (JsonSyntaxException e){
                isRightFormat = false;
            }
            if (isRightFormat) {
                System.out.println("Received: " + req.toString());
                User user = new User();
                res = user.login(req);
                if (res.getResult() == 0) {
                    HttpSession session = servletReq.getSession();
                    session.setAttribute("name",1);
                    session.setMaxInactiveInterval(60*60);
                }
            }
            else{
                res = new UserLoginResponse(97, -1);
            }
            return gson.toJson(res);
        }

        private String handleForgotpassword(Gson gson, JsonObject request){
            UserForgotPasswordRequest req = null;
            UserForgotPasswordResponse res = null;
            boolean isRightFormat = true;
            try {
                req = gson.fromJson(request, UserForgotPasswordRequest.class);
            }catch (JsonSyntaxException e){
                isRightFormat = false;
            }
            if (isRightFormat) {
                System.out.println("Received: " + req.toString());
                User user = new User();
                res = user.forgotPassword(req);
            }
            else{
                res = new UserForgotPasswordResponse(97);
            }
            return gson.toJson(res);
        }

        private String handleResetpassword(Gson gson, JsonObject request){
            UserResetPasswordRequest req = null;
            UserResetPasswordResponse res = null;
            boolean isRightFormat = true;
            try {
                req = gson.fromJson(request, UserResetPasswordRequest.class);
            }catch (JsonSyntaxException e){
                isRightFormat = false;
            }
            if (isRightFormat) {
                System.out.println("Received: " + req.toString());
                User user = new User();
                res = user.resetPassword(req);
            }
            else{
                res = new UserResetPasswordResponse(97);
            }
            return gson.toJson(res);
        }

        private String handleViewaccount(Gson gson, JsonObject request, HttpServletRequest servletReq){
            UserViewAccountRequest req = null;
            UserViewAccountResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, UserViewAccountRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    User user = new User();
                    res = user.viewAccount(req);
                } else {
                    res = new UserViewAccountResponse(97, "", "", "");
                }
            }
            else {
                res = new UserViewAccountResponse(2, "", "", "");
            }
            return gson.toJson(res);
        }

        private String handleUserupdate(Gson gson, JsonObject request, HttpServletRequest servletReq){
            UserUpdateRequest req = null;
            UserUpdateResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, UserUpdateRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    User user = new User();
                    res = user.updateUser(req, false);
                } else {
                    res = new UserUpdateResponse(97);
                }
            }
            else {
                res = new UserUpdateResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleViewPoints(Gson gson, JsonObject request, HttpServletRequest servletReq){
            UserViewPointsRequest req = null;
            UserViewPointsResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, UserViewPointsRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    User user = new User();
                    res = user.viewPointsFromDB(req);
                } else {
                    res = new UserViewPointsResponse(97, -1, -1);
                }
            }
            else {
                res = new UserViewPointsResponse(2, -1, -1);
            }
            return gson.toJson(res);
        }

        private String handleViewTransaction(Gson gson, JsonObject request, HttpServletRequest servletReq){
            UserViewTransactionRequest req = null;
            UserViewTransactionResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, UserViewTransactionRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    User user = new User();
                    res = user.viewTransactionFromDB(req);
                } else {
                    ArrayList<DtoTransaction> transactionlist = new ArrayList<DtoTransaction>();
                    res = new UserViewTransactionResponse(97, transactionlist);
                }
            }
            else {
                ArrayList<DtoTransaction> transactionlist = new ArrayList<DtoTransaction>();
                res = new UserViewTransactionResponse(97, transactionlist);
            }
            return gson.toJson(res);
        }

        private String handleLogout(Gson gson, JsonObject request, HttpServletRequest servletReq){
            UserLogoutRequest req = null;
            UserLogoutResponse res = null;
            boolean isRightFormat = true;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                try {
                    req = gson.fromJson(request, UserLogoutRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    User user = new User();
                    res = user.logout(req);
                    if (res.getResult() == 0) {
                        session.invalidate();
                    }
                } else {
                    res = new UserLogoutResponse(97);
                }
            }
            else{
                res = new UserLogoutResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleViewRequest(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideViewRequestRequest req = null;
            RideViewRequestResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null){
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideViewRequestRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.viewRideRequestfromDB(req);
                }
                else{
                    ArrayList<DtoRideRequest> requestlist = new ArrayList<DtoRideRequest>();
                    res = new RideViewRequestResponse(97, requestlist);
                }
            }
            else {
                ArrayList<DtoRideRequest> requestlist = new ArrayList<DtoRideRequest>();
                res = new RideViewRequestResponse(2, requestlist);
            }
            return gson.toJson(res);
        }

        private String handleViewOffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideViewOfferRequest req = null;
            RideViewOfferResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideViewOfferRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.viewRideOfferfromDB(req);
                } else {
                    ArrayList<DtoRideOffer> offerlist = new ArrayList<DtoRideOffer>();
                    res = new RideViewOfferResponse(97, offerlist);
                }
            }
            else {
                ArrayList<DtoRideOffer> offerlist = new ArrayList<DtoRideOffer>();
                res = new RideViewOfferResponse(2, offerlist);
            }
            return gson.toJson(res);
        }

        private String handleViewAcceptedrequest(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideViewAcceptedRequestRequest req = null;
            RideViewAcceptedRequestResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideViewAcceptedRequestRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    //RideOffer rideOffer = new RideOffer();
                    //res = rideOffer.viewRideOfferfromDB(req);
                } else {
                    ArrayList<DtoAcceptedRequest> acceptedrequestlist = new ArrayList<DtoAcceptedRequest>();
                    res = new RideViewAcceptedRequestResponse(97, acceptedrequestlist);
                }
            }
            else {
                ArrayList<DtoAcceptedRequest> acceptedrequestlist = new ArrayList<DtoAcceptedRequest>();
                res = new RideViewAcceptedRequestResponse(2, acceptedrequestlist);
            }
            return gson.toJson(res);
        }

        private String handleViewJoinedOffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideViewJoinedOfferRequest req = null;
            RideViewJoinedOfferResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideViewJoinedOfferRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    //RideOffer rideOffer = new RideOffer();
                    //res = rideOffer.viewRideOfferfromDB(req);
                } else {
                    ArrayList<DtoJoinedOffer> joinedofferlist = new ArrayList<DtoJoinedOffer>();
                    res = new RideViewJoinedOfferResponse(97, joinedofferlist);
                }
            }
            else {
                ArrayList<DtoJoinedOffer> joinedofferlist = new ArrayList<DtoJoinedOffer>();
                res = new RideViewJoinedOfferResponse(2, joinedofferlist);
            }
            return gson.toJson(res);
        }

        private String handleRideRequest(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideRequestRequest req = null;
            RideRequestResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideRequestRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.addRideRequestToDB(req);
                } else {
                    res = new RideRequestResponse(97, -1);
                }
            }
            else {
                res = new RideRequestResponse(2, -1);
            }
            return gson.toJson(res);
        }

        private String handleCancelRequest(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideCancelRequestRequest req = null;
            RideCancelRequestResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideCancelRequestRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.cancelRideRequestInDB(req);
                } else {
                    res = new RideCancelRequestResponse(97);
                }
            }
            else {
                res = new RideCancelRequestResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleUpdateRequest(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideUpdateRequestRequest req = null;
            RideUpdateRequestResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideUpdateRequestRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.updateRideRequestInDB(req);
                } else {
                    res = new RideUpdateRequestResponse(97);
                }
            }
            else {
                res = new RideUpdateRequestResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleRideOffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideOfferRequest req = null;
            RideOfferResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideOfferRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.addRideOfferToDB(req);
                } else {
                    res = new RideOfferResponse(97, -1);
                }
            }
            else {
                res = new RideOfferResponse(2, -1);
            }
            return gson.toJson(res);
        }

        private String handleCancelOffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideCancelOfferRequest req = null;
            RideCancelOfferResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideCancelOfferRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.cancelRideOfferInDB(req);
                } else {
                    res = new RideCancelOfferResponse(97);
                }
            }
            else {
                res = new RideCancelOfferResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleUpdateOffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideUpdateOfferRequest req = null;
            RideUpdateOfferResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideUpdateOfferRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.updateRideOfferInDB(req);
                } else {
                    res = new RideUpdateOfferResponse(97);
                }
            }
            else {
                res = new RideUpdateOfferResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleSearchRequest(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideRequestSearchRequest req = null;
            RideRequestSearchResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideRequestSearchRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    try {
                        res = RideRequest.search(req);
                    } catch (InterruptedException | ApiException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    res = new RideRequestSearchResponse(97);
                }
            }
            else {
                res = new RideRequestSearchResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleSearchOffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideOfferSearchRequest req = null;
            RideOfferSearchResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideOfferSearchRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    try {
                        res = RideOffer.search(req);
                    } catch (InterruptedException | ApiException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    res = new RideOfferSearchResponse(97);
                }
            }
            else {
                res = new RideOfferSearchResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleAcceptRequest(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideAcceptRequestRequest req = null;
            RideAcceptRequestResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideAcceptRequestRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
//                    RideOffer rideOffer = new RideOffer();
//                   res = rideOffer.addRideOfferToDB(req);
                } else {
                    res = new RideAcceptRequestResponse(97);
                }
            }
            else {
                res = new RideAcceptRequestResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleCancelAcceptedrequest(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideCancelAcceptedRequestRequest req = null;
            RideCancelAcceptedRequestResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideCancelAcceptedRequestRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
//                    RideOffer rideOffer = new RideOffer();
//                   res = rideOffer.addRideOfferToDB(req);
                } else {
                    res = new RideCancelAcceptedRequestResponse(97);
                }
            }
            else {
                res = new RideCancelAcceptedRequestResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleJoinOffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideJoinOfferRequest req = null;
            RideJoinOfferResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideJoinOfferRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
//                    RideOffer rideOffer = new RideOffer();
//                   res = rideOffer.addRideOfferToDB(req);
                } else {
                    res = new RideJoinOfferResponse(97);
                }
            }
            else {
                res = new RideJoinOfferResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleCancelJoinedoffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideCancelJoinedOfferRequest req = null;
            RideCancelJoinedOfferResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideCancelJoinedOfferRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
//                    RideOffer rideOffer = new RideOffer();
//                   res = rideOffer.addRideOfferToDB(req);
                } else {
                    res = new RideCancelJoinedOfferResponse(97);
                }
            }
            else {
                res = new RideCancelJoinedOfferResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleUpdateJoinedoffer(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideUpdateJoinedOfferRequest req = null;
            RideUpdateJoinedOfferResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideUpdateJoinedOfferRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
//                    RideOffer rideOffer = new RideOffer();
//                   res = rideOffer.addRideOfferToDB(req);
                } else {
                    res = new RideUpdateJoinedOfferResponse(97);
                }
            }
            else {
                res = new RideUpdateJoinedOfferResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleRequestPickup(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideRequestPickupRequest req = null;
            RideRequestPickupResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideRequestPickupRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.getRequestPickupCode(req);
                } else {
                    res = new RideRequestPickupResponse(97, -1);
                }
            }
            else {
                res = new RideRequestPickupResponse(2, -1);
            }
            return gson.toJson(res);
        }

        private String handleOfferPickup(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideOfferPickupRequest req = null;
            RideOfferPickupResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideOfferPickupRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.getOfferPickupCode(req);
                } else {
                    res = new RideOfferPickupResponse(97, -1);
                }
            }
            else {
                res = new RideOfferPickupResponse(2, -1);
            }
            return gson.toJson(res);
        }

        private String handleAcceptedrequestPickup(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideAcceptedRequestPickupRequest req = null;
            RideAcceptedRequestPickupResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideAcceptedRequestPickupRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    AcceptedRequest acceptedRequest = new AcceptedRequest();
                    res = acceptedRequest.getAcceptedRequestPickupCode(req);
                } else {
                    res = new RideAcceptedRequestPickupResponse(97, -1);
                }
            }
            else {
                res = new RideAcceptedRequestPickupResponse(2, -1);
            }
            return gson.toJson(res);
        }

        private String handleJoinedofferPickup(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideJoinedOfferPickupRequest req = null;
            RideJoinedOfferPickupResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideJoinedOfferPickupRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    JoinedOffer joinedOffer = new JoinedOffer();
                    res = joinedOffer.getJoinedOfferPickupCode(req);
                } else {
                    res = new RideJoinedOfferPickupResponse(97, -1);
                }
            }
            else {
                res = new RideJoinedOfferPickupResponse(2, -1);
            }
            return gson.toJson(res);
        }

        private String handleRequestComfirmpickup(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideRequestConfirmRequest req = null;
            RideRequestConfirmResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideRequestConfirmRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.confirmRideRequestPickup(req);
                } else {
                    res = new RideRequestConfirmResponse(97);
                }
            }
            else {
                res = new RideRequestConfirmResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleOfferComfirmpickup(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideOfferConfirmRequest req = null;
            RideOfferConfirmResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideOfferConfirmRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.confirmRideOfferPickup(req);
                } else {
                    res = new RideOfferConfirmResponse(97);
                }
            }
            else {
                res = new RideOfferConfirmResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleAcceptedrequestComfirmpickup(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideAcceptedRequestConfirmRequest req = null;
            RideAcceptedRequestConfirmResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideAcceptedRequestConfirmRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    AcceptedRequest acceptedRequest = new AcceptedRequest();
                    res = acceptedRequest.confirmAcceptedRequestPickup(req);
                } else {
                    res = new RideAcceptedRequestConfirmResponse(97);
                }
            }
            else {
                res = new RideAcceptedRequestConfirmResponse(2);
            }
            return gson.toJson(res);
        }

        private String handleJoinedofferComfirmpickup(Gson gson, JsonObject request, HttpServletRequest servletReq){
            RideJoinedOfferConfirmRequest req = null;
            RideJoinedOfferConfirmResponse res = null;
            HttpSession session = servletReq.getSession(false);
            if(session!=null) {
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideJoinedOfferConfirmRequest.class);
                } catch (JsonSyntaxException e) {
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    JoinedOffer joinedOffer = new JoinedOffer();
                    res = joinedOffer.confirmJoinedOfferPickup(req);
                } else {
                    res = new RideJoinedOfferConfirmResponse(97);
                }
            }
            else {
                res = new RideJoinedOfferConfirmResponse(2);
            }
            return gson.toJson(res);
        }

        @Override
        protected void doPost(HttpServletRequest servletReq, HttpServletResponse servletResp)
                throws ServletException, IOException {
            System.out.println("Request " + uri + " received");

            BufferedReader in = new BufferedReader(servletReq.getReader());

            JsonObject request = (JsonObject) new JsonParser().parse(in);
            Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

            String response = null;

            if (uri.equals("/user/signup")){
                response = handleSignup(gson, request);
            }
            else if (uri.equals("/user/verifyemail")){
                response = handleVerifyemail(gson, request, servletReq);
            }
            else if (uri.equals("/user/login")){
                response = handleLogin(gson, request, servletReq);
            }
            else if (uri.equals("/user/forgotpassword")){
                response = handleForgotpassword(gson, request);
            }
            else if (uri.equals("/user/resetpassword")){
                response = handleResetpassword(gson, request);
            }
            else if (uri.equals("/user/viewaccount")){
                response = handleViewaccount(gson, request, servletReq);
            }
            else if (uri.equals("/user/update")){
                response = handleUserupdate(gson, request, servletReq);
            }
            else if (uri.equals("/user/view/points")){
                response = handleViewPoints(gson, request, servletReq);
            }
            else if (uri.equals("/user/view/transaction")){
                response = handleViewTransaction(gson, request, servletReq);
            }
            else if (uri.equals("/user/logout")){
                response = handleLogout(gson, request, servletReq);
            }
            else if (uri.equals("/ride/view/request")){
                response = handleViewRequest(gson, request, servletReq);
            }
            else if (uri.equals("/ride/view/offer")){
                response = handleViewOffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/view/acceptedrequest")){
                response = handleViewAcceptedrequest(gson, request, servletReq);
            }
           else if (uri.equals("/ride/view/joinedoffer")){
                response = handleViewJoinedOffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/request")){
                response = handleRideRequest(gson, request, servletReq);
            }
            else if (uri.equals("/ride/cancel/request")){
                response = handleCancelRequest(gson, request, servletReq);
            }
            else if (uri.equals("/ride/update/request")){
                response = handleUpdateRequest(gson, request, servletReq);
            }
            else if (uri.equals("/ride/offer")){
                response = handleRideOffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/cancel/offer")){
                response = handleCancelOffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/update/offer")){
                response = handleUpdateOffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/search/request")){
                response = handleSearchRequest(gson, request, servletReq);
            }
            else if (uri.equals("/ride/search/offer")){
                response = handleSearchOffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/search/multiplerequest")){

            }
            else if (uri.equals("/ride/search/multipleoffer")){

            }
            else if (uri.equals("/ride/accept/request")){
                response = handleAcceptRequest(gson, request, servletReq);
            }
            else if (uri.equals("/ride/cancel/acceptedrequest")){
                response = handleCancelAcceptedrequest(gson, request, servletReq);
            }
            else if (uri.equals("/ride/join/offer")){
                response = handleJoinOffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/cancel/joinedoffer")){
                response = handleCancelJoinedoffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/update/joinedoffer")){
                response = handleUpdateJoinedoffer(gson, request, servletReq);
            }
            else if (uri.equals("/ride/request/pickup")){
                response = handleRequestPickup(gson, request, servletReq);
            }
            else if (uri.equals("/ride/offer/pickup")){
                response = handleOfferPickup(gson, request, servletReq);
            }
            else if (uri.equals("/ride/acceptedrequest/pickup")){
                response = handleAcceptedrequestPickup(gson, request, servletReq);
            }
            else if (uri.equals("/ride/joinedoffer/pickup")){
                response = handleJoinedofferPickup(gson, request, servletReq);
            }
            else if (uri.equals("/ride/request/confirmpickup")){
                response = handleRequestComfirmpickup(gson, request, servletReq);
            }
            else if (uri.equals("/ride/offer/confirmpickup")){
                response = handleOfferComfirmpickup(gson, request, servletReq);
            }
            else if (uri.equals("/ride/acceptedrequest/confirmpickup")){
                response = handleAcceptedrequestComfirmpickup(gson, request, servletReq);
            }
            else if (uri.equals("/ride/joinedoffer/confirmpickup")){
                response = handleJoinedofferComfirmpickup(gson, request, servletReq);
            }
            else {
                System.out.println("Request " + uri + " is unknown");
                JsonObject responseObj = new JsonObject();
                responseObj.addProperty("result", 98);
                response = responseObj.toString();
            }

            servletResp.setContentType("application/json");
            servletResp.getWriter().println(response);

            System.out.println("Sent response: " + response.toString());
        }

    }

    private void connect(){
        try {
            //Set up connection to database
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Connected to database...");

            //Set up server
            Server server = new Server(8080);

            HashSessionIdManager idmanager = new HashSessionIdManager();
            server.setSessionIdManager(idmanager);

            ServletContextHandler handler = new ServletContextHandler(server, "/");
            server.setHandler(handler);
            handler.setContextPath("/");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/user/signup")), "/user/signup");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/user/verifyemail")), "/user/verifyemail");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/user/login")), "/user/login");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/user/forgotpassword")), "/user/forgotpassword");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/user/resetpassword")), "/user/resetpassword");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/user/viewaccount")), "/user/viewaccount");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/user/update")), "/user/update");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/user/logout")), "/user/logout");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/view/request")), "/ride/view/request");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/view/offer")), "/ride/view/offer");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/request")), "/ride/request");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/cancel/request")), "/ride/cancel/request");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/update/request")), "/ride/update/request");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/offer")), "/ride/offer");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/cancel/offer")), "/ride/cancel/offer");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/update/offer")), "/ride/update/offer");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/search/request")), "/ride/search/request");
            handler.addServlet(new ServletHolder(new BoilerideServlet("/ride/search/offer")), "/ride/search/offer");

            HashSessionManager manager = new HashSessionManager();
            SessionHandler sessions = new SessionHandler(manager);
            handler.setHandler(sessions);

            server.start();
            server.join();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception{

        BoilerideServer server = new BoilerideServer();

        server.connect();

//        DatabaseCommunicator.rideOfferFrom("Lafayette", new Date(),
//                1, 1, true, true, true, true);
//        DatabaseCommunicator.rideRequestFromTo("Lafayette", "Chicago",
//                new Date(),
//                1, 1, true, true, true, true);

//        RideRequestSearchRequest query1 = new RideRequestSearchRequest("hyuang", "Hillenbrand Dining Court, 3rd Street, West Lafayette, IN 47906", 10.0, "Recreational Sports Center, North Martin Jischke Drive, West Lafayette, IN 47906", 10.0,
//                new Date(), 10000, 1, 1, true, false, false, false);
//        RideOfferSearchRequest query2 = new RideOfferSearchRequest("hyuang", "Recreational Sports Center, North Martin Jischke Drive, West Lafayette, IN 47906", 10.0, "Lawson Computer Science Building, North University Street, West Lafayette, IN 47906", 10.0,
//                new Date(), 10000, 10, 1, 1, false, true, false, false);
//
//        try {
//            RideRequestSearchResponse r1 = RideRequest.search(query1);
//            RideOfferSearchResponse r2 = RideOffer.search(query2);
//            System.out.println(new Gson().toJson(r1));
//            System.out.println(new Gson().toJson(r2));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        GoogleMapAPI gma = new GoogleMapAPI();
//        try {
//            System.out.println(gma.estimate("PMU, 101 Grant St Room 186 101, West Lafayette, IN 47906", "200 North Second Street, Lafayette, IN 47901"));
//            System.out.println(gma.getCity("200 North Second Street, Lafayette, IN 47901"));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //System.out.println(DatabaseCommunicator.loginWithEmailPassword("test3", "test"));
        //System.out.println(DatabaseCommunicator.selectUserByEmail("ryan@mail.com").getNickname());
        //System.out.println("hello");
//        String[] o = {"West Lafayette"};
//        String[] d = {"Chicago"};
//        GeoApiContext c = new GeoApiContext.Builder().apiKey("AIzaSyCgUC4EOMBRtNI32zglDvMveuiiJgW_uOI").build();
//        try {
//            DistanceMatrix m = DistanceMatrixApi.getDistanceMatrix(c, o, d)
//                    //.newRequest(c)
//                    //.origins(o)
//                    //.destinations(d)
//                    //.mode(TravelMode.DRIVING)
//                    //.trafficModel(TrafficModel.OPTIMISTIC)
//                    //.departureTime(new DateTime(System.currentTimeMillis()))
//                    .await();
//            System.out.println("out");
//            DistanceMatrixElement res = m.rows[0].elements[0];
//            System.out.println(res.duration);
//            System.out.println(res.distance);
//            System.out.println(res.durationInTraffic);
//
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //new JsonParser().parse();
//        JsonObject test = new JsonObject();
//        test.addProperty("email", "aaaaa@gmail.com");
//        test.addProperty("password", "aaaaa");
//        test.addProperty("nickname", "bbbbb");
//        test.addProperty("phone", "12345");
//
//        UserSignUpRequest x1 = null;
//        try {
//            x1 = new Gson().fromJson(test, UserSignUpRequest.class);
//        }catch (JsonSyntaxException e){
//            System.out.println("wrong format");
//            }
//        UserSignUpRequest x2 = new Gson().fromJson(test, UserSignUpRequest.class);
//
//        UserSignUpRequest[] arr = {x1, x2};
//
//        System.out.println(x1.getEmail());
//
//        System.out.println(new Gson().toJson(x1));
//        System.out.println(new Gson().toJson(arr));
//
//
//        RideOffer rideOffer = new RideOffer();
//        List<Trip> result = rideOffer.search("A", "E");
//        for (Trip t : result) {
//            for (RideOffer r : t.getRides()) {
//                System.out.println(r.toString());
//            }
//        }

    }
}
