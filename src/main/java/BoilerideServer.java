import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import com.google.gson.*;


import java.io.*;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

import DTO.*;

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
            String response = null;

            if (uri.equals("/user/signup")){
                UserSignUpRequest req = new Gson().fromJson(request, UserSignUpRequest.class);
                System.out.println("Received: " + req.toString());
//                UserSignUpResponse res = new UserSignUpResponse(0, 0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/user/verifyemail")){
                UserVerifyEmailRequest req = new Gson().fromJson(request, UserVerifyEmailRequest.class);
                System.out.println("Received: " + req.toString());
//                UserVerifyEmailResponse res = new UserVerifyEmailResponse(0, 0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/user/login")){
                UserLoginRequest req = new Gson().fromJson(request, UserLoginRequest.class);
                System.out.println("Received: " + req.toString());
//                UserLoginResponse res = new UserLoginResponse(0, 0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/user/forgotpassword")){
                UserForgotPasswordRequest req = new Gson().fromJson(request, UserForgotPasswordRequest.class);
                System.out.println("Received: " + req.toString());
//                UserForgotPasswordResponse res = new UserForgotPasswordResponse(0, 0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/user/resetpassword")){
                UserResetPasswordRequest req = new Gson().fromJson(request, UserResetPasswordRequest.class);
                System.out.println("Received: " + req.toString());
//                UserResetPasswordResponse res = new UserResetPasswordResponse(0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/user/viewaccount")){
                UserViewAccountRequest req = new Gson().fromJson(request, UserViewAccountRequest.class);
                System.out.println("Received: " + req.toString());
//                UserViewAccountResponse res = new UserViewAccountResponse();
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/user/update")){
                UserUpdateRequest req = new Gson().fromJson(request, UserUpdateRequest.class);
                System.out.println("Received: " + req.toString());
//                UserUpdateResponse res = new UserUpdateResponse(0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/user/logout")){
                UserLoginRequest req = new Gson().fromJson(request, UserLoginRequest.class);
                System.out.println("Received: " + req.toString());
//                UserLoginResponse res = new UserLoginResponse(0, 0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/view/request")){
                RideViewRequestRequest req = new Gson().fromJson(request, RideViewRequestRequest.class);
                System.out.println("Received: " + req.toString());
//                RideViewRequestResponse res = new RideViewRequestResponse(0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/view/offer")){
                RideViewOfferRequest req = new Gson().fromJson(request, RideViewOfferRequest.class);
                System.out.println("Received: " + req.toString());
//                RideViewOfferResponse res = new RideViewOfferResponse(0);
//                response = new Gson().toJson(res);
            }
//            else if (uri.equals("/ride/view/acceptedrequest")){
//                RideViewAcceptedRequestRequest req = new Gson().fromJson(request, RideViewAcceptedRequestRequest.class);
//                System.out.println("Received: " + req.toString());
//                RideViewAcceptedRequestResponse res = new RideViewAcceptedRequestResponse(0);
//                response = new Gson().toJson(res);
//            }
//           else if (uri.equals("/ride/view/joinedoffer")){
//                RideViewJoinedOfferRequest req = new Gson().fromJson(request, RideViewJoinedOfferRequest.class);
//                System.out.println("Received: " + req.toString());
//                RideViewJoinedOfferResponse res = new RideViewJoinedOfferResponse(0);
//                response = new Gson().toJson(res);
//            }
            else if (uri.equals("/ride/request")){
                RideRequestRequest req = null;
                RideRequestResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = new Gson().fromJson(request, RideRequestRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    res = new RideRequestResponse(0, 0);
                }
                else {
                    res = new RideRequestResponse(97, -1);
                }
                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/cancel/request")){
                RideCancelRequestRequest req = new Gson().fromJson(request, RideCancelRequestRequest.class);
                System.out.println("Received: " + req.toString());
//                RideCancelRequestResponse res = new RideCancelRequestResponse(0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/update/request")){
                RideUpdateRequestRequest req = null;
                RideUpdateRequestResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = new Gson().fromJson(request, RideUpdateRequestRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    res = new RideUpdateRequestResponse(0);
                }
                else {
                    res = new RideUpdateRequestResponse(97);
                }
                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/offer")){
                RideOfferRequest req = null;
                RideOfferResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = new Gson().fromJson(request, RideOfferRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    res = new RideOfferResponse(0, 0);
                }
                else {
                    res = new RideOfferResponse(97, -1);
                }
                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/cancel/offer")){
                RideCancelOfferRequest req = new Gson().fromJson(request, RideCancelOfferRequest.class);
                System.out.println("Received: " + req.toString());
//                RideCancelOfferResponse res = new RideCancelOfferResponse(0);
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/update/offer")){
                RideUpdateOfferRequest req = null;
                RideUpdateOfferResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = new Gson().fromJson(request, RideUpdateOfferRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
                    res = new RideUpdateOfferResponse(0);
                }
                else {
                    res = new RideUpdateOfferResponse(97);
                }
                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/search/request")){
                RideSearchRequestRequest req = null;
//                RideSearchRequestResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = new Gson().fromJson(request, RideSearchRequestRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
//                    res = new RideSearchRequestResponse(0);
                }
                else {
//                    res = new RideSearchRequestResponse(97);
                }
//                response = new Gson().toJson(res);
            }
            else if (uri.equals("/ride/search/offer")){
                RideSearchOfferRequest req = null;
//                RideSearchOfferResponse res = null;
                boolean isRightFormat = true;
                try {
                    req = new Gson().fromJson(request, RideSearchOfferRequest.class);
                }catch (JsonSyntaxException e){
                    isRightFormat = false;
                }
                if (isRightFormat) {
                    System.out.println("Received: " + req.toString());
//                    res = new RideSearchOfferResponse(0);
                }
                else {
//                    res = new RideSearchOfferResponse(97);
                }
//                response = new Gson().toJson(res);
            }
            else {
                System.out.println("Request " + uri + " is unknown");
                JsonObject responseObj = new JsonObject();
                responseObj.addProperty("result", 98);
                response = responseObj.toString();
            }

            System.out.println("Sent response: " + response.toString());

            httpExchange.getResponseHeaders().set("Content-Type", "application/json");
            httpExchange.sendResponseHeaders(200, response.length());

            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());

            os.close();
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
        }finally {
            //finally block used to close resources
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        BoilerideServer server = new BoilerideServer();

        server.connect();

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
