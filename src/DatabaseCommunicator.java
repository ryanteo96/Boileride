import java.sql.*;

/**
 * Created by remychow on 9/13/18.
 */
public class DatabaseCommunicator {

    private Connection conn;

    public DatabaseCommunicator(Connection conn) {
        this.conn = conn;

    }

    public void testExecuteQuery(Connection conn){
        System.out.println("select from database");
        try {
            Statement stmt = this.conn.createStatement();
            String sql;
            sql = "SELECT * FROM Users";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String userid = rs.getString("userid");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("update, insert, delete in database");
        try {
            Statement stmt = this.conn.createStatement();
            String sql;
            String userid = "testing";
            sql = "INSERT into Users values(" + userid + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
