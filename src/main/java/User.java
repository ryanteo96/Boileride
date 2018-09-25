
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import DTO.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    private int status;
    public static DatabaseCommunicator SQL = new DatabaseCommunicator();

    public User(){}

    public User(String email, String password, String nickname, String phone, int points, int status) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.points = points;
        this.status = status;
    }

    String getNickname(){return nickname;}
    String getPassword(){return password;}
    String getEmail(){return email;}
    String getPhone(){return phone;}
    int getPoints() { return points; }
    int getStatus() { return status; }

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

            /*
                String buffer = req.getEmail();
                String hashCode = hash(buffer);
                int userid = lookUpUserid(req.getEmail());
            if(user = selectUser(userid) && user != null)
            {

                if(req.getCode().compare(hashCode) == 0)
                {

                    int dbResponse = -1;
                    user.status = 1;
                    user.point = 200;
                    dbResponse = updataUserStatus(user.getUserid(),user);
                    if(dbResponse == -1)
                    {
                        System.out.println("Failed add user in db");
                        response.setUserid(-1);
                    }
                    else
                    {
                        response.setUserid(dbResponse);
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


            */
        return response;
    }
    private boolean verifyEmailExist(String email)
    {
        /*
            if(SQL.emailExist(email))
            {
                return true;
            }

        */

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
                            sender.sendEmail(req.getEmail(),"Your special coe to register your account", hashCode );
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

        /*
        * if(emailExist(req.getEmail()) == 0)
        * {
        *   if(loginWithEmailPassword(req.getEmail(), req.getPassword()) != 1)
        *   {
        *       int userid = loginWithEmailPassword(req.getEmail(), req.getPassword()) ;
        *

        *       if(user = selectUser(userid) && user != NULL)
        *       {
                    System.out.println("Login success");
        *           response.setResult(0);
        *           response.setUserid(userid);

        *
        *           user.status = 1;
        *           updateUserStatus(userid,user);
        *
        *       }
        *
        *   }
        *   else
        *   {
        *       response.setResult(2);
        *   }
        * }
        * else
        * {
        *   response.setResult(1);
        * }
        * */
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
    public UserUpdateResponse updateUser(UserUpdateRequest req) {
        UserUpdateResponse response = new UserUpdateResponse(-1);
        User user;
//        if(user = selectUser(req.getUserid()) && user != NULL)
//        {
    //        if (req.getEmail().compareTo("") == 0) {
    //            req.setEmail(user.getEmail());
    //        }
    //        if (req.getNickname().compareTo("") == 0) {
    //            req.setNickname(user.getNickname());
    //        }
    //        if (req.getPassword().compareTo("") == 0) {
    //            req.setPassword(user.getPassword());
    //        }
    //        if (req.getPhone().compareTo("") == 0) {
    //            req.setPhone(user.getPhone());
    //        }
//
//        }



        /*
           if(user != NULL)
           {
                if(verifyNickname(req.getNickname()))
                {
                    if(verifyEmail(req.getEmail()))
                    {
                        if(verifyPhone(req.getPhone()))
                        {
                            if(user.status == 1)
                            {
                                if(updateSQLUser(req.getUserid(), user) == 0)
                                {
                                    System.out.println("Success updating user in db");
                                    req.setResult(0);
                                }
                                else
                                {
                                    System.out.println("Failed updating user in db");
                                }
                            }
                            else
                            {
                                System.out.println("User not logged in");
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

        */
        return response;
    }
    public UserForgotPasswordResponse forgotPassword(UserForgotPasswordRequest req)
    {
        UserForgotPasswordResponse response = new UserForgotPasswordResponse(-1);
        String email = req.getEmail();
        int id;
        String password;
        User user;

        /*
            if(id = lookUpUserid(email) && id != -1)
            {

                if(user != NULL)
                {
                    password = user.getPassword();
                    String buffer = password+email;
                    String hashCode = buffer.hashCode();
                    response.setCode(hashCode);
                    response.setResult(0);
                    SendEmail.sendEmail(email, "Unique code for resetting password", "Please use this code to reset your password:" + hashCode);
                }
                else
                {
                    System.out.println("Failed getting userinfo in UserForgotPasswordResponse");
                }
            }
            else
            {
                response.setResult(1);
            }

         */



        return response;
    }
    public UserResetPasswordResponse resetPassword(UserResetPasswordRequest req)
    {
        UserResetPasswordResponse response = new UserResetPasswordResponse(-1);
        UserUpdateRequest updateReq = new UserUpdateRequest(-1, "", "", "", "");
        UserUpdateResponse updateResponse;
        User user;

        /*
            if(user = selectUser(req.getUserid()) && user != NULL)
            {
                String buffer = user.getPassword() + user.getEmail();
                String hashCode = buffer.hashCode();
                if(req.getCode().compare(hashCode) == 0)
                {

                   updateReq.setUserid(req.getUserid());
                   updateReq.setEmail(user.getEmail());
                   updateReq.setPassword(req.getNewpassword());
                   updateReq.setNickname(user.getNickname());
                   updateReq.setPhone(user.getPhone());
                   updateResponse = updateUser(updateReq);

                   if(updateResponse.getResult() == 0)
                   {
                        req.setResult(0);
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
        */


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
}
