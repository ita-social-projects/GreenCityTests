package com.softserve.edu.greencity.ui.tools.api.mail;

import com.sun.mail.imap.protocol.FLAGS;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import javax.mail.Message;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class use gmail api to be and take 1st unread msg to be continue
 */
public class GoogleMailAPI {
    private static BaseMailAPI emailUtils;
    @SneakyThrows(Exception.class)
    @Step
    public  GoogleMailAPI connectToEmail(String mail, String pass) {
        emailUtils = new BaseMailAPI(mail, pass, "smtp.gmail.com", BaseMailAPI.EmailFolder.INBOX);
        return this;
    }

    //TODO: split logic to small methods,
    //TODO: split Matcher to another class as individual functional
    @Step("get green city auth confirm link from first mail")
    @SneakyThrows(Exception.class)
    public String getconfirmURL(String mail, String pass,int maxTries) {
        connectToEmail(mail,pass);
        String link = "";
        int count = 0;
        //!!!!
        while (true) {
            Message[] email = emailUtils.getMessagesBySubject("Verify your email address", true, 5);
            String mailContent = emailUtils.getMessageContent(email[0]).trim().replaceAll("\\s+", "");
            Pattern pattern = Pattern.compile("https://greencity[^\"]+");
            final Matcher m = pattern.matcher(mailContent);
            m.find();
            link = mailContent.substring( m.start(), m.end() )
                    .replace("3D","")
                    .replace("amp;","")
                    .replace("=","")
                    .replace("token","token=")
                    .replace("user_id","user_id=");
            if (++count == maxTries) {
                return null;
            }
            return link;
        }
    }

    @SneakyThrows
    @Step("get Messages By Subject")
    public  void clearMail(String mail, String pass) {
        connectToEmail(mail,pass);
        Message[] msg = emailUtils.getAllMessages();
        for (Message message :msg) {
            message.setFlag(FLAGS.Flag.DELETED, true);
        }
    }
}

