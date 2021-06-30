package com.softserve.edu.greencity.ui.api.mail;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.sun.mail.imap.protocol.FLAGS;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;

import javax.mail.Message;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class use gmail api to be and take 1st unread msg to be continue
 */
public class GoogleMailAPI  {
    @BeforeMethod
   private void offUnnecessary(){
        java.util.logging.Logger
                .getLogger("com.google.api.**")
                .setLevel(Level.OFF);
        java.util.logging.Logger
                .getLogger("com.google.api.client.util.store.FileDataStoreFactory")
                .setLevel(Level.OFF);
        java.util.logging.Logger.
                getLogger("com.google.api.client.util.store.FileDataStoreFactory")
                .setLevel(Level.OFF);
    }
    private static BaseMailAPI emailUtils;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @SneakyThrows(Exception.class)
    @Step
    public  GoogleMailAPI connectToEmail(String mail, String pass) {
        emailUtils = new BaseMailAPI(mail, pass, "smtp.gmail.com", BaseMailAPI.EmailFolder.INBOX);
        return this;
    }

    @SneakyThrows(Exception.class)
    @Step("get array of messages")
    public Message[] getMassagesBySubject(String subject, boolean unread, int maxToSearch, long timeToWait){
        waitForMessagesWithSubject(subject,unread,maxToSearch,timeToWait);
        return emailUtils.getMessagesBySubject(subject, unread,  maxToSearch);
    }

    @SneakyThrows(Exception.class)
    @Step("get array of messages")
    public Message[] getMassagesBySubject(String subject, boolean unread, int maxToSearch){
        return emailUtils.getMessagesBySubject(subject, unread,  maxToSearch);
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

    //TODO: split logic to small methods,
    //TODO: split Matcher to another class as individual functional
    @Step("get green city auth confirm link from first mail")
    @SneakyThrows(Exception.class)
    public String getconfirmURL(String mail, String pass,int maxTries) {
        connectToEmail(mail,pass);
        waitForMessagesWithSubject("Verify your email address",true,5,30);
        String link = "";
        int count = 0;
        Message[] email;
        do {
            email = emailUtils.getMessagesBySubject("Verify your email address", true, 5);
        } while ((email.length == 0) && (++count < maxTries));

        if(email.length == 0) {
            return null;
        }

        String mailContent = emailUtils.getMessageContent(email[0]).trim().replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile(">https://ita-social-projects[^\"<]+");
        final Matcher m = pattern.matcher(mailContent);
        m.find();
        link = mailContent.substring( m.start(), m.end() )
                .replace(">","")
                .replace("amp;","");


        return link;
    }

    @Step("get green city auth confirm link from first mail")
    @SneakyThrows(Exception.class)
    public String getconfirmURL(String subject, String mail, String pass, String regex, int maxTries) {
        connectToEmail(mail,pass);
        waitForMessagesWithSubject(subject,true,5,30);
        String link = "";
        int count = 0;
        Message[] email;
        do {
            email = emailUtils.getMessagesBySubject(subject, true, 5);
        } while ((email.length == 0) && (++count < maxTries));

        if(email.length == 0) {
            return null;
        }

        String mailContent = emailUtils.getMessageContent(email[0]).trim().replaceAll("\\s+", "");
            Pattern pattern = Pattern.compile(regex);
            final Matcher m = pattern.matcher(mailContent);
            m.find();
            link = mailContent.substring( m.start(), m.end() )
                    .replace("#","%23")
                    .replace("3D","")
                    .replace("amp;","")
                    .replace("=","")
                    .replace("token","token=")
                    .replace("user_id","user_id=");
            return link;
    }

    @SneakyThrows
    public int getNumberMailsBySubject(String mail, String password, String subject, int maxTries) {
        connectToEmail(mail, password);
        int count = 0;
        while (true) {
            Message[] emails = emailUtils.getMessagesBySubject(subject, true, 5);
            int num = emails.length;
            if (++count == maxTries) {
                return 0;
            }
            return num;
        }
    }

    @SneakyThrows(Exception.class)
    @Step("get array of messages")
    public void waitForMessagesWithSubject(String subject, boolean unread, int maxToSearch, long timeToWaitInSeconds){
        logger.info("Wait for email with subject: " + subject);
        User user = UserRepository.get().googleUserCredentials();
        connectToEmail(user.getEmail(),user.getPassword());
        long start = System.nanoTime()/ 1000000000;
        long end = start + ((long) timeToWaitInSeconds);
        boolean isWaiting = true;
        while (isWaiting) {
           int emailCount = emailUtils.getMessagesBySubject(subject, unread,  maxToSearch).length ;
            if (emailCount > 0
                    || System.nanoTime()/1000000000 - end == 0 ) {
                logger.info("emails with subject founds: " + emailCount);
                isWaiting = false;
            }
        }
    }

    @SneakyThrows(Exception.class)
    @Step("get array of messages")
    public void waitForMessagesWithSubject(String subject, boolean unread, int maxToSearch, long timeToWaitInSeconds, String email, String emailPassword){
        logger.info("Wait for email with subject: " + subject);
        long start = System.nanoTime()/ 1000000000;
        long end = start + ((long) timeToWaitInSeconds);
        boolean isWaiting = true;
        connectToEmail(email, emailPassword);
        while (isWaiting) {
            int emailCount = emailUtils.getMessagesBySubject(subject, unread,  maxToSearch).length ;
            if (emailCount > 0
                    || System.nanoTime()/1000000000 - end == 0 )
            {       logger.info("emails with " + subject +" founds: " + emailCount);
                isWaiting = false;
            }
        }
    }
}
