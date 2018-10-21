import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * Class for sending email for email verification and notification
 *
 * @version September 16, 2018
 */

public class SendEmail {

    /**
     * Send email
     * @param recipient email address of recipient
     * @param subject subject header of email
     * @param msg message to be sent to recipient
     * @return 0 if success, > 0 if fail
     */
    public static int sendEmail(String recipient, String subject, String msg){
        String sender = "boilerideboileride@gmail.com";
        String password = "boileride18!";

        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, password);
                    }
                });

        try {
            System.out.println("Email sending...");
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

            System.out.println("Email sent successfully");
        } catch (AddressException e) {
            return 1;
        } catch (MessagingException e) {
            e.printStackTrace();
            return 2;
        }
        return 0;
    }

    public static void main(String[] args) {
        int ret = sendEmail("ochow@purdue.edu", "Boileride", "Boileride email sent!");
        System.out.println(ret);
    }
}
