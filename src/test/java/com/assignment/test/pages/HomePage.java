package com.assignment.test.pages;

import com.assignment.lib.config.TestScenario;
import com.assignment.lib.core.BasePage;
import com.assignment.lib.elements.PageElement;
import com.assignment.lib.types.ElementType;
import com.assignment.lib.types.LocatorType;


public class HomePage extends BasePage {
    public HomePage(TestScenario testScenario) {
        super(testScenario, "craigslist");
    }

    // --------------------------------------------------------------------------------------
    // Page elements
    public final PageElement languageSelect = new PageElement(driver, ElementType.DROP_BOX, "language Selection", LocatorType.ID, "chlang");
    public final PageElement aptHousingLink = new PageElement(driver, ElementType.LINK, "Apt/Housing link", LocatorType.LINK_TEXT, "apts / housing");

    // --------------------------------------------------------------------------------------


}
