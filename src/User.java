import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by arthur on 2018/9/13.
 */
public class User
{
    String nickname = "";
    String email = "";
    String password = "";
    String phoneNumber = "";


    Boolean verifyNickname(String nickname)
    {
        if(nickname.length() < 50)
        {
            return true;
        }
        return false;
    }
    Boolean verifyEmail(String email)
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
    Boolean verifyPassword(String password)
    {
        if(password.length()>6)
        {
            
        }
        return false;
    }
    Boolean verifyPhone(String phoneNumber)
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


}
