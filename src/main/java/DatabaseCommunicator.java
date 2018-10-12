import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import DTO.*;
/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DatabaseCommunicator class send queries to database and return results back to server
 *
 * @version September 13, 2018
 */

public class DatabaseCommunicator {
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://142.93.19.7:3306/boileridedb?useSSL=false";
//    private static final String USER = "backend";
//    private static final String PASSWORD = "Boileride18!";

    //private static Connection conn = null;
    /*
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static Connection conn = null;
*/

    // Connect this thread to the database.
    /*
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
    */
    public static int addUser(User user){
        String nickname = user.getNickname();
        String password = user.getPassword();
        String phone = user.getPhone();
        int status = user.getStatus();
        int points = user.getPoints();
        String email = user.getEmail();

        int userid = -1;
//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("INSERT INTO USER(nickname, password, phone, status, points, email) " +
                    "VALUES ('" + nickname + "','" + password + "','" + phone + "',"
                    + status + "," + points + ",'" + email + "')");

            //stmt.executeUpdate("INSERT INTO USER(nickname, password, phone, status, points, email) VALUES ('test','test','test',1,0,'test3')");
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE email = " + "'"+ email+"'");
            while(rs.next()) {
                userid = rs.getInt("userid");
            }
            //System.out.println(userid);
            rs.close();
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

            int id = 0 , points = 0, reserve = 0, status = 0;
            String nickname = "", password = "", phone = "", email = "";

            if (rs.next()) {
                id = rs.getInt("userid");
                nickname = rs.getString("nickname");
                phone = rs.getString("phone");
                points = rs.getInt("points");
                reserve = rs.getInt("reserve");
                status = rs.getInt("status");
                email = rs.getString("email");
                password = rs.getString("password");
                //System.out.println("id = "+ id + ", nickname = " + nickname + ", password =" + password + ", phone = " + phone + ", points = " + points + ", status = " + status + ", email = " + email);User(String email, String password, String nickname, String phone, int points, int status)
                resultUser = new User( email,  password,  nickname,  phone,  points,  reserve,  status,  id);
            }
            rs.close();
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

//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE email = '" + email+"'");

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
                resultUser = new User( email, password,  nickname,  phone,  points,  status, id);
                //System.out.println(nickname + " " +password + " " + id);
            }
            rs.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return resultUser;
    }

    public static int loginWithEmailPassword(String email, String password) {
        //User resultUser = null;
        int userid = -1;

//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT userid FROM USER WHERE email = '" + email+"' AND password = '"+password+"'");
            //int id = 0;
            //System.out.println(rs.getInt(userid));

            if (rs.next()) {
                userid = rs.getInt("userid");

                //userid = id;
                System.out.println(userid);
            }
            rs.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return userid;
    }

    public static int updateSQLUser(int userid, User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();
        String phone = user.getPhone();
        int status = user.getStatus();
        int points = user.getPoints();
        String email = user.getEmail();


//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE USER SET nickname = '"+nickname+"', password = '"+password+"', " +
                    "phone = "+phone+", status = "+status+", points = "+points+", email = '"+email+"' WHERE userid = " +userid);

            stmt.close();
        }
        catch(SQLException e) {
            System.out.println("what");
            e.printStackTrace();
            return 1;
        }

        return 0;
    }

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
        int requestedid = -1;

        int requestedby = ride.getRequestedby();
        int passenger = ride.getPassengers();
        int luggage = ride.getLuggage();
        boolean smoking = ride.isSmoking();
        boolean foodndrink = ride.isFoodndrink();
        boolean pets = ride.isPets();
        boolean ac = ride.isAc();
        int travellingtime = ride.getTravelingtime();
        int price = ride.getPrice();
        int status = ride.getStatus();
        String pickuplocation = ride.getPickuplocation();
        String destination = ride.getDestination();
        Date datentime = ride.getDatentime();
        Timestamp timestamp = new java.sql.Timestamp(datentime.getTime());

