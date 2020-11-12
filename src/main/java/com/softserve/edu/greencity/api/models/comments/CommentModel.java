package com.softserve.edu.greencity.api.models.comments;

//{
//        "id": 2595,
//        "author": {
//        "id": 302,
//        "name": "My name is User_Lviv",
//        "userProfilePicturePath": "https://storage.cloud.google.com/staging.greencity-c5a3a.appspot.com/8ebf14f9-1e5b-4ec5-84b8-0241e91d71a6"
//        },
//        "text": "string",
//        "modifiedDate": "2020-11-12T20:13:00.071862"
//        }
public class CommentModel {
    public Integer id;
    public CommentAuthor author;
    public String text;
    public String modifiedDate;

    public CommentModel() {
    }

}
