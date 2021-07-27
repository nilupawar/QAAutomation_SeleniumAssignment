package com.assignment.test.glue;

import com.assignment.lib.config.TestCase;
import com.assignment.test.pages.AptHousingPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AptHousing {
    private final AptHousingPage aptHousingPage;

    public AptHousing(TestCase testCase) {
        aptHousingPage = new AptHousingPage(testCase);
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

    @Then("^user should be on apt housing page$")
    public void isValidAptHousingPage() {
        Assert.assertTrue(aptHousingPage.isValidPage());
    }
}
