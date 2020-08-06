package com.softserve.edu.greencity.ui.tools;

import com.google.api.client.util.Base64;
import com.google.api.client.util.StringUtils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.BatchDeleteMessagesRequest;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.softserve.edu.greencity.ui.data.User;
import com.softserve.edu.greencity.ui.data.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GMailMethods {

    User gmail = UserRepository.get().userCredentialsForRegistration();
    String user = gmail.getEmail();

    public static List<String> getIdsOfLettersNeeded(Gmail service, String user, ListMessagesResponse messageList) throws IOException {

        List<Message> messages = new ArrayList<>();

        List<String> messagesIds = new ArrayList<>();

        while (messageList.getMessages() != null) {
            messages.addAll(messageList.getMessages());
            if (messageList.getNextPageToken() != null) {
                String pageToken = messageList.getNextPageToken();
                messageList = service.users().messages().list(user)
                        .setPageToken(pageToken).execute();
            } else {
                break;
            }
        }
        for (Message message : messages) {
            messagesIds.add(message.getId());
        }
        return messagesIds;
    }

    public  void batchDelete(Gmail service) throws IOException {
        ListMessagesResponse messageList = service.users().messages().list(user).execute();
        List<String> messagesIds = getIdsOfLettersNeeded(service, user, messageList);
        BatchDeleteMessagesRequest req = new BatchDeleteMessagesRequest();
        BatchDeleteMessagesRequest batchRequ =  req.setIds(messagesIds);

        Gmail.Users.Messages.BatchDelete batchDelete = service.users().messages().batchDelete(user, batchRequ);
        batchDelete.execute();
    }
    public String getVerifLink(Gmail service) throws IOException {
        String query = "from:mailgreencity1@gmail.com subject:Verify your email address  is:unread  ";

        ListMessagesResponse response = service.users().messages().list(user).setQ(query).execute();
        List<String> stringMessages = getIdsOfLettersNeeded(service, user, response);

        String zeroId = stringMessages.get(0);

        Message getMess = service.users().messages().get("gcsignupuser@gmail.com", zeroId).setFormat("FULL").execute();

        String content = StringUtils.newStringUtf8(Base64.decodeBase64(getMess.getPayload().getBody().getData()));

        Pattern p = Pattern.compile("https(.)+user_id=[0-9]+");
        Matcher m = p.matcher(content);
        String verifLink = null;

        if (m.find()) {
            verifLink = content.substring(m.start(), m.end());
            verifLink = verifLink.replace("&amp;", "&");
        }

        return verifLink;
    }
}
