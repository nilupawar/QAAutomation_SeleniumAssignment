package com.assignment.test.glue;

import com.assignment.lib.config.TestScenario;
import com.assignment.test.pages.AptHousingPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AptHousing {
    private final AptHousingPage aptHousingPage;

    public AptHousing(TestScenario testScenario) {
        aptHousingPage = new AptHousingPage(testScenario);
    }

    @When("^ascending price sort order is selected$")
    public void itemsAreSortedByPriceInAscendingOrder() {
        aptHousingPage.sortItemsByAscendingPrice();
    }

    @Then("^items should be displayed in price ascending order$")
    public void itemsShouldBeDisplayedInPriceAscendingOrder() {
        Assert.assertTrue(aptHousingPage.isSortedByPriceAsc());
    }

    @When("^descending price sort order is selected$")
    public void itemsAreSortedByPriceInDescendingOrder() {
        aptHousingPage.sortItemsByDescendingPrice();
    }

    @Then("^items should be displayed in price descending order$")
    public void itemsShouldBeDisplayedInPriceDescendingOrder() {
        Assert.assertTrue(aptHousingPage.isSortedByPriceDesc());
    }

    @Then("^user should be on apts/housing page$")
    public void isValidAptHousingPage() {
        Assert.assertTrue(aptHousingPage.isValidPage());
    }

    @When("user search for {string} apartment")
    public void searchItem(String searchItem) {
        aptHousingPage.searchItem(searchItem);
    }

    @Then("default sort should be set to {string}")
    public void checkDefaultSortCriteria(String defaultSort) {
        Assert.assertEquals(defaultSort,aptHousingPage.getDefaultSortOption());
    }

    @Then("items should be sorted based on newest sort criteria")
    public void newestSortCriteria() {
        Assert.assertTrue(aptHousingPage.isSortedByNewest());
    }

}
