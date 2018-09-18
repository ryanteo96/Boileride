
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import DTO.*;

/**
 * Created by arthur on 2018/9/13.
 */
public class User
{

    private String email;
    private String password;
    private String nickname;
    private String phone;


    String getNickname(){return nickname;}
    String getPassword(){return password;}
    String getEmail(){return email;}
    String getPhone(){return phone;}

    private boolean verifyNickname(String nickname)
    {
        if(nickname.length() < 50)
        {
            return true;
        }
        return false;
    }
    private boolean verifyEmail(String email)
    {
        try
        {
            InternetAddress e = new InternetAddress(email);
            e.validate();
            return true;
        }
        catch (AddressException error)
        {
            System.out.print(error.getLocalizedMessage());
            return false;
        }
    }
    private boolean verifyEmailExist(String email)
    {
        /*
            if(emailExist(email))
            {
                return true;
            }
        */


        return false;
    }
    private boolean verifyPhone(String phoneNumber)
    {
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
            if(user.verifyEmail(req.getEmail()))
            {
                if(!user.verifyEmailExist(req.getEmail()))
                {
                    if(user.verifyPhone(req.getPhone()))
                    {
                        System.out.println("Passed signUp Verification");
                        /*
                            int dbResponse = -1;
                            dbResponse = addUser(user);
                            if(dbResponse == -1)
                            {
                                System.out.println("Failed add user in db");
                                response.setUserid(-1);
                            }
                            else
                            {
                                response.setUserid(dbResponse);
                            }
                        */

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
        *       System.out.println("Login success");
        *       response.setResult(0);
        *       response.setUserid(userid);
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
                System.out.println("Success getting user in db from viewAccount function.");
                response.setResult(0);
                response.setNickname(user.getNickName());
                response.setPhone(user.getPhone());
                response.setEmail(user.getEmail());
            }
            else
            {
                response.setResult(1);
            }


        */

        return response;
    }
    public UserUpdateResponse updateUser(UserUpdateRequest req)
    {
        UserUpdateResponse response = new UserUpdateResponse(-1);
        /*
            if(user = selectUser(req.getUserid()) && user != NULL)
            {
                if(verifyNickname(req.getNickname()))
                {
                    if(verifyEmail(req.getEmail()))
                    {
                        if(verifyPhone(req.getPhone()))
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
        UserForgotPasswordResponse response = new UserForgotPasswordResponse(-1,-1);
        String email = req.getEmail();
        int id;
        String password;
        User user;

        /*


            if(id = lookUpUserid(email) && id != -1)
            {
                user = selectUser(id);
                if(user != NULL)
                {
                    password = user.getPassword();
                    String buffer = password+email;
                    String hashCode = buffer.hashCode();
                    response.setCode(hashCode);
                    response.setResult(0);
                    response.setUserid(id);
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
                if(req.getCode() == hashCode)
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

}
