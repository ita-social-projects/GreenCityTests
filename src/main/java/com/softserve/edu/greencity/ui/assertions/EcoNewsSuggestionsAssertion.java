package com.softserve.edu.greencity.ui.assertions;

import com.softserve.edu.greencity.data.econews.Tag;
import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class EcoNewsSuggestionsAssertion {

    public static void assertSuggestionsByDate(ItemsContainer suggestions, Tag suitableTag, boolean ascendingOrder) {
        //verificate that all news have suitableTag
        for(int i=0;i<suggestions.getItemComponentsCount()-1;i++){
            List<WebElement> newsTags = suggestions.chooseNewsByNumber(i).getTags();
            boolean flag = false;
            for(WebElement tag : newsTags){
                if(tag.getText().equals(suitableTag.name())){
                    flag = true;
                    break;
                }
            }
            Assert.assertTrue(flag,(i+1)+" news doesn`t have tag " + suitableTag.name());
        }

        if(!ascendingOrder) {
            for (int i = 0; i < suggestions.getItemComponentsCount() - 1; i++) {
                Assert.assertTrue(suggestions.chooseNewsByNumber(i).getCreationDate()
                        .compareTo(suggestions.chooseNewsByNumber(i + 1).getCreationDate()) >= 0);
            }
        }
        else {
            for (int i = 0; i < suggestions.getItemComponentsCount() - 1; i++) {
                Assert.assertTrue(suggestions.chooseNewsByNumber(i).getCreationDate()
                        .compareTo(suggestions.chooseNewsByNumber(i + 1).getCreationDate()) <= 0);
            }
        }
    }
}
