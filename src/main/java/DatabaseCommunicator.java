import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DatabaseCommunicator class send queries to database and return results back to server
 *
 * @version September 13, 2018
 */

public class DatabaseCommunicator {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://142.93.19.7:3306/boileridedb?useSSL=false";
    private static final String USER = "backend";
    private static final String PASSWORD = "Boileride18!";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;


    // Connect this thread to the database.
    private static void connectDB() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int addUser(User user){
        String nickname = user.getNickname();
        String password = user.getPassword();
        String phone = user.getPhone();
        int status = user.getStatus();
        int points = user.getPoints();
        String email = user.getEmail();

        int userid = 0;
        if(conn == null) {
            connectDB();
        }
        try {
            stmt.executeUpdate("INSERT INTO USER(nickname, password, phone, status, points, email) " +
                    "VALUES ('" + nickname + "','" + password + "','" + phone + "',"
                    + status + "," + points + ",'" + email + "')");

            //stmt.executeUpdate("INSERT INTO USER(nickname, password, phone, status, points, email) VALUES ('test','test','test',1,0,'test3')");
            rs = stmt.executeQuery("SELECT * FROM USER WHERE email = " + "'"+ email+"'");
            while(rs.next()) {
                userid = rs.getInt("userid");
            }
            System.out.println(userid);
            conn.close();
        }
        catch(SQLException e) {
            System.out.println("what");
            e.printStackTrace();
            return 0;
        }

        return userid;
    }

    public static User selectUser(int userid){
        User resultUser = null;

        if(conn == null) {
            connectDB();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM USER WHERE userid = " + userid);

            int id = 0 , points = 0, status = 0;
            String nickname = "", password = "", phone = "", email = "";

            while (rs.next()) {
                //id = rs.getInt("userid");
                nickname = rs.getString("nickname");
                password = rs.getString("password");
                phone = rs.getString("phone");
                points = rs.getInt("points");
                status = rs.getInt("status");
                email = rs.getString("email");
                //System.out.println("id = "+ id + ", nickname = " + nickname + ", password =" + password + ", phone = " + phone + ", points = " + points + ", status = " + status + ", email = " + email);User(String email, String password, String nickname, String phone, int points, int status)
                resultUser = new User( email,  password,  nickname,  phone,  points,  status);
            }
            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultUser;
    }

    /*
        public static RideRequest[] selectRequestList(int userid){
            return null;
        }

        public static RideOffer[] selectOfferList(int userid){
            return null;
        }
    */

    public static int updateUserStatus(int i, int j) {
        return 0;
    }
    public static int addRideRequest(RideRequest ride){
        int isAC = 0, isPets = 0, isFoodndrink = 0, isSmoking = 0;
        if(ride.isAc()) {
            isAC = 1;
        }
        if(ride.isPets()) {
            isPets = 1;
        }
        if(ride.isFoodndrink()) {
            isFoodndrink = 1;
        }
        if(ride.isSmoking()) {
            isSmoking = 1;
        }
        int requestedby = ride.getRequestedby(), passenger = ride.getPassengers(), luggage = ride.getLuggage(), smoking = isSmoking,
                foodndrink = isFoodndrink, pets = isPets, AC = isAC, travellingtime = ride.getTravelingtime(), price = ride.getPrice(), status = ride.getStatus();
        String pickuplocation = ride.getPickuplocation(), destination = ride.getDestination();

        Date datentime = ride.getDatentime();
        String strDate = datentime.toString();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new java.sql.Timestamp(date.getTime());

        if(conn == null) {
            connectDB();
        }
        try {
            stmt.executeUpdate("INSERT INTO RIDEREQUEST(requestedby, pickuplocation, destination, " +
                    "datentime, passenger, luggage, smoking, foodndrink, pets, AC, travellingtime, price, status) " +
                    "VALUES ( "+requestedby + ", '" + pickuplocation + "', '" +destination + "', '"
                    + timestamp + "', " + passenger + ", " + luggage +
                    ", " + smoking + ", " + foodndrink+", " +pets+", " +AC+", "+travellingtime+", " +price+ ", " +status + ")");


            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static RideRequest selectRideRequest(int requestid){
        RideRequest rideRequest = null;

        if(conn == null) {
            connectDB();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM RIDEREQUEST WHERE requestid = " + requestid);

            int requestedby = 0, passenger = 0, luggage = 0,
                    travellingtime = 0, price = 0, smoking = 0, foodndrink = 0, pets = 0, AC = 0, status = 0;
            String pickuplocation = "", destination = "";
            String datentime = null;
            boolean smoke = false, food =false, pet = false, ac = false;



            while (rs.next()) {
                requestedby = rs.getInt("requestedby");
                pickuplocation = rs.getString("pickuplocation");
                destination = rs.getString("destination");
                datentime = rs.getString("datentime");
                //time = rs.getTime("datentime");
                passenger = rs.getInt("passenger");
                luggage = rs.getInt("luggage");
                smoking = rs.getInt("smoking");
                foodndrink = rs.getInt("foodndrink");
                pets = rs.getInt("pets");
                AC = rs.getInt("AC");
                travellingtime = rs.getInt("travellingtime");
                price = rs.getInt("price");
                status = rs.getInt("status");
            }

            if(smoking == 1) {
                smoke = true;
            }
            if(foodndrink == 1) {
                food = true;
            }
            if(pets == 1) {
                pet = true;
            }
            if(AC == 1) {
                ac = true;
            }

            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datentime);
            Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            rideRequest = new RideRequest( requestedby,  pickuplocation,  destination,  timestamp,  passenger,
                    luggage, smoke, food, pet, ac, travellingtime, price, status);
            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rideRequest;
    }

    public static DTO.DtoRideRequest[] selectRequestList(int requestid){
        return null;
    }

    public static int cancelRideRequest(int requestid){
        if(conn == null) {
            connectDB();
        }
        try {
            stmt.executeUpdate("UPDATE RIDEREQUEST SET status = 2 WHERE requestid = " + requestid);

            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updateRideRequest(int requestid, RideRequest request){
        return 0;
    }

    public static int addRideOffer(RideOffer offer){
        return 0;
    }

    public static DTO.DtoRideOffer[] selectOfferList(int offerid){
        return null;
    }
    public static RideOffer selectRideOffer(int offerid){
        return null;
    }

    public static int cancelRideOffer(int offerid){
        return 0;
    }

    public static int updateRideOffer(int offerid, RideOffer offer){
        return 0;
    }

    public static User [ ] selectUserJoinedOffer(int offerid){
        User[] users = null;
        return users;
    }

    public static User [ ] selectUserAcceptedRequest(int requestid){
        User[] users = null;
        return users;
    }


    public static void main (String args[]) throws ParseException {
        // User user = new User( "okay@mail.com", "lala", "ryan", "5678", 50, 1 );
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2011-05-18 16:29:31");
        //Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        //System.out.println(timestamp);
        //addUser(user);
        //selectUser(1);

        //RideRequest ride = new RideRequest(1, "purdue", "chicago", timestamp, 2, 2,false,false,false,false,10, 100, 1);
        //addRideRequest(ride);

        //RideRequest rr = selectRideRequest(6);
        //System.out.println(rr.getDestination() +" "+ rr.getDatentime() + " " + rr.getPickuplocation());
        cancelRideRequest(7);
    }
}
