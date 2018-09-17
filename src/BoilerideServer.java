import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.Iterator;

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

            JSONParser parser = new JSONParser();
            JSONObject jsonRequestObj = null;
            try {
                Object requestObj = parser.parse(in);
                jsonRequestObj = (JSONObject)requestObj;

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (uri.equals("/user/signup")){
                String email = jsonRequestObj.get("email").toString();
                String password = jsonRequestObj.get("password").toString();
                String nickname = jsonRequestObj.get("nickname").toString();
                String phone = jsonRequestObj.get("phone").toString();
                System.out.println("Received: " + email + " " + password + " " + nickname + " " + phone);
            }
            else if (uri.equals("/user/verifyemail")){
                String userid = jsonRequestObj.get("userid").toString();
                String code = jsonRequestObj.get("code").toString();
                System.out.println("Received: " + userid + " " + code);
            }
            else if (uri.equals("/user/login")){
                String email = jsonRequestObj.get("email").toString();
                String password = jsonRequestObj.get("password").toString();
                System.out.println("Received: " + email + " " + password);
            }
            else if (uri.equals("/user/forgotpassword")){
                String email = jsonRequestObj.get("email").toString();
                System.out.println("Received: " + email);
            }
            else if (uri.equals("/user/resetpassword")){
                String userid = jsonRequestObj.get("userid").toString();
                String code  = jsonRequestObj.get("code").toString();
                String newpassword  = jsonRequestObj.get("newpassword").toString();
                System.out.println("Received: " + userid + " " + code + " " + newpassword);
            }
            else if (uri.equals("/user/viewaccount")){
                String userid = jsonRequestObj.get("userid").toString();
                System.out.println("Received: " + userid);
            }
            else if (uri.equals("/user/update")){
                String userid = jsonRequestObj.get("userid").toString();
                String email = jsonRequestObj.get("email").toString();
                String password = jsonRequestObj.get("password").toString();
                String nickname = jsonRequestObj.get("nickname").toString();
                String phone = jsonRequestObj.get("phone").toString();
                System.out.println("Received: " + userid + " " + email + " " + password + " " + nickname + " " + phone);
            }
            else if (uri.equals("/user/logout")){
                String userid = jsonRequestObj.get("userid").toString();
                System.out.println("Received: " + userid);
            }
            else if (uri.equals("/ride/view/request")){
                String userid = jsonRequestObj.get("userid").toString();
                System.out.println("Received: " + userid);
            }
            else if (uri.equals("/ride/view/offer")){
                String userid = jsonRequestObj.get("userid").toString();
                System.out.println("Received: " + userid);
            }
            else if (uri.equals("/ride/view/acceptedrequest")){
                String userid = jsonRequestObj.get("userid").toString();
                System.out.println("Received: " + userid);
            }
            else if (uri.equals("/ride/view/joinedoffer")){
                String userid = jsonRequestObj.get("userid").toString();
                System.out.println("Received: " + userid);
            }
            else if (uri.equals("/ride/request")){
                String userid = jsonRequestObj.get("userid").toString();
                String pickuplocation = jsonRequestObj.get("pickuplocation").toString();
                String destination = jsonRequestObj.get("destination").toString();
                String datentime = jsonRequestObj.get("datentime").toString();
                String passengers = jsonRequestObj.get("passengers").toString();
                String luggage = jsonRequestObj.get("luggage").toString();
                String smoking = jsonRequestObj.get("smoking").toString();
                String foodndrink = jsonRequestObj.get("foodndrink").toString();
                String pets = jsonRequestObj.get("pets").toString();
                String ac = jsonRequestObj.get("ac").toString();
                String travelingtime = jsonRequestObj.get("travelingtime").toString();
                String price = jsonRequestObj.get("price").toString();
                System.out.println("Received: " + userid + " " + pickuplocation + " " + destination + " "
                        + datentime + " " + passengers + " " + luggage + " " + smoking + " " + foodndrink + " "
                        + pets + " " + ac + " " + travelingtime + " " + price);
            }
            else if (uri.equals("/ride/cancel/request")){
                String userid = jsonRequestObj.get("userid").toString();
                System.out.println("Received: " + userid);
            }
            else if (uri.equals("/ride/update/request")){
                String userid = jsonRequestObj.get("userid").toString();
                String requestid = jsonRequestObj.get("requestid").toString();
                String pickuplocation = jsonRequestObj.get("pickuplocation").toString();
                String destination = jsonRequestObj.get("destination").toString();
                String datentime = jsonRequestObj.get("datentime").toString();
                String passengers = jsonRequestObj.get("passengers").toString();
                String luggage = jsonRequestObj.get("luggage").toString();
                String smoking = jsonRequestObj.get("smoking").toString();
                String foodndrink = jsonRequestObj.get("foodndrink").toString();
                String pets = jsonRequestObj.get("pets").toString();
                String ac = jsonRequestObj.get("ac").toString();
                String travelingtime = jsonRequestObj.get("travelingtime").toString();
                String price = jsonRequestObj.get("price").toString();
                System.out.println("Received: " + userid + " " + requestid + " " + pickuplocation + " "
                        + destination + " " + datentime + " " + passengers + " " + luggage + " " + smoking + " "
                        + foodndrink + " " + pets + " " + ac + " " + travelingtime + " " + price);
            }
            else if (uri.equals("/ride/offer")){
                String userid = jsonRequestObj.get("userid").toString();
                String pickuplocation = jsonRequestObj.get("pickuplocation").toString();
                String destination = jsonRequestObj.get("destination").toString();
                String datentime = jsonRequestObj.get("datentime").toString();
                String seats = jsonRequestObj.get("seats").toString();
                String luggage = jsonRequestObj.get("luggage").toString();
                String smoking = jsonRequestObj.get("smoking").toString();
                String foodndrink = jsonRequestObj.get("foodndrink").toString();
                String pets = jsonRequestObj.get("pets").toString();
                String ac = jsonRequestObj.get("ac").toString();
                String travelingtime = jsonRequestObj.get("travelingtime").toString();
                String price = jsonRequestObj.get("price").toString();
                System.out.println("Received: " + userid + " " + pickuplocation + " " + destination + " "
                        + datentime + " " + seats + " " + luggage + " " + smoking + " " + foodndrink + " "
                        + pets + " " + ac + " " + travelingtime + " " + price);
            }
            else if (uri.equals("/ride/cancel/offer")){
                String userid = jsonRequestObj.get("userid").toString();
                System.out.println("Received: " + userid);
            }
            else if (uri.equals("/ride/update/offer")){
                String userid = jsonRequestObj.get("userid").toString();
                String offerid = jsonRequestObj.get("offerid").toString();
                String pickuplocation = jsonRequestObj.get("pickuplocation").toString();
                String destination = jsonRequestObj.get("destination").toString();
                String datentime = jsonRequestObj.get("datentime").toString();
                String seats = jsonRequestObj.get("seats").toString();
                String luggage = jsonRequestObj.get("luggage").toString();
                String smoking = jsonRequestObj.get("smoking").toString();
                String foodndrink = jsonRequestObj.get("foodndrink").toString();
                String pets = jsonRequestObj.get("pets").toString();
                String ac = jsonRequestObj.get("ac").toString();
                String travelingtime = jsonRequestObj.get("travelingtime").toString();
                String price = jsonRequestObj.get("price").toString();
                System.out.println("Received: " + userid + " " + offerid + " " + pickuplocation + " "
                        + destination + " " + datentime + " " + seats + " " + luggage + " " + smoking + " "
                        + foodndrink + " " + pets + " " + ac + " " + travelingtime + " " + price);
            }
            else if (uri.equals("/ride/search/request")){
                String userid = jsonRequestObj.get("userid").toString();
                String pickuplocation = jsonRequestObj.get("pickuplocation").toString();
                String destination = jsonRequestObj.get("destination").toString();
                String pickupproximity = jsonRequestObj.get("pickupproximity").toString();
                String destinationproximity = jsonRequestObj.get("destinationproximity").toString();
                String datentime = jsonRequestObj.get("datentime").toString();
                String datentimerange = jsonRequestObj.get("datentimerange").toString();
                String seats = jsonRequestObj.get("seats").toString();
                String luggage = jsonRequestObj.get("luggage").toString();
                String smoking = jsonRequestObj.get("smoking").toString();
                String foodndrink = jsonRequestObj.get("foodndrink").toString();
                String pets = jsonRequestObj.get("pets").toString();
                String ac = jsonRequestObj.get("ac").toString();
                System.out.println("Received: " + userid + " " + pickuplocation + " " + destination + " "
                        + pickupproximity + " " + datentime + " " + datentimerange + " " + seats + " "
                        + luggage + " " + smoking + " " + foodndrink + " " + pets + " " + ac + " ");
            }
            else if (uri.equals("/ride/search/offer")){
                String userid = jsonRequestObj.get("userid").toString();
                String pickuplocation = jsonRequestObj.get("pickuplocation").toString();
                String destination = jsonRequestObj.get("destination").toString();
                String pickupproximity = jsonRequestObj.get("pickupproximity").toString();
                String destinationproximity = jsonRequestObj.get("destinationproximity").toString();
                String datentime = jsonRequestObj.get("datentime").toString();
                String datentimerange = jsonRequestObj.get("datentimerange").toString();
                String passengers = jsonRequestObj.get("passengers").toString();
                String luggage = jsonRequestObj.get("luggage").toString();
                String smoking = jsonRequestObj.get("smoking").toString();
                String foodndrink = jsonRequestObj.get("foodndrink").toString();
                String pets = jsonRequestObj.get("pets").toString();
                String ac = jsonRequestObj.get("ac").toString();
                System.out.println("Received: " + userid + " " + pickuplocation + " " + destination + " "
                        + pickupproximity + " " + datentime + " " + datentimerange + " " + passengers + " "
                        + luggage + " " + smoking + " " + foodndrink + " " + pets + " " + ac + " ");
            }
            else {
                System.out.println("Request " + uri + " is unknown");
            }

            JSONObject responseObj = new JSONObject();
            responseObj.put("result", new Integer(0));
            String response = responseObj.toJSONString();

            httpExchange.getResponseHeaders().set("Content-Type", "application/json");
            httpExchange.sendResponseHeaders(200, response.length());

            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());

            os.close();
        }

    }

    private void connect(){
        //Set up connection to database

        try{

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to database...");

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

        int ret = SendEmail.sendEmail("ochow@purdue.edu", "Boileride", "Boileride email sent!");
        System.out.println(ret);

//        try {
//            server.conn.close();
//            System.out.println("Disconnected from database...");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
