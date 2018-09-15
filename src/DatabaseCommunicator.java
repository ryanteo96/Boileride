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

    public void sampleExecuteQuery(Connection conn){
        System.out.println("select from database");
        try {
            Statement stmt = conn.createStatement();
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
            Statement stmt = conn.createStatement();
            String sql;
            String userid = "testing";
            sql = "INSERT into Users values(" + userid + ")";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLIntegrityConstraintViolationException e){
            //Duplicate entry
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static User selectUser(Connection conn, String userid){
        User resultUser = new User();
        return resultUser;
    }


}
