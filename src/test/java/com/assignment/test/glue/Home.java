package com.assignment.test.glue;

import com.assignment.lib.config.TestCase;
import com.assignment.test.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.net.MalformedURLException;

public class Home {
    private final HomePage homePage;

    public Home(TestCase testCase) {
        homePage = new HomePage(testCase);
    }

    @Given("^user is at home page$")
    public void userIsOnHomePage() throws MalformedURLException {
        homePage.navigateToApplication();
    }

    @And("selected language is {string}")
    public void selectedLanguageIs(String language) {
        homePage.languageSelect.selectByVisibleText(language);
    }

    @And("^user navigates to (?:apts\\/housing|flats\\/housing) page$")
    public void userNavigatesToAptHousingPage() {
        homePage.aptHousingLink.click();
    }
}
