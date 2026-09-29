package MailSender;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class SendEmail 

{
    public boolean isEmailSent(String body, String recipient)
    {
        boolean flag=false;
        try
        {
        final String senderEmail = "satyamgadekar@gmail.com";
        final String appPassword = "ukep hqwj hugc rhrx";
         Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
               new Authenticator()
                {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(senderEmail, appPassword);
                    }
                });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipient));
            message.setSubject("DATABASE TAMPER DETECTION ALERT");
            message.setText(body);
            Transport.send(message);

            System.out.println("Email sent successfully.");
            flag=true;
        }
        catch(Exception ex)
        {
            
        }
        return flag;
    }
}
