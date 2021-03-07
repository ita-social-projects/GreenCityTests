package com.softserve.edu.greencity.ui.assertions;

import com.softserve.edu.greencity.ui.pages.econews.ItemComponent;
import com.softserve.edu.greencity.ui.pages.econews.ItemsContainer;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class EcoNewsSuggestionsAssertion {

    public static void assertSuggestionsByDate(ItemsContainer suggestions, boolean ascendingOrder) {
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
