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
    static final String DB_URL = "jdbc:mysql://localhost:3306/boileridedb?useSSL=false";

    static final String USER = "backend";
    static final String PASS = "boileride18";

    static Statement stmt;

    private static class testHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {

            System.out.println(httpExchange.getRequestMethod());
            System.out.println(httpExchange.getRequestURI());
            InputStreamReader in = new InputStreamReader(httpExchange.getRequestBody());
//            BufferedReader inbuf = new BufferedReader(in);
//            String inline;
//            StringBuffer request = new StringBuffer();
//            while ((inline = inbuf.readLine()) != null) {
//                request.append(inline);
//            }
//            inbuf.close();
//            System.out.println(in.toString());
            JSONParser parser = new JSONParser();
            try {
                Object requestObj = parser.parse(in);
                JSONObject jsonObj = (JSONObject)requestObj;
                System.out.println(jsonObj);
                System.out.println(jsonObj.get("hate"));
                JSONArray msg = (JSONArray) jsonObj.get("messages");
                Iterator<String> iterator = msg.iterator();
                while (iterator.hasNext()){
                    System.out.println(iterator.next());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

//            String response = "This is the response";

            JSONObject responseObj = new JSONObject();
            responseObj.put("age", new Integer(100));
            JSONArray responseMsg = new JSONArray();
            responseMsg.add("x");
            responseMsg.add("y");
            responseMsg.add("z");
            responseObj.put("messages", responseMsg);
            String response = responseObj.toJSONString();

            httpExchange.getResponseHeaders().set("Content-Type", "application/json");
            httpExchange.sendResponseHeaders(200, response.length());

            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());

            os.close();
        }
    }

    public static void main(String[] args) {

        //Set up connection to database
        Connection conn = null;
        stmt = null;

        try{

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            HttpServer server = HttpServer.create(new InetSocketAddress(8080),0);
            server.createContext("/test", new testHandler());
            server.setExecutor(null);
            server.start();

            stmt.close();
            conn.close();


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
                if (stmt != null)
                    stmt.close();
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
}
