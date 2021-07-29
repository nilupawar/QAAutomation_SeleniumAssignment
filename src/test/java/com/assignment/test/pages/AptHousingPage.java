package com.assignment.test.pages;

import com.assignment.lib.config.TestScenario;
import com.assignment.lib.core.BasePage;
import com.assignment.lib.elements.PageElement;
import com.assignment.lib.types.ElementType;
import com.assignment.lib.types.LocatorType;
import com.assignment.lib.util.SortType;
import com.assignment.lib.util.Utility;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.assignment.lib.util.SortType.ASC;
import static com.assignment.lib.util.SortType.DESC;

public class AptHousingPage extends BasePage {
    public AptHousingPage(TestScenario testScenario) {
        super(testScenario, "apartments / housing for rent");
    }

    private final Map<String, String> sortDropDownTitles = Map.of("upcoming", "show upcoming open houses",
            "newest", "show newest matches first",
            "priceasc", "sort by price, lowest to highest",
            "pricedsc", "sort by price, highest to lowest");

    //------------------------------------------------------------------------------------------------------------------
    // Page elements
    PageElement sortByDropDown = new PageElement(driver, ElementType.DROP_BOX, "Sort by", LocatorType.XPATH, "//div[@aria-expanded='false'][@aria-label='sort options']/ul");
    PageElement sortByDropDownItem = new PageElement(driver, ElementType.PLAIN_TEXT, "<PLACE_HOLDER>", LocatorType.XPATH, "<PLACE_HOLDER>");
    PageElement searchResults = new PageElement(driver, ElementType.TABLE, "Search Result", LocatorType.ID, "search-results");
    PageElement searchTextBox = new PageElement(driver, ElementType.TEXT_BOX, "Search apartments / housing for rent", LocatorType.ID, "query");
    PageElement searchButton = new PageElement(driver, ElementType.BUTTON, "Search button", LocatorType.XPATH, "//span[text()='press to search craigslist']/parent::button");

    //------------------------------------------------------------------------------------------------------------------
    // Public methods
    //------------------------------------------------------------------------------------------------------------------
    public void sortItemsByAscendingPrice() {
        expandSortDropDown();
        sortByDropDownItem.setName("Price Ascending");
        sortByDropDownItem.setLocatorValue("//a[@title=\"" + sortDropDownTitles.get("priceasc") + "\"]/parent::li");
        sortByDropDownItem.click();
    }

    public void sortItemsByDescendingPrice() {
        expandSortDropDown();
        sortByDropDownItem.setName("Price Descending");
        sortByDropDownItem.setLocatorValue("//a[@title='" + sortDropDownTitles.get("pricedsc") + "']/parent::li");
        sortByDropDownItem.click();
    }

    public boolean isSortedByPriceAsc() {
        return isPriceSorted(ASC);
    }

    public boolean isSortedByPriceDesc() {
        return isPriceSorted(DESC);
    }

    public boolean isSortedByNewest() {
        return isDateSorted(DESC);
    }

    public void searchItem(String searchText) {
        searchTextBox.enterValue(searchText, false);
        searchButton.click();
        searchTextBox.waitUntilClickable(Utility.parseIntTestConfig("object.wait.time.macro"));
        Assert.assertTrue(isValidPage());
    }

    public String getDefaultSortOption() {
        return sortByDropDown.getObject()
                .findElement(By.xpath(".//li[@aria-selected='true']"))
                .getText()
                .trim();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Private methods
    //------------------------------------------------------------------------------------------------------------------
    private boolean isPriceSorted(SortType sortType) {
        List<Double> price = getItemsPrice();
        cucumberResultLog("Price values order in application : " + price.toString());
        return Utility.isSorted(price, sortType);
    }

    private boolean isDateSorted(SortType sortType) {
        List<Date> dates = getItemsDate();
        cucumberResultLog("Dates values order in application : " + dates.toString());
        return Utility.isSorted(dates, sortType);
    }

    private void expandSortDropDown() {
        sortByDropDown.clickIfPresent();
    }

    private List<Double> getItemsPrice() {
        List<WebElement> allSearchedItems = getAllSearchedResultItems();
        return allSearchedItems.stream()
                .map(item -> {
                    try {
                        return getItemPrice(item);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    private List<WebElement> getAllSearchedResultItems() {
        return searchResults.getObject().findElements(By.xpath("//li[@class='result-row']"));
    }

    private Double getItemPrice(WebElement item) throws ParseException {
        String currencySymbol = "[£$€]";
        String price = item.findElement(By.xpath(".//span[@class='result-price']")).getText();
        price = price.replaceAll(currencySymbol, "");
        return NumberFormat.getNumberInstance(Locale.ENGLISH).parse(price).doubleValue();
    }

    private List<Date> getItemsDate() {
        List<WebElement> allSearchedItems = getAllSearchedResultItems();
        return allSearchedItems.stream()
                .map(item -> {
                    try {
                        return getItemDate(item);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    private Date getItemDate(WebElement item) throws ParseException {
        // Assumption that year will not be passed in the parameter
        String dateValue = item.findElement(By.tagName("time")).getAttribute("datetime");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        return format.parse(dateValue);
    }
}
