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

    //private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static Connection conn = null;


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
            //System.out.println(userid);
            rs.close();
            conn.close();
            stmt.close();
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

//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE userid = " + userid);

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
            rs.close();
//            conn.close();
            stmt.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultUser;
    }

    public static User selectUserByEmail(String email) {
        User resultUser = null;

        if(conn == null) {
            connectDB();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM USER WHERE email = '" + email+"'");

            int id = 0 , points = 0, status = 0;
            String nickname = "", password = "", phone = "";

            while (rs.next()) {
                id = rs.getInt("userid");
                nickname = rs.getString("nickname");
                password = rs.getString("password");
                phone = rs.getString("phone");
                points = rs.getInt("points");
                status = rs.getInt("status");
                //email = rs.getString("email");

                //need to add USERID but USER is not taking it
                resultUser = new User( email,  password,  nickname,  phone,  points,  status);
                //System.out.println(nickname + " " +password + " " + id);
            }
            rs.close();
            conn.close();
            stmt.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return resultUser;
    }

    public static int updateSQLUser(int userid, User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();
        String phone = user.getPhone();
        int status = user.getStatus();
        int points = user.getPoints();
        String email = user.getEmail();

        if(conn == null) {
            connectDB();
        }
        try {
            stmt.executeUpdate("UPDATE USER SET nickname = '"+nickname+"', password = '"+password+"', " +
                    "phone = "+phone+", status = "+status+", points = "+points+", email = '"+email+"' WHERE userid = " +userid);

            conn.close();
            stmt.close();
        }
        catch(SQLException e) {
            System.out.println("what");
            e.printStackTrace();
            return 1;
        }

        return 0;
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
//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE USER set status = "+j +" WHERE userid = "+i);
            stmt.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }
    public static int addRideRequest(RideRequest ride){
        int requestedid = 0;
        int isAC = 0;
        int isPets = 0;
        int isFoodndrink = 0;
        int isSmoking = 0;

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
        int requestedby = ride.getRequestedby();
        int passenger = ride.getPassengers();
        int luggage = ride.getLuggage();
        int smoking = isSmoking;
        int foodndrink = isFoodndrink;
        int pets = isPets;
        int AC = isAC;
        int travellingtime = ride.getTravelingtime();
        int price = ride.getPrice();
        int status = ride.getStatus();
        String pickuplocation = ride.getPickuplocation();
        String destination = ride.getDestination();
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

            //Dont know how many condition to check
            rs = stmt.executeQuery("SELECT * FROM RIDEREQUEST WHERE pickuplocation = '"+pickuplocation+"' AND destination = '"+destination+"' AND requestedby = "+requestedby);
            while(rs.next()) {
                requestedid = rs.getInt("requestid");
            }
            //System.out.println(requestedid);
            rs.close();
            conn.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return requestedid;
    }

    public static RideRequest selectRideRequest(int requestid){
        RideRequest rideRequest = null;

        if(conn == null) {
            connectDB();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM RIDEREQUEST WHERE requestid = " + requestid);

            int requestedby = 0;
            int passenger = 0;
            int luggage = 0;
            int travellingtime = 0;
            int price = 0;
            int smoking = 0;
            int foodndrink = 0;
            int pets = 0;
            int AC = 0;
            int status = 0;
            String pickuplocation = "";
            String destination = "";
            String datentime = null;
            boolean smoke = false;
            boolean food =false;
            boolean pet = false;
            boolean ac = false;

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

            rs.close();
            conn.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
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

            rs.close();
            conn.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updateRideRequest(int requestid, RideRequest request){
        int isAC = 0;
        int isPets = 0;
        int isFoodndrink = 0;
        int isSmoking = 0;

        if(request.isAc()) {
            isAC = 1;
        }
        if(request.isPets()) {
            isPets = 1;
        }
        if(request.isFoodndrink()) {
            isFoodndrink = 1;
        }
        if(request.isSmoking()) {
            isSmoking = 1;
        }
        int requestedby = request.getRequestedby();
        int passenger = request.getPassengers();
        int luggage = request.getLuggage();
        int smoking = isSmoking;
        int foodndrink = isFoodndrink;
        int pets = isPets;
        int AC = isAC;
        int travellingtime = request.getTravelingtime();
        int price = request.getPrice();
        //int status = request.getStatus();
        String pickuplocation = request.getPickuplocation();
        String destination = request.getDestination();
        Date datentime = request.getDatentime();

        if(conn == null) {
            connectDB();
        }
        try {
            //took out status (remy says status shouldn't be updated)
            stmt.executeUpdate("UPDATE RIDEREQUEST SET requestedby = "+requestedby +", pickuplocation = '" +pickuplocation+
                    "',  destination = '" +destination+"', datentime = '" +datentime+"', passenger = " +passenger+ ",  luggage = " +luggage+
                    ", smoking = " +smoking+ ", foodndrink = "+foodndrink+", pets = " +pets+", AC = " +AC+", travellingtime = " +travellingtime+
                    ", price = " +price+ " WHERE requestid = " +requestid);

            conn.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int addRideOffer(RideOffer offer){
        int offerid = 0;
        int isAC = 0;
        int isPets = 0;
        int isFoodndrink = 0;
        int isSmoking = 0;

        if(offer.isAc()) {
            isAC = 1;
        }
        if(offer.isPets()) {
            isPets = 1;
        }
        if(offer.isFoodndrink()) {
            isFoodndrink = 1;
        }
        if(offer.isSmoking()) {
            isSmoking = 1;
        }
        int offeredby = offer.getOfferedby();
        int seats = offer.getSeats();
        int luggage = offer.getLuggage();
        int smoking = isSmoking;
        int foodndrink = isFoodndrink;
        int pets = isPets;
        int AC = isAC;
        int travellingtime = offer.getTravelingtime();
        int price = offer.getPrice();
        int status = offer.getStatus();
        int seatsleft = offer.getSeatleft();
        int luggagesleft = offer.getLuggageleft();
        String pickuplocation = offer.getPickuplocation();
        String destination = offer.getDestination();
        Date datentime = offer.getDatentime();
        /*
        String strDate = datentime.toString();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        */
        if(conn == null) {
            connectDB();
        }
        try {
            stmt.executeUpdate("INSERT INTO RIDEOFFER(offeredby, pickuplocation, destination, " +
                    "datentime, seats, luggage, smoking, foodndrink, pets, AC, travellingtime, price, seatsleft, luggagesleft, status) " +
                    "VALUES ( "+offeredby + ", '" + pickuplocation + "', '" +destination + "', '"
                    + datentime + "', " + seats + ", " + luggage +
                    ", " + smoking + ", " + foodndrink+", " +pets+", " +AC+", "+travellingtime+", " +price+ ", " +seatsleft+ ", " +luggagesleft+ ", " +status + ")");

            //Dont know how many condition to check
            rs = stmt.executeQuery("SELECT * FROM RIDEOFFER WHERE pickuplocation = '"+pickuplocation+"' AND destination = '"+destination+"' AND offeredby = "+offeredby);
            while(rs.next()) {
                offerid = rs.getInt("offerid");
            }
            rs.close();
            conn.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return offerid;
    }

    public static DTO.DtoRideOffer[] selectOfferList(int offerid){
        return null;
    }
    public static RideOffer selectRideOffer(int offerid){
        RideOffer rideOffer = null;

        if(conn == null) {
            connectDB();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM RIDEOFFER WHERE offerid = " + offerid);

            int offeredby = 0;
            int luggage = 0;
            int travellingtime = 0;
            int price = 0;
            int smoking = 0;
            int foodndrink = 0;
            int pets = 0;
            int AC = 0;
            int status = 0;
            int seatsleft = 0;
            int luggagesleft = 0;
            int seats = 0;
            String pickuplocation = "";
            String destination = "";
            String datentime = null;
            boolean smoke = false, food =false, pet = false, ac = false;

            while (rs.next()) {
                offeredby = rs.getInt("offeredby");
                pickuplocation = rs.getString("pickuplocation");
                destination = rs.getString("destination");
                datentime = rs.getString("datentime");
                seats = rs.getInt("seats");
                luggage = rs.getInt("luggage");
                smoking = rs.getInt("smoking");
                foodndrink = rs.getInt("foodndrink");
                pets = rs.getInt("pets");
                AC = rs.getInt("AC");
                travellingtime = rs.getInt("travellingtime");
                price = rs.getInt("price");
                status = rs.getInt("status");
                seatsleft = rs.getInt("seatsleft");
                luggagesleft = rs.getInt("luggagesleft");
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

            rideOffer = new RideOffer( offeredby,  pickuplocation,  destination,  timestamp, seats,
                    luggage, smoke, food, pet, ac, travellingtime, price, status, seatsleft, luggagesleft);
           //System.out.println(offeredby + ", " +pickuplocation+ ", " + destination);
            rs.close();
            conn.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rideOffer;
    }

    public static int cancelRideOffer(int offerid){
        if(conn == null) {
            connectDB();
        }
        try {
            stmt.executeUpdate("UPDATE RIDEREQUEST SET status = 2 WHERE offerid = " + offerid);

            //rs.close();
            conn.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updateRideOffer(int offerid, RideOffer offer){
        int isAC = 0;
        int isPets = 0;
        int isFoodndrink = 0;
        int isSmoking = 0;

        if(offer.isAc()) {
            isAC = 1;
        }
        if(offer.isPets()) {
            isPets = 1;
        }
        if(offer.isFoodndrink()) {
            isFoodndrink = 1;
        }
        if(offer.isSmoking()) {
            isSmoking = 1;
        }

        int offeredby = offer.getOfferedby();
        int seats = offer.getSeats();
        int luggage = offer.getLuggage();
        int smoking = isSmoking;
        int foodndrink = isFoodndrink;
        int pets = isPets;
        int AC = isAC;
        int travellingtime = offer.getTravelingtime();
        int price = offer.getPrice();
        //int status = offer.getStatus();
        //int seatsleft = offer.getSeatleft();
        //int luggagesleft = offer.getLuggageleft();
        String pickuplocation = offer.getPickuplocation();
        String destination = offer.getDestination();
        Date datentime = offer.getDatentime();

        if(conn == null) {
            connectDB();
        }
        try {
            //took out status, luggagesleft, seatsleft (remy says shouldn't update them)
            stmt.executeUpdate("UPDATE RIDEOFFER SET offeredby = "+offeredby +", pickuplocation = '" +pickuplocation+
                    "',  destination = '" +destination+"', datentime = '" +datentime+"', seats = " +seats+ ",  luggage = " +luggage+
                    ", smoking = " +smoking+ ", foodndrink = "+foodndrink+", pets = " +pets+", AC = " +AC+", travellingtime = " +travellingtime+
                    ", price = " +price+ " WHERE offerid = " +offerid);

            conn.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }

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
        //User user = new User( "henry@mail.com", "tnc", "armiel", "8888", 1000, 0 );
        //updateSQLUser(1, user);
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-05-18 18:45:11");
        //Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        //System.out.println(timestamp);
        //addUser(user);
        //selectUser(1);

        //RideRequest ride = new RideRequest(3, "HEAVEN", "EARTH", timestamp, 5, 7,true,true,false,false,10000, 100000,0);
        //updateRideOffer(3, ride);
        //addRideOffer(ride);
        //updateRideRequest(11, ride);
        //RideRequest rr = selectRideRequest(6);
        //System.out.println(rr.getDestination() +" "+ rr.getDatentime() + " " + rr.getPickuplocation());
        //cancelRideRequest(7);
        //updateUserStatus(10, 0);
        //selectUserByEmail("okay@mail.com");
        //selectRideOffer(3);
    }
}
