package com.softserve.edu.greencity.ui.tools;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.BatchDeleteMessagesRequest;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GMailBatchDelete {

    public static void batchDelete(Gmail service) throws IOException {
        ListMessagesResponse messageList = service.users().messages().list("gcsignupuser@gmail.com").execute();

        List<Message> messages = new ArrayList<>();

        List<String> stringMessages = new ArrayList<>();

        while (messageList.getMessages() != null) {
            messages.addAll(messageList.getMessages());
            if (messageList.getNextPageToken() != null) {
                String pageToken = messageList.getNextPageToken();
                messageList = service.users().messages().list("gcsignupuser@gmail.com")
                        .setPageToken(pageToken).execute();
            } else {
                break;
            }
        }

        for (Message message : messages) {
            stringMessages.add(message.getId());

        }

        BatchDeleteMessagesRequest req = new BatchDeleteMessagesRequest();
        BatchDeleteMessagesRequest batchRequ =  req.setIds(stringMessages);

        Gmail.Users.Messages.BatchDelete batchDelete = service.users().messages().batchDelete("gcsignupuser@gmail.com", batchRequ);

        batchDelete.execute();
    }
}
