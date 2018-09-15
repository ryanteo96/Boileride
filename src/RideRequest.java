import java.util.Date;
import java.time.format.*;
import java.time.*;
import java.sql.*;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * RideRequest class handles data verification and actions related to posting, canceling and updating ride request
 *
 * @version September 14, 2018
 */

public class RideRequest {
    private String pickuplocation;
    private String destination;
    private Date datentime;
    private int passengers;
    private int luggage;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;
    private int travelingtime;
    private int price;

    public int verifyUserid(User user){
        if (user == null){
            return 1;
        }
//        else if (user.getStatus() == 0){
//            return 2;
//        }
        return 0;
    }

    public int verifyDestination(String pickuplocation, String destination){
        if (pickuplocation.equalsIgnoreCase(destination)){
            return 3;
        }
        return 0;
    }

    public int verifyDatentime(Date datentime){
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
//            LocalDateTime date = LocalDateTime.parse(datentime, formatter);
//            Date dateFromLocalDT = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
            Date today = new Date();
            if (datentime.compareTo(today) <= 0 ){
                return 5;
            }
//        } catch (DateTimeParseException ex){
//            return 7;
//        }
        return 0;
    }

    public int isEnoughPrice(User user, int price){
//        if (user.getPoints < price) {
//            return 6;
//        }
        return 0;
    }

    public int verifyData(Connection conn, String userid, String pickuplocation, String destination, Date datentime, int price){
        int result = 0;
        User user = DatabaseCommunicator.selectUser(conn, userid);
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(pickuplocation, destination);
        int dateResult = verifyDatentime(datentime);
        int priceResult = isEnoughPrice(user, price);
        if (userResult > 0) return userResult;
        else if (desResult > 0) return desResult;
        else if (dateResult > 0) return dateResult;
        else if (priceResult > 0) return priceResult;
        else return result;
    }


}
