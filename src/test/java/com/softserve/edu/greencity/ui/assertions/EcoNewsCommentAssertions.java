package com.softserve.edu.greencity.ui.assertions;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.softserve.edu.greencity.ui.locators.comments.CommentLocator.*;

public class EcoNewsCommentAssertions {
    public static void assertNewComment(List<WebElement> comments, String expectedComment) {
        boolean commentMatch = false;
        for (WebElement comment : comments) {
            String text = comment.findElement(COMMENT_CURRENT_TEXT.getPath()).getText();
            if (expectedComment.equals(text.trim())) {
                commentMatch = true;
            }
        }
        Assert.assertTrue(commentMatch, "Such comment doesn't exist:" + expectedComment);
    }
}
