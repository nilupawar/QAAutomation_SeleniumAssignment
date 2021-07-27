package com.assignment.test.pages;

import com.assignment.lib.config.TestCase;
import com.assignment.lib.core.BasePage;
import com.assignment.lib.elements.PageElement;
import com.assignment.lib.types.ElementType;
import com.assignment.lib.types.LocatorType;
import com.assignment.lib.util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.assignment.lib.util.SortType.ASC;
import static com.assignment.lib.util.SortType.DESC;

public class AptHousingPage extends BasePage {
    public AptHousingPage(TestCase testCase) {
        super(testCase, "apartment / housing for rent");
    }


    private final Map<String, String> sortDropDownTitles = Map.of("upcoming", "show upcoming open houses",
            "newest", "show newest matches first",
            "priceasc", "sort by price, lowest to highest",
            "pricedsc", "sort by price, highest to lowest");

    // --------------------------------------------------------------------------------------
    // Page elements
    PageElement sortByDropDown = new PageElement(driver, ElementType.DROP_BOX, "Sort by", LocatorType.XPATH, "//div[@aria-expanded=\"false\"][@aria-label=\"sort options\"]");
    PageElement sortByDropDownItem = new PageElement(driver, ElementType.PLAIN_TEXT, "<PLACE_HOLDER>", LocatorType.XPATH, "<PLACE_HOLDER>");

    // --------------------------------------------------------------------------------------

    public void sortItemsByAscendingPrice() {
        expandSortDropDown();
        sortByDropDownItem.setName("Price Ascending");
        sortByDropDownItem.setLocatorValue("//a[@title=" + sortDropDownTitles.get("priceasc") + "]/parent::li");
    }

    public boolean isSortedByPriceAsc() {
        List<Integer> price = getItemsPrice();
        return Utility.isSorted(price, ASC);
    }

    public boolean isSortedByPriceDesc() {
        List<Integer> price = getItemsPrice();
        return Utility.isSorted(price, DESC);
    }

    public void sortItemsByDescendingPrice() {
        expandSortDropDown();
        sortByDropDownItem.setName("Price Descending");
        sortByDropDownItem.setLocatorValue("//a[@title=" + sortDropDownTitles.get("pricedsc") + "]/parent::li");
    }

    private void expandSortDropDown() {
        sortByDropDown.clickIfPresent();
    }

    private List<Integer> getItemsPrice() {
        //TODO - Implement this
        return new ArrayList<>();
    }

    private List<String> getItemsDate(){
        //TODO - Implement this
        return new ArrayList<>();
    }

}
