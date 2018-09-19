import java.sql.*;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DatabaseCommunicator class send queries to database and return results back to server
 *
 * @version September 13, 2018
 */

public class DatabaseCommunicator {

    public void sampleExecuteQuery(){
        System.out.println("select from database");
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            String sql;
            sql = "SELECT * FROM Users";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next() ) {
                System.out.println("no data");
            } else {
                do {
                    String userid = rs.getString("userid");
                }
                while (rs.next());
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("update, insert, delete in database");
        try {
            Statement stmt = BoilerideServer.conn.createStatement();
            String sql;
            String userid = "testing";
            sql = "INSERT into Users values(" + userid + ")";
            int count = stmt.executeUpdate(sql);
            if (count > 0){
                //done
            }
            else {
                //not successful
            }
            stmt.close();
        } catch (SQLIntegrityConstraintViolationException e){
            //Duplicate entry
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int addUser(User user){
        return 0;
    }

    public static User selectUser(int userid){
        User resultUser = new User();
        resultUser = null;
        return resultUser;
    }

    public static int addRideRequest(RideRequest req){
        return 0;
    }

    public static RideRequest selectRideRequest(int requestid){
        return null;
    }

    public static int cancelRideRequest(int requestid){
        return 0;
    }

    public static int addRideOffer(RideOffer offer){
        return 0;
    }

    public static RideOffer selectRideOffer(int offerid){
        return null;
    }

    public static int cancelRideOffer(int offerid){
        return 0;
    }
}
