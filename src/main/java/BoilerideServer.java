import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

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

    private static class requestHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            String uri = httpExchange.getRequestURI().toString();
            System.out.println("Request " + uri + " received");

            InputStreamReader in = new InputStreamReader(httpExchange.getRequestBody());

            JsonObject request = (JsonObject) new JsonParser().parse(in);
            Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

            String response = null;

            if (uri.equals("/user/signup")){
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
                response = gson.toJson(res);
            }
            else if (uri.equals("/user/verifyemail")){
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
                }
                else{
                    res = new UserVerifyEmailResponse(97, -1);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/user/login")){
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
                }
                else{
                    res = new UserLoginResponse(97, -1);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/user/forgotpassword")){
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
                    //remove -1 initially is res = new UserForgotPasswordResponse(97, -1);
                    res = new UserForgotPasswordResponse(97);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/user/resetpassword")){
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
                response = gson.toJson(res);
            }
            else if (uri.equals("/user/viewaccount")){
                UserViewAccountRequest req = null;
                UserViewAccountResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, UserViewAccountRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    User user = new User();
                    res = user.viewAccount(req);
                }
                else{
                    res = new UserViewAccountResponse(97, "", "", "");
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/user/update")){
                UserUpdateRequest req = null;
                UserUpdateResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, UserUpdateRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    User user = new User();
                    res = user.updateUser(req, false);
                }
                else{
                    res = new UserUpdateResponse(97);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/user/logout")){
                UserLogoutRequest req = null;
                UserLogoutResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, UserLogoutRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    User user = new User();
                    res = user.logout(req);
                }
                else{
                    res = new UserLogoutResponse(97);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/view/request")){
                RideViewRequestRequest req = null;
                RideViewRequestResponse res = null;
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
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/view/offer")){
                RideViewOfferRequest req = null;
                RideViewOfferResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideViewOfferRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.viewRideOfferfromDB(req);
                }
                else{
                    ArrayList<DtoRideOffer> offerlist = new ArrayList<DtoRideOffer>();
                    res = new RideViewOfferResponse(97, offerlist);
                }
                response = gson.toJson(res);
            }
//            else if (uri.equals("/ride/view/acceptedrequest")){
//                RideViewAcceptedRequestRequest req = gson.fromJson(request, RideViewAcceptedRequestRequest.class);
//                System.out.println("Received: " + req.toString());
//                RideViewAcceptedRequestResponse res = new RideViewAcceptedRequestResponse(0);
//                response = gson.toJson(res);
//            }
//           else if (uri.equals("/ride/view/joinedoffer")){
//                RideViewJoinedOfferRequest req = gson.fromJson(request, RideViewJoinedOfferRequest.class);
//                System.out.println("Received: " + req.toString());
//                RideViewJoinedOfferResponse res = new RideViewJoinedOfferResponse(0);
//                response = gson.toJson(res);
//            }
            else if (uri.equals("/ride/request")){
                RideRequestRequest req = null;
                RideRequestResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideRequestRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.addRideRequestToDB(req);
                }
                else {
                    res = new RideRequestResponse(97, -1);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/cancel/request")){
                RideCancelRequestRequest req = null;
                RideCancelRequestResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideCancelRequestRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.cancelRideRequestInDB(req);
                }
                else{
                    res = new RideCancelRequestResponse(97);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/update/request")){
                RideUpdateRequestRequest req = null;
                RideUpdateRequestResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideUpdateRequestRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideRequest rideRequest = new RideRequest();
                    res = rideRequest.updateRideRequestInDB(req);
                }
                else {
                    res = new RideUpdateRequestResponse(97);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/offer")){
                RideOfferRequest req = null;
                RideOfferResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideOfferRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.addRideOfferToDB(req);
                }
                else {
                    res = new RideOfferResponse(97, -1);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/cancel/offer")){
                RideCancelOfferRequest req = null;
                RideCancelOfferResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideCancelOfferRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.cancelRideOfferInDB(req);
                }
                else{
                    res = new RideCancelOfferResponse(97);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/update/offer")){
                RideUpdateOfferRequest req = null;
                RideUpdateOfferResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideUpdateOfferRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    RideOffer rideOffer = new RideOffer();
                    res = rideOffer.updateRideOfferInDB(req);
                }
                else {
                    res = new RideUpdateOfferResponse(97);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/search/request")){
                RideRequestSearchRequest req = null;
                RideRequestSearchResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideRequestSearchRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    try {
                        res = RideRequest.search(req);
                    } catch (InterruptedException | ApiException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    res = new RideRequestSearchResponse(97);
                }
                response = gson.toJson(res);
            }
            else if (uri.equals("/ride/search/offer")){
                RideOfferSearchRequest req = null;
                RideOfferSearchResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = gson.fromJson(request, RideOfferSearchRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    try {
                        res = RideOffer.search(req);
                    } catch (InterruptedException | ApiException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    res = new RideOfferSearchResponse(97);
                }
                response = gson.toJson(res);
            }
            else {
                System.out.println("Request " + uri + " is unknown");
                JsonObject responseObj = new JsonObject();
                responseObj.addProperty("result", 98);
                response = responseObj.toString();
            }

            httpExchange.getResponseHeaders().set("Content-Type", "application/json");
            httpExchange.sendResponseHeaders(200, response.length());

            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());

            os.close();

            System.out.println("Sent response: " + response.toString());
        }

    }

    private void connect(){

        try{
            //Set up connection to database
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database...");
            //Set up server
            HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);
            server.createContext("/", new requestHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Connected to server...");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (IOException e) {
            //Handle errors for HttpServer
            e.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

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