//        if(conn == null) {
//            connectDB();
//        }
        try {
            String query = "INSERT INTO RIDEREQUEST(requestedby, pickuplocation, destination, " +
                    "datentime, passenger, luggage, smoking, foodndrink, pets, AC, travellingtime, price, status) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt =  BoilerideServer.conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,requestedby);
            stmt.setString(2,pickuplocation);
            stmt.setString(3,destination);
            stmt.setTimestamp(4,timestamp);
            stmt.setInt(5,passenger);
            stmt.setInt(6,luggage);
            stmt.setBoolean(7,smoking);
            stmt.setBoolean(8,foodndrink);
            stmt.setBoolean(9,pets);
            stmt.setBoolean(10,ac);
            stmt.setInt(11,travellingtime);
            stmt.setInt(12,price);
            stmt.setInt(13,status);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                requestedid = rs.getInt(1);
            }

            rs.close();
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

//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDEREQUEST WHERE requestid = " + requestid);

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
            String datentimeStr = null;
            boolean smoke = false;
            boolean food =false;
            boolean pet = false;
            boolean ac = false;

            if (rs.next()) {
                requestedby = rs.getInt("requestedby");
                pickuplocation = rs.getString("pickuplocation");
                destination = rs.getString("destination");
                datentimeStr = rs.getString("datentime");
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


                if (smoking == 1) {
                    smoke = true;
                }
                if (foodndrink == 1) {
                    food = true;
                }
                if (pets == 1) {
                    pet = true;
                }
                if (AC == 1) {
                    ac = true;
                }

                Date datentime = null;
                try {
                    datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                rideRequest = new RideRequest(requestedby, pickuplocation, destination, datentime, passenger,
                        luggage, smoke, food, pet, ac, travellingtime, price, status);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return rideRequest;
    }

    public static ArrayList<DtoRideRequest> selectRequestList(int userid){
        ArrayList<DtoRideRequest> requestlist = new ArrayList<DtoRideRequest>();

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT r.*, u.nickname, a FROM RIDEREQUEST WHERE requestedby = " + userid);
            //ResultSet rs = stmt.executeQuery("select r.*, u1.nickname as requestedbyname, u2.nickname as acceptedbyname, a.userid as acceptedby, u2.phone from RIDEREQUEST r JOIN USER u1 on r.requestedby = u1.userid JOIN ACCEPTEDRIDEREQUEST a on r.requestid = a.requestid JOIN USER u2 on a.userid = u2.userid WHERE requestedby = " + userid);
            ResultSet rs = stmt.executeQuery("select r.*, u1.nickname as requestedbyname, u2.nickname as acceptedbyname, a.userid as acceptedby, u2.phone, a.requestuserstatus " +
                    "FROM RIDEREQUEST r, USER u1, USER u2, ACCEPTEDRIDEREQUEST a WHERE r.requestedby = u1.userid and r.requestid = a.requestid and " +
                    "a.userid = u2.userid and r.requestedby = " + userid);
            while (rs.next()){
                int requestid = -1;
                int requestedby = -1;
                String requestedbyname = "";
                String pickuplocation = "";
                String destination = "";
                String datentimeStr = null;
                int passenger = 0;
                int luggage = 0;
                int smoking = 0;
                int foodndrink = 0;
                int pets = 0;
                int AC = 0;
                boolean smoke = false;
                boolean food =false;
                boolean pet = false;
                boolean ac = false;
                int travellingtime = 0;
                int price = 0;
                int status = 0;
                int acceptedby = -1;
                String acceptedbyname = "";
                String phone = "";
                int requestuserstatus = -1;

                requestid = rs.getInt("requestid");
                requestedby = rs.getInt("requestedby");
                requestedbyname = rs.getString("requestedbyname");
                pickuplocation = rs.getString("pickuplocation");
                destination = rs.getString("destination");
                datentimeStr = rs.getString("datentime");
                passenger = rs.getInt("passenger");
                luggage = rs.getInt("luggage");
                smoking = rs.getInt("smoking");
                foodndrink = rs.getInt("foodndrink");
                pets = rs.getInt("pets");
                AC = rs.getInt("AC");
                travellingtime = rs.getInt("travellingtime");
                price = rs.getInt("price");
                status = rs.getInt("status");
                acceptedby = rs.getInt("acceptedby");
                acceptedbyname = rs.getString("acceptedbyname");
                phone = rs.getString("phone");
                requestuserstatus = rs.getInt("requestuserstatus");

                if (smoking == 1) {
                    smoke = true;
                }
                if (foodndrink == 1) {
                    food = true;
                }
                if (pets == 1) {
                    pet = true;
                }
                if (AC == 1) {
                    ac = true;
                }

                Date datentime = null;
                try {
                    datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                DtoRideRequest rideRequest = new DtoRideRequest(requestid, requestedby, requestedbyname, pickuplocation, destination, datentime, passenger,
                        luggage, smoke, food, pet, ac, travellingtime, price, status, acceptedby, acceptedbyname, phone, requestuserstatus);

                requestlist.add(rideRequest);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return requestlist;
    }

    public static ArrayList<DtoRideRequest> selectCurrentRequestList(int userid){
        ArrayList<DtoRideRequest> requestlist = new ArrayList<DtoRideRequest>();

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT r.*, u.nickname, a FROM RIDEREQUEST WHERE requestedby = " + userid);
            //ResultSet rs = stmt.executeQuery("select r.*, u1.nickname as requestedbyname, u2.nickname as acceptedbyname, a.userid as acceptedby, u2.phone from RIDEREQUEST r JOIN USER u1 on r.requestedby = u1.userid JOIN ACCEPTEDRIDEREQUEST a on r.requestid = a.requestid JOIN USER u2 on a.userid = u2.userid WHERE requestedby = " + userid);
            ResultSet rs = stmt.executeQuery("select requestid, requestedby, datentime, price FROM RIDEREQUEST WHERE (status = 0 or status = 1) and requestedby = " + userid);
            while (rs.next()){
                int requestid = -1;
                int requestedby = -1;
                String datentimeStr = null;
                int price = 0;

                requestid = rs.getInt("requestid");
                requestedby = rs.getInt("requestedby");
                datentimeStr = rs.getString("datentime");
                price = rs.getInt("price");

                Date datentime = null;
                try {
                    datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                DtoRideRequest rideRequest = new DtoRideRequest(requestid, requestedby, datentime, price);

                requestlist.add(rideRequest);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return requestlist;
    }

    public static int cancelRideRequest(int requestid){
//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE RIDEREQUEST SET status = 2 WHERE requestid = " + requestid);

            //rs.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updateRideRequest(int requestid, RideRequest request){

        //int requestedby = request.getRequestedby();
        int passenger = request.getPassengers();
        int luggage = request.getLuggage();
        boolean smoking = request.isSmoking();
        boolean foodndrink = request.isFoodndrink();
        boolean pets = request.isPets();
        boolean ac = request.isAc();
        int travellingtime = request.getTravelingtime();
        int price = request.getPrice();
        //int status = request.getStatus();
        String pickuplocation = request.getPickuplocation();
        String destination = request.getDestination();
        Date datentime = request.getDatentime();
        Timestamp timestamp = new java.sql.Timestamp(datentime.getTime());

//        if(conn == null) {
//            connectDB();
//        }
        try {
            //took out status (remy says requestedby, status shouldn't be updated)
            String query = "UPDATE RIDEREQUEST SET pickuplocation = ?, destination = ?, datentime = ?, passenger = ?, luggage = ?, " +
                    "smoking = ?, foodndrink = ?, pets = ?, AC = ?, travellingtime = ?, price = ? WHERE requestid = ?";
            PreparedStatement stmt =  BoilerideServer.conn.prepareStatement(query);

            stmt.setString(1, pickuplocation);
            stmt.setString(2, destination);
            stmt.setTimestamp(3, timestamp);
            stmt.setInt(4,passenger);
            stmt.setInt(5,luggage);
            stmt.setBoolean(6,smoking);
            stmt.setBoolean(7,foodndrink);
            stmt.setBoolean(8,pets);
            stmt.setBoolean(9,ac);
            stmt.setInt(10,travellingtime);
            stmt.setInt(11,price);
            stmt.setInt(12, requestid);

            stmt.executeUpdate();

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int addRideOffer(RideOffer offer){
        int offerid = -1;

        int offeredby = offer.getOfferedby();
        int seats = offer.getSeats();
        int luggage = offer.getLuggage();
        boolean smoking = offer.isSmoking();
        boolean foodndrink = offer.isFoodndrink();
        boolean pets = offer.isPets();
        boolean ac = offer.isAc();
        int travellingtime = offer.getTravelingtime();
        int price = offer.getPrice();
        int status = offer.getStatus();
        int seatsleft = offer.getSeatleft();
        int luggagesleft = offer.getLuggageleft();
        String pickuplocation = offer.getPickuplocation();
        String destination = offer.getDestination();
        Date datentime = offer.getDatentime();
        Timestamp timestamp = new java.sql.Timestamp(datentime.getTime());

//        if(conn == null) {
//            connectDB();
//        }
        try {
            String query = "INSERT INTO RIDEOFFER(offeredby, pickuplocation, destination, " +
                    "datentime, seats, luggage, smoking, foodndrink, pets, AC, travellingtime, price, seatsleft, luggagesleft, status) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt =  BoilerideServer.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,offeredby);
            stmt.setString(2,pickuplocation);
            stmt.setString(3,destination);
            stmt.setTimestamp(4,timestamp);
            stmt.setInt(5,seats);
            stmt.setInt(6,luggage);
            stmt.setBoolean(7,smoking);
            stmt.setBoolean(8,foodndrink);
            stmt.setBoolean(9,pets);
            stmt.setBoolean(10,ac);
            stmt.setInt(11,travellingtime);
            stmt.setInt(12,price);
            stmt.setInt(13,seatsleft);
            stmt.setInt(14,luggagesleft);
            stmt.setInt(15,status);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                offerid = rs.getInt(1);
            }

            rs.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return offerid;
    }

    public static ArrayList<DtoRideOffer> selectOfferList(int userid){
        ArrayList<DtoRideOffer> offerlist = new ArrayList<DtoRideOffer>();

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT * FROM RIDEOFFER WHERE offeredby = " + userid);
            ResultSet rs = stmt.executeQuery("SELECT r.*, u1.nickname as offeredbyname, u2.nickname as joinedbyname, j.userid as joinedby, u2.phone, j.offeruserstatus " +
                    "FROM RIDEOFFER r, USER u1, USER u2, JOINEDRIDEOFFER j WHERE r.offeredby = u1.userid and r.offerid = j.offerid and j.userid = u2.userid " +
                    "and r.offeredby = "+ userid + " ORDER BY offerid");

            int offerid = -1;
            int offeredby = -1;
            String offeredbyname = "";
            int luggage = 0;
            int travellingtime = 0;
            int price = 0;
            int status = 0;
            int seatsleft = 0;
            int luggagesleft = 0;
            int seats = 0;
            String pickuplocation = "";
            String destination = "";
            String datentimeStr = null;
            boolean smoke = false;
            boolean food =false;
            boolean pet = false;
            boolean ac = false;
            ArrayList<Integer> joinedby = new ArrayList<Integer>();
            ArrayList<String> joinedbyname = new ArrayList<String>();
            ArrayList<String> phone = new ArrayList<String>();
            ArrayList<Integer> offeruserstatus = new ArrayList<Integer>();

            while (rs.next()) {
                if (offerid != rs.getInt("offerid")){
                    if (offerid != -1) {
                        Date datentime = null;
                        try {
                            datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        DtoRideOffer rideOffer = new DtoRideOffer(offerid, offeredby, offeredbyname, pickuplocation, destination, datentime, seats,
                                luggage, smoke, food, pet, ac, travellingtime, price, seatsleft, luggagesleft, status, joinedby, joinedbyname, phone, offeruserstatus);
                        offerlist.add(rideOffer);
                    }
                    offerid = rs.getInt("offerid");
                    offeredby = rs.getInt("offeredby");
                    offeredbyname = rs.getString("offeredbyname");
                    pickuplocation = rs.getString("pickuplocation");
                    destination = rs.getString("destination");
                    datentimeStr = rs.getString("datentime");
                    seats = rs.getInt("seats");
                    luggage = rs.getInt("luggage");
                    if (rs.getInt("smoking") == 1) smoke = true;
                    else smoke = false;
                    if (rs.getInt("foodndrink") == 1) food = true;
                    else food = false;
                    if (rs.getInt("pets") == 1) pet = true;
                    else pet = false;
                    if (rs.getInt("AC") == 1) ac = true;
                    else ac = false;
                    travellingtime = rs.getInt("travellingtime");
                    price = rs.getInt("price");
                    status = rs.getInt("status");
                    seatsleft = rs.getInt("seatsleft");
                    luggagesleft = rs.getInt("luggagesleft");
                    joinedby = new ArrayList<Integer>();
                    joinedbyname = new ArrayList<String>();
                    phone = new ArrayList<String>();
                    offeruserstatus = new ArrayList<Integer>();
                    joinedby.add(rs.getInt("joinedby"));
                    joinedbyname.add(rs.getString("joinedbyname"));
                    phone.add(rs.getString("phone"));
                    offeruserstatus.add(rs.getInt("offeruserstatus"));
                }
                else{
                    joinedby.add(rs.getInt("joinedby"));
                    joinedbyname.add(rs.getString("joinedbyname"));
                    phone.add(rs.getString("phone"));
                    offeruserstatus.add(rs.getInt("offeruserstatus"));
                }
            }
            Date datentime = null;
            try {
                datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DtoRideOffer rideOffer = new DtoRideOffer(offerid, offeredby, offeredbyname, pickuplocation, destination, datentime, seats,
                    luggage, smoke, food, pet, ac, travellingtime, price, seatsleft, luggagesleft, status, joinedby, joinedbyname, phone, offeruserstatus);
            offerlist.add(rideOffer);

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return offerlist;
    }

    public static ArrayList<DtoRideOffer> selectCurrentOfferList(int userid){
        ArrayList<DtoRideOffer> offerlist = new ArrayList<DtoRideOffer>();

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT * FROM RIDEOFFER WHERE offeredby = " + userid);
            ResultSet rs = stmt.executeQuery("SELECT offerid, offeredby, price, datentime " +
                    "FROM RIDEOFFER WHERE (status = 0 or status = 1) and offeredby = "+ userid);

            while (rs.next()) {
                int offerid = -1;
                int offeredby = -1;
                String datentimeStr = null;
                int price = 0;

                offerid = rs.getInt("offerid");
                offeredby = rs.getInt("offeredby");
                datentimeStr = rs.getString("datentime");
                price = rs.getInt("price");

                Date datentime = null;
                try {
                    datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                DtoRideOffer rideOffer = new DtoRideOffer(offerid, offeredby, datentime, price);

                offerlist.add(rideOffer);
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return offerlist;
    }

    public static RideOffer selectRideOffer(int offerid){

        RideOffer rideOffer = null;

//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RIDEOFFER WHERE offerid = " + offerid);

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
            String datentimeStr = null;
            boolean smoke = false, food =false, pet = false, ac = false;

            if (rs.next()) {
                offeredby = rs.getInt("offeredby");
                pickuplocation = rs.getString("pickuplocation");
                destination = rs.getString("destination");
                datentimeStr = rs.getString("datentime");
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


                if (smoking == 1) {
                    smoke = true;
                }
                if (foodndrink == 1) {
                    food = true;
                }
                if (pets == 1) {
                    pet = true;
                }
                if (AC == 1) {
                    ac = true;
                }

                Date datentime = null;
                try {
                    datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                rideOffer = new RideOffer(offeredby, pickuplocation, destination, datentime, seats,
                        luggage, smoke, food, pet, ac, travellingtime, price, seatsleft, luggagesleft, status);
            }
           //System.out.println(offeredby + ", " +pickuplocation+ ", " + destination);
            rs.close();
            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return rideOffer;
    }

    public static int cancelRideOffer(int offerid){
//        if(conn == null) {
//            connectDB();
//        }
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE RIDEOFFER SET status = 2 WHERE offerid = " + offerid);

            stmt.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updateRideOffer(int offerid, RideOffer offer){

        int seats = offer.getSeats();
        int luggage = offer.getLuggage();
        boolean smoking = offer.isSmoking();
        boolean foodndrink = offer.isFoodndrink();
        boolean pets = offer.isPets();
        boolean ac = offer.isAc();
        int travellingtime = offer.getTravelingtime();
        int price = offer.getPrice();
        //int status = offer.getStatus();
        //int seatsleft = offer.getSeatleft();
        //int luggagesleft = offer.getLuggageleft();
        String pickuplocation = offer.getPickuplocation();
        String destination = offer.getDestination();
        Date datentime = offer.getDatentime();
        Timestamp timestamp = new java.sql.Timestamp(datentime.getTime());

//        if(conn == null) {
//            connectDB();
//        }
        try {
            //took out status, offeredby (remy says shouldn't update them)
            String query = "UPDATE RIDEOFFER SET pickuplocation = ?, destination = ?, datentime = ?, " +
                    "seatsleft = seatsleft + (? - seats), luggagesleft = luggagesleft + (? - luggage), " +
                    "seats = ?, luggage = ?, " + "smoking = ?, foodndrink = ?, pets = ?, AC = ?, travellingtime = ?, " +
                    "price = ? WHERE offerid = ?";
            PreparedStatement stmt =  BoilerideServer.conn.prepareStatement(query);


//            Statement stmt = BoilerideServer.conn.createStatement();
//            stmt.executeUpdate("UPDATE RIDEOFFER SET offeredby = "+offeredby +", pickuplocation = '" +pickuplocation+
//                    "',  destination = '" +destination+"', datentime = '" +timestamp+ "', seatsleft = seatsleft + (" + seats + "-seats)" +
//                    ", luggagesleft = luggagesleft + (" + luggage + "-luggage)" + ", seats = " +seats+ ",  luggage = " +luggage+ ", smoking = " +smoking+
//                    ", foodndrink = "+foodndrink+", pets = " +pets+", AC = " +AC+", travellingtime = " +travellingtime+
//                    ", price = " +price + " WHERE offerid = " +offerid);

            stmt.setString(1, pickuplocation);
            stmt.setString(2, destination);
            stmt.setTimestamp(3, timestamp);
            stmt.setInt(4, seats);
            stmt.setInt(5, luggage);
            stmt.setInt(6, seats);
            stmt.setInt(7,luggage);
            stmt.setBoolean(8,smoking);
            stmt.setBoolean(9,foodndrink);
            stmt.setBoolean(10,pets);
            stmt.setBoolean(11,ac);
            stmt.setInt(12,travellingtime);
            stmt.setInt(13,price);
            stmt.setInt(14, offerid);

            stmt.executeUpdate();

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 99;
        }

        return 0;
    }

    public static ArrayList<User>  selectUserAcceptedRequest(int requestid){
        ArrayList<User> users = new ArrayList<User>();

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT u.* FROM USER u, ACCEPTEDRIDEREQUEST a WHERE u.userid = a.userid and a.requestid = " + requestid);

            int id = 0 , points = 0, reserve = 0, status = 0;
            String nickname = "", password = "", phone = "", email = "";

            while (rs.next()) {
                id = rs.getInt("userid");
                nickname = rs.getString("nickname");
                phone = rs.getString("phone");
                points = rs.getInt("points");
                reserve = rs.getInt("reserve");
                status = rs.getInt("status");
                email = rs.getString("email");
                password = rs.getString("password");
                //System.out.println("id = "+ id + ", nickname = " + nickname + ", password =" + password + ", phone = " + phone + ", points = " + points + ", status = " + status + ", email = " + email);User(String email, String password, String nickname, String phone, int points, int status)
                User resultUser = new User( email,  password,  nickname,  phone,  points,  reserve,  status,  id);
                users.add(resultUser);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }

    public static ArrayList<User> selectUserJoinedOffer(int offerid){
        ArrayList<User> users = new ArrayList<User>();

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT u.* FROM USER u, JOINEDRIDEOFFER j WHERE u.userid = j.userid and j.offerid = " + offerid);

            int id = 0 , points = 0, reserve = 0, status = 0;
            String nickname = "", password = "", phone = "", email = "";

            while (rs.next()) {
                id = rs.getInt("userid");
                nickname = rs.getString("nickname");
                phone = rs.getString("phone");
                points = rs.getInt("points");
                reserve = rs.getInt("reserve");
                status = rs.getInt("status");
                email = rs.getString("email");
                password = rs.getString("password");
                //System.out.println("id = "+ id + ", nickname = " + nickname + ", password =" + password + ", phone = " + phone + ", points = " + points + ", status = " + status + ", email = " + email);User(String email, String password, String nickname, String phone, int points, int status)
                User resultUser = new User( email,  password,  nickname,  phone,  points,  reserve,  status,  id);
                users.add(resultUser);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return users;
        }
        return users;
    }

    public static ArrayList<RideOffer> rideOfferFrom(String city, Date date, int qpassenger, int qluggage, boolean qsmoking, boolean qfoodndrink, boolean qpets, boolean qac) {
        ArrayList<RideOffer> res = new ArrayList<>();

        int offerid = -1;
        int offeredby = -1;
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
        String datentimeStr = null;
        boolean smoke = false;
        boolean food =false;
        boolean pet = false;
        boolean ac = false;

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            String query = String.format("SELECT * FROM RIDEOFFER " +
                            "WHERE status <> 2 " +
                            "AND pickuplocation like '%%%s%%' " +
                            "AND datentime >= '%s' " +
                            "AND seatsleft >= %d " +
                            "AND luggagesleft >= %d " +
                            "AND smoking = %d " +
                            "AND foodndrink = %d " +
                            "AND pets = %d " +
                            "AND ac = %d", city, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date),
                    qpassenger, qluggage, qsmoking ? 1 : 0, qfoodndrink ? 1 : 0, qpets ? 1 : 0, qac ? 1 : 0);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                offerid = rs.getInt("offerid");
                offeredby = rs.getInt("offeredby");
                pickuplocation = rs.getString("pickuplocation");
                destination = rs.getString("destination");
                datentimeStr = rs.getString("datentime");
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

                if (smoking == 1) {
                    smoke = true;
                }
                if (foodndrink == 1) {
                    food = true;
                }
                if (pets == 1) {
                    pet = true;
                }
                if (AC == 1) {
                    ac = true;
                }

                Date datentime = null;
                try {
                    datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                RideOffer rideOffer = new RideOffer(offerid, offeredby, pickuplocation, destination, datentime, seats,
                        luggage, smoke, food, pet, ac, travellingtime, price, seatsleft, luggagesleft, status);

                res.add(rideOffer);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return res;
    }

    public static ArrayList<RideRequest> rideRequestFromTo(String city1, String city2, Date date, int qpassenger, int qluggage, boolean qsmoking, boolean qfoodndrink, boolean qpets, boolean qac) {
        ArrayList<RideRequest> res = new ArrayList<>();

        int requestid = -1;
        int requestedby = -1;
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
        String datentimeStr = null;
        boolean smoke = false;
        boolean food =false;
        boolean pet = false;
        boolean ac = false;

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            String query = String.format("SELECT * FROM RIDEREQUEST " +
                    "WHERE status <> 2 " +
                    "AND pickuplocation like '%%%s%%' " +
                    "AND destination like '%%%s%%' " +
                    "AND datentime >= '%s' " +
                    "AND passenger >= %d " +
                    "AND luggage >= %d " +
                    "AND smoking = %d " +
                    "AND foodndrink = %d " +
                    "AND pets = %d " +
                    "AND ac = %d", city1, city2, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date),
                    qpassenger, qluggage, qsmoking ? 1 : 0, qfoodndrink ? 1 : 0, qpets ? 1 : 0, qac ? 1 : 0);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                requestid = rs.getInt("requestid");
                requestedby = rs.getInt("requestedby");
                pickuplocation = rs.getString("pickuplocation");
                destination = rs.getString("destination");
                datentimeStr = rs.getString("datentime");
                passenger = rs.getInt("passenger");
                luggage = rs.getInt("luggage");
                smoking = rs.getInt("smoking");
                foodndrink = rs.getInt("foodndrink");
                pets = rs.getInt("pets");
                AC = rs.getInt("AC");
                travellingtime = rs.getInt("travellingtime");
                price = rs.getInt("price");
                status = rs.getInt("status");

                if (smoking == 1) {
                    smoke = true;
                }
                if (foodndrink == 1) {
                    food = true;
                }
                if (pets == 1) {
                    pet = true;
                }
                if (AC == 1) {
                    ac = true;
                }

                Date datentime = null;
                try {
                    datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                RideRequest rideRequest = new RideRequest(requestid, requestedby, pickuplocation, destination, datentime, passenger,
                        luggage, smoke, food, pet, ac, travellingtime, price, status);
                res.add(rideRequest);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return res;
    }

    public static int addRequestPickup(int requestid, int code){
        return 0;
    }

    public static int addOfferPickup(int offerid, int code){
        return 0;
    }

    public static int addAcceptedReqPickup(int userid, int requestid, int code){
        return 0;
    }

    public static int addJoinedOfferPickup(int userid, int offerid, int code){
        return 0;
    }

    public static AcceptedRequest selectAcceptedRequest(int requestid){
        return null;
    }

    public static JoinedOffer selectJoinedOffer(int userid, int offerid){
        return null;
    }

    public static int updateRequestUserStatus(int requestid, int requestuserstatus){
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE ACCEPTEDRIDEREQUEST SET requestuserstatus = " + requestuserstatus + " WHERE requestid = " + requestid);

            stmt.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updateAcceptedUserStatus(int requestid, int accepteduserstatus){
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE ACCEPTEDRIDEREQUEST SET accepteduserstatus = " + accepteduserstatus + " WHERE requestid = " + requestid);

            stmt.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updateOfferUserStatus(int userid, int offerid, int pickupstatus){
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE JOINEDRIDEOFFER SET offeruserstatus = " + pickupstatus + " WHERE offerid = " + offerid + " and userid = " + userid);

            stmt.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updateJoinedUserStatus(int userid, int offerid, int pickupstatus){
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE JOINEDRIDEOFFER SET joineduserstatus = " + pickupstatus + " WHERE offerid = " + offerid + " and userid = " + userid);

            stmt.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static int updatePointReserve(int userid, int pointAmount, int reserveAmount){
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("UPDATE USER SET points = points+" + pointAmount + ", reserve = reserve+" + reserveAmount + " WHERE userid = " + userid);

            stmt.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return 99;
        }
        return 0;
    }

    public static ArrayList<AcceptedRequest> selectCurrentAcceptedRequestList(int userid){
        return null;
    }

    public static ArrayList<JoinedOffer> selectCurrentJoinedOfferList(int userid){
        return null;
    }

    public static int updateRequestStatus(int requestid, int status){
        return 0;
    }

    public static int updateOfferStatus(int offerid, int userid, int status){
        return 0;
    }

    public static int updateAcceptedStatus(int requestid, int status){
        return 0;
    }

    public static int updateJoinedStatus(int offerid, int userid, int status){
        return 0;
    }

    public static ArrayList<DtoTransaction> selectTransactionList(int userid){
        ArrayList<DtoTransaction> transactionlist = new ArrayList<DtoTransaction>();

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT t.*, u1.nickname as tousername, u2.nickname as fromusername FROM TRANSACTION t, USER u1, USER u2 " +
                    "WHERE t.touserid = u1.userid and t.foruserid = u2.userid and (foruserid = " + userid + " or touserid = " + userid + ")");

            int transactionid;
            int touserid;
            String tousername;
            int fromuserid;
            String fromusername;
            String datentimeStr;
            Date datentime;
            int amount;
            String description;

            while (rs.next()) {
                transactionid = rs.getInt("transactionid");
                touserid = rs.getInt("touserid");
                tousername = rs.getString("tousername");
                fromuserid = rs.getInt("foruserid");
                fromusername = rs.getString("fromusername");
                datentimeStr = rs.getString("datentime");
                amount = rs.getInt("amount");
                description = rs.getString("description");

                datentime = null;
                try {
                    datentime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datentimeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                DtoTransaction transaction = new DtoTransaction(transactionid, touserid, tousername, fromuserid, fromusername, datentime, amount, description);
                transactionlist.add(transaction);
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return transactionlist;
        }

        return transactionlist;
    }

    public static int addTransaction(int to, int from, Date date, int amount, String description){

        Timestamp timestamp = new java.sql.Timestamp(date.getTime());

        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            stmt.executeUpdate("INSERT INTO TRANSACTION(touserid, foruseid, datentime, amount, description) " +
                    "VALUES (" + to + "," + from + ",'" + timestamp + "'," + amount + ",'" + description + "')");

            //System.out.println(userid);
            stmt.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return 99;
        }

        return 0;
    }

    public static void main (String args[]) throws ParseException {
        //User user = new User( "henry@mail.com", "tnc", "armiel", "8888", 1000, 0 );
        //updateSQLUser(1, user);
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-05-18 18:45:11");
        //Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        //System.out.println(timestamp);
        //addUser(user);
        //selectUser(1);
        //System.out.println(loginWithEmailPassword("ryan@mail.com", "lala"));
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
