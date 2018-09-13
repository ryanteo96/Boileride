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

    private Statement stmt;
    private Connection conn;
    private DatabaseCommunicator db;

    private BoilerideServer(){
        this.stmt = null;
        this.conn = null;
        this.db = null;
    }

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

            }
            else if (uri.equals("/user/verifyemail")){

            }
            else if (uri.equals("/user/login")){

            }
            else if (uri.equals("/user/forgotpassword")){

            }
            else if (uri.equals("/user/resetpassword")){

            }
            else if (uri.equals("/user/viewaccount")){

            }
            else if (uri.equals("/user/update")){

            }
            else if (uri.equals("/user/logout")){

            }
            else if (uri.equals("/ride/view/request")){

            }
            else if (uri.equals("/ride/view/offer")){

            }
            else if (uri.equals("/ride/view/acceptedrequest")){

            }
            else if (uri.equals("/ride/view/joinedoffer")){

            }
            else if (uri.equals("/ride/request")){

            }
            else if (uri.equals("/ride/cancel/request")){

            }
            else if (uri.equals("/ride/update/request")){

            }
            else if (uri.equals("/ride/offer")){

            }
            else if (uri.equals("/ride/cancel/offer")){

            }
            else if (uri.equals("/ride/update/offer")){

            }
            else if (uri.equals("/ride/search/request")){

            }
            else if (uri.equals("/ride/search/offer")){

            }
            else {
                System.out.println("Request " + uri + " is not handled");
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
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
            this.stmt = this.conn.createStatement();
            System.out.println("Connected to database...");
            this.db = new DatabaseCommunicator(this.conn);

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
                if (this.stmt != null)
                    this.stmt.close();
            } catch (SQLException se2) {
                // nothing we can do
            }
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

//        try {
//            server.conn.close();
//            System.out.println("Disconnected from database...");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
