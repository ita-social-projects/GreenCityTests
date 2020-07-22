package com.softserve.edu.greencity.ui.tools;

import com.google.api.client.util.Base64;
import com.google.api.client.util.StringUtils;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GMailVerification {

    public static String getVerifLink(Gmail service) throws IOException {
        String query = "from:mailgreencity1@gmail.com subject:Verify your email address  is:unread  ";

        ListMessagesResponse response = service.users().messages().list("gcsignupuser@gmail.com").setQ(query).execute();

        List<Message> messages = new ArrayList<>();

        List<String> stringMessages = new ArrayList<>();

        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().messages().list("gcsignupuser@gmail.com").setQ(query)
                        .setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        for (Message message : messages) {
            stringMessages.add(message.getId());
            System.out.println(message.getId());
        }

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
        System.out.println(verifLink);
        return verifLink;
    }

}
