
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import DTO.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by arthur on 2018/9/13.
 */
public class User
{

    private String email;
    private String password;
    private String nickname;
    private String phone;
    private int points;
    private int reserve;
    private int status;
    private int userid;

    public static DatabaseCommunicator SQL = new DatabaseCommunicator();

    public User(){}

    public User(String email, String password, String nickname, String phone, int points, int reserve, int status, int userid) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.points = points;
        this.reserve = reserve;
        this.status = status;
        this.userid = userid;
    }

    public User(String email, String password, String nickname, String phone, int points, int status, int userid) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.points = points;
        this.status = status;
        this.userid = userid;
    }

    String getNickname(){return nickname;}
    String getPassword(){return password;}
    String getEmail(){return email;}
    String getPhone(){return phone;}
    int getPoints() { return points; }
    int getStatus() { return status; }
    int getUserid() {return userid;}

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", points=" + points +
                ", status=" + status +
                '}';
    }

    private boolean verifyNickname(String nickname)
    {
        if(nickname.length() < 50)
        {
            return true;
        }
        return false;
    }
    private boolean verifyEmailFormat(String email)
    {

        try
        {
            InternetAddress e = new InternetAddress(email);
            e.validate();
            return true;
        }
        catch (AddressException error)
        {
            System.out.println(error.getLocalizedMessage());
        }

        return false;


    }
    private String hash(String str)
    {
        String hash = "";

        try
        {
            MessageDigest objSHA256 = MessageDigest.getInstance("SHA-256");
            byte[] bytSHA256 = objSHA256.digest(str.getBytes());
            BigInteger intNumSHA256 = new BigInteger(1, bytSHA256);
            String hcSHA256 = intNumSHA256.toString(16);
            while (hcSHA256.length() < 64) {
                hcSHA256 = "0" + hcSHA256;
            }
            hash = hcSHA256;
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Cant find SHA-256 hash");
            System.out.println(e.getLocalizedMessage());
        }
        return hash;
    }

    public UserVerifyEmailResponse verifyEmailCode(UserVerifyEmailRequest req)
    {
        UserVerifyEmailResponse response = new UserVerifyEmailResponse(-1,-1);
        User user;

        String buffer = req.getEmail();
        String hashCode = hash(buffer);
        user =  SQL.selectUserByEmail(req.getEmail());
        int userid = user.getUserid();
        if(user != null)
        {
            if(req.getCode().compareTo(hashCode) == 0)
            {
                int dbResponse = -1;
                user.status = 1;
                user.points = 200;
                dbResponse = SQL.updateUserStatus(user.getUserid(),1);
                if(dbResponse == -1)
                {
                    System.out.println("Failed add user in db");
                    response.setUserid(-1);
                }
                else
                {
                    response.setUserid(userid);
                    response.setResult(0);
                }
            }
            else
            {
                response.setResult(2);
            }
        }
        else
        {
            System.out.println("Failed to select user in verifyEmailCode");
        }



        return response;
    }
    private boolean verifyEmailExist(String email)
    {
        if(SQL.selectUserByEmail(email) != null)
        {
            return true;
        }
        return false;
    }
    private boolean verifyPhone(String phoneNumber)
    {
        if(phoneNumber.length() < 10)
        {
            return false;
        }
        for(int i = 0; i<phoneNumber.length();i++)
        {
            if(!Character.isDigit(phoneNumber.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }



    public UserSignUpResponse signUp(UserSignUpRequest req)
    {
        User user = new User();
        UserSignUpResponse response = new UserSignUpResponse(-1,-1);
        if(user.verifyNickname(req.getNickname()))
        {
            if(user.verifyEmailFormat(req.getEmail()))
            {
                if(!user.verifyEmailExist(req.getEmail()))
                {
                    if(user.verifyPhone(req.getPhone()))
                    {
                        System.out.println("Passed signUp Verification");
                        SendEmail sender = new SendEmail();
                            String hashCode = hash(req.getEmail());
                            sender.sendEmail(req.getEmail(),"Your special code to register your account", hashCode );
                            user.status = -1;
                            user.email = req.getEmail();
                            user.nickname = req.getNickname();
                            user.password = req.getPassword();
                            user.phone = req.getPhone();
                            user.points = 200;
                            SQL.addUser(user);


                        response.setResult(0);

                    }
                    else
                    {
                        response.setResult(4);
                    }
                }
                else
                {
                    response.setResult(3);
                }
            }
            else
            {
                response.setResult(2);
            }
        }
        else
        {
            response.setResult(1);
        }

        return response;
    }



    public UserLoginResponse login(UserLoginRequest req)
    {
        UserLoginResponse response = new UserLoginResponse(-1,-1);
        User user = SQL.selectUserByEmail(req.getEmail());
        int userid = -1;
        if(user!= null)
         {
             userid = SQL.loginWithEmailPassword(req.getEmail(), req.getPassword());
             if(userid != -1 && user.getStatus() > -1)
             {
               if(user.getUserid() == userid)
               {
                   System.out.println("Login success");
                   response.setResult(0);
                   response.setUserid(userid);
                   user.status = 1;
                   SQL.updateUserStatus(userid,1);

               }

             }
             else
             {
                 response.setResult(2);
             }
         }
         else
         {
           response.setResult(1);
         }

        return response;
    }
    public UserViewAccountResponse viewAccount(UserViewAccountRequest req)
    {

        UserViewAccountResponse response = new UserViewAccountResponse(-1, "", "", "");
        User user;
        /*

            if(user = selectUser(req.getUserid()) && user != NULL)
            {
                if(user.status == 1)
                {
                    System.out.println("Success getting user in db from viewAccount function.");
                    response.setResult(0);
                    response.setNickname(user.getNickName());
                    response.setPhone(user.getPhone());
                    response.setEmail(user.getEmail());
                }
                else
                {
                    System.out.println("User not logged in");
                    response.setResult(1);
                }
            }
            else
            {
                response.setResult(1);
            }


        */

        return response;
    }
    public UserUpdateResponse updateUser(UserUpdateRequest req, Boolean resetPassword)
    {

        UserUpdateResponse response = new UserUpdateResponse(-1);
        User user = SQL.selectUser(req.getUserid());
        boolean resetEmail = false;
        if(user != null)
        {
            if (req.getEmail().compareTo("") == 0) {
                req.setEmail(user.getEmail());
            }
            else
            {
                resetEmail = true;

            }

            if (req.getNickname().compareTo("") == 0) {
                req.setNickname(user.getNickname());
            }
            if (req.getPassword().compareTo("") == 0) {
                req.setPassword(user.getPassword());
            }
            if (req.getPhone().compareTo("") == 0) {
                req.setPhone(user.getPhone());
            }

        }

           if(user != null)
           {
                if(verifyNickname(req.getNickname()))
                {
                    if(verifyEmailFormat(req.getEmail()))
                    {
                        if(verifyPhone(req.getPhone()))
                        {
                            if(user.status == 1 || resetPassword || resetEmail)
                            {
                                if(user.status == 1 && !resetEmail)
                                {
                                    user = new User(req.getEmail(),req.getPassword(),req.getNickname(), req.getPhone(),user.getPoints(),1, req.getUserid());
                                }
                                else if(resetPassword)
                                {
                                    user = new User(req.getEmail(),req.getPassword(),req.getNickname(), req.getPhone(),user.getPoints(),0, req.getUserid());
                                }
                                else if(resetEmail)
                                {
                                    user = new User(req.getEmail(),req.getPassword(),req.getNickname(), req.getPhone(),user.getPoints(),-1, req.getUserid());
                                    String hashCode = hash(req.getEmail());
                                    SendEmail sender = new SendEmail();
                                    sender.sendEmail(req.getEmail(),"Your special code to activate your account", hashCode );
                                }


                                int result = SQL.updateSQLUser(req.getUserid(), user);
                                if( result == 0)
                                {
                                    System.out.println("Success updating user in db");
                                    response.setResult(0);
                                }
                                else if(result == 1)
                                {
                                    System.out.println("Duplicate email in server");
                                }
                                else
                                {
                                    System.out.println("Failed updating user in db");
                                }
                            }
                            else
                            {
                                System.out.println("User not logged in");
                                response.setResult(2);
                            }

                        }
                        else
                        {
                            response.setResult(5);
                        }
                    }
                    else
                    {
                        response.setResult(4);
                    }
                }
                else
                {
                    response.setResult(3);
                }

            }
            else
            {
                response.setResult(1);
            }

        return response;
    }
    public UserForgotPasswordResponse forgotPassword(UserForgotPasswordRequest req)
    {
        UserForgotPasswordResponse response = new UserForgotPasswordResponse(-1);
        String email = req.getEmail();

        String password;
        User user = SQL.selectUserByEmail(email);


        if(user != null)
        {
            password = user.getPassword();
            String buffer = password+email;
            String hashCode = hash(buffer);
            response.setCode(hashCode);
            response.setResult(0);
            SendEmail.sendEmail(email, "Unique code for resetting password", "Please use this code to reset your password:" + hashCode);

        }
        else
        {
            response.setResult(1);
        }

        return response;
    }
    public UserResetPasswordResponse resetPassword(UserResetPasswordRequest req)
    {
        UserResetPasswordResponse response = new UserResetPasswordResponse(-1);
        UserUpdateRequest updateReq = new UserUpdateRequest(-1, "", "", "", "");
        UserUpdateResponse updateResponse;
        User user = SQL.selectUserByEmail(req.getEmail());


            if(user != null)
            {
                String buffer = user.getPassword() + user.getEmail();
                String hashCode = hash(buffer);

                if(req.getCode().compareTo(hashCode) == 0)
                {

                   updateReq.setUserid(user.getUserid());
//                   updateReq.setEmail(req.getEmail());
                   updateReq.setPassword(req.getNewpassword());
//                   updateReq.setNickname(user.getNickname());
//                   updateReq.setPhone(user.getPhone());

                   updateResponse = updateUser(updateReq, true);

                   if(updateResponse.getResult() == 0)
                   {
                        response.setResult(0);
                        System.out.println("Success reset password ");
                   }
                   else
                   {
                        switch(updateResponse.getResult())
                        {
                            case 1:
                                System.out.println("Invalid Userid");
                                break;
                            case 2:
                                System.out.println("User not log in");
                                break;
                            case 3:
                                System.out.println("Invalid nickname");
                                break;
                            case 4:
                                System.out.println("Invalid email");
                                break;
                            case 5:
                                System.out.println("Invalid phone");
                                break;
                             default:
                                break;

                        }
                   }

                }
                else
                {
                    response.setResult(2);
                }
            }
            else
            {
                response.setResult(1);
            }


        return response;
    }

    public int verifyUserid(User user){
        if (user == null){
            return 1;
        }
        else if (user.getStatus() <= 0){
            return 2;
        }
        return 0;
    }

    public UserLogoutResponse logout(UserLogoutRequest request){
        int result = 0;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        if (userResult > 0) result = userResult;
        else {
            result = DatabaseCommunicator.updateUserStatus(request.getUserid(), 0);
        }
        UserLogoutResponse res = new UserLogoutResponse(result);
        return res;
    }

    public UserViewPointsResponse viewPointsFromDB(UserViewPointsRequest request){
        PointCalculator.chargeFailConfirmationFee(request.getUserid());
        int result = 0;
        int points = -1;
        int reserve = -1;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        if (user == null){
            result = 1;
        }
        UserViewPointsResponse res = new UserViewPointsResponse(result, points, reserve);
        return res;
    }

    public UserViewTransactionResponse viewTransactionFromDB(UserViewTransactionRequest request){
        PointCalculator.chargeFailConfirmationFee(request.getUserid());
        int result = 0;
        ArrayList<DtoTransaction> transactionlist = new ArrayList<DtoTransaction>();
        transactionlist = DatabaseCommunicator.selectTransactionList(request.getUserid());
        UserViewTransactionResponse res = new UserViewTransactionResponse(result, transactionlist);
        return res;
    }
}
