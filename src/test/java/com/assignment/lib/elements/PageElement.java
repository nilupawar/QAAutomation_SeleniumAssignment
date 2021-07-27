package com.assignment.lib.elements;

import com.assignment.lib.types.ElementType;
import com.assignment.lib.types.LocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/*
 *  author : Nilesh Pawar
 * */
public class PageElement {
    private String name;
    private LocatorType locatorType;
    private String locatorValue;
    private PageFrame pageFrame;
    private String testData;
    private final WebDriver driver;
    private ElementType elementType;

    private static final Logger LOGGER = LoggerFactory.getLogger(PageElement.class);

    public PageElement(WebDriver driver, ElementType elementType, String name, LocatorType locatorType, String locatorValue, PageFrame pageFrame, String testData) {
        this.driver = driver;
        this.elementType = elementType;
        this.name = name;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
        this.pageFrame = pageFrame;
        this.testData = testData;
    }

    public PageElement(WebDriver driver, ElementType elementType, String name, LocatorType locatorType, String locatorValue, PageFrame pageFrame) {
        this.driver = driver;
        this.elementType = elementType;
        this.name = name;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
        this.pageFrame = pageFrame;
    }

    public PageElement(WebDriver driver, ElementType elementType, String name, LocatorType locatorType, String locatorValue) {
        this.driver = driver;
        this.elementType = elementType;
        this.name = name;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
    }

    public PageElement(WebDriver driver, ElementType elementType, String name, LocatorType locatorType, String locatorValue, String testData) {
        this.driver = driver;
        this.elementType = elementType;
        this.name = name;
        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
        this.testData = testData;
    }

    private void switchToFrame() {
        driver.switchTo().defaultContent();
        if (pageFrame != null) {
            switch (pageFrame.getIdentificationType()) {
                case INDEX:
                    driver.switchTo().frame(pageFrame.getFrameIndex());
                    break;
                case WEB_ELEMENT:
                    driver.switchTo().frame(pageFrame.getFrameWebElement());
                    break;
                case LOCATOR:
                    driver.switchTo().frame(pageFrame.getFrameLocator());
                    break;
                default:
                    LOGGER.error("Invalid choice for frame identification type '{}'", pageFrame.getIdentificationType());
                    throw new IllegalArgumentException("\"Invalid choice for frame identification type '" + pageFrame.getIdentificationType() + "'");
            }
        }
    }

    /*
     *
     * */
    private List<WebElement> getWebElements() {
        this.switchToFrame();
        switch (this.locatorType) {
            case XPATH:
                return driver.findElements(By.xpath(this.locatorValue));
            case CSS:
                return driver.findElements(By.cssSelector(this.locatorValue));
            case ID:
                return driver.findElements(By.id(this.locatorValue));
            case NAME:
                return driver.findElements(By.name(this.locatorValue));
            case LINK_TEXT:
                return driver.findElements(By.linkText(this.locatorValue));
            case PARTIAL_LINK_TEXT:
                return driver.findElements(By.partialLinkText(this.locatorValue));
            case CLASS_NAME:
                return driver.findElements(By.className(this.locatorValue));
            case TAG_NAME:
                return driver.findElements(By.tagName(this.locatorValue));
            default:
                LOGGER.error("Invalid choice for locator type '{}'", locatorType);
                throw new IllegalArgumentException("Invalid choice for locator type '" + locatorType + "'");
        }
    }

    /*
     *
     * */
    private WebElement getWebElement() {
        this.switchToFrame();
        switch (this.locatorType) {
            case XPATH:
                return driver.findElement(By.xpath(this.locatorValue));
            case CSS:
                return driver.findElement(By.cssSelector(this.locatorValue));
            case ID:
                return driver.findElement(By.id(this.locatorValue));
            case NAME:
                return driver.findElement(By.name(this.locatorValue));
            case LINK_TEXT:
                return driver.findElement(By.linkText(this.locatorValue));
            case PARTIAL_LINK_TEXT:
                return driver.findElement(By.partialLinkText(this.locatorValue));
            case CLASS_NAME:
                return driver.findElement(By.className(this.locatorValue));
            case TAG_NAME:
                return driver.findElement(By.tagName(this.locatorValue));
            default:
                LOGGER.error("Invalid choice for locator type '{}'", locatorType);
                throw new IllegalArgumentException("Invalid choice for locator type '" + locatorType + "'");
        }
    }

    public WebElement getObject() {
        return getWebElement();
    }

    public int getObjectsCount() {
        return getWebElements().size();
    }

    public List<WebElement> getObjects() {
        return getWebElements();
    }

    /*
     *
     * */
    public void enterValue() {
        putValue(testData, false);
    }

    public void enterValue(boolean isPassword) {
        putValue(testData, isPassword);
    }

    public void enterPassword() {
        putValue(testData, true);
    }

    public void enterValue(String value, boolean isPassword) {
        putValue(value, isPassword);
    }


    /*
     *
     * */
    public void selectByVisibleText(String value) {
        LOGGER.debug("Trying to select value '{}' of element '{}' ", value, name);
        WebElement webElement = getWebElement();
        assert webElement != null;
        Select select = new Select(webElement);
        select.selectByVisibleText(value);
        LOGGER.info("Selected value '{}' of element '{}' ", value, name);
    }

    public void selectByValueProperty(String value) {
        LOGGER.debug("Trying to select value '{}' of element '{}' ", value, name);
        WebElement webElement = getWebElement();
        assert webElement != null;
        Select select = new Select(webElement);
        select.selectByValue(value);
        LOGGER.info("Selected value '{}' of element '{}' ", value, name);
    }


    /*
     *
     * */
    public void clickIfPresent() {
        LOGGER.debug("Trying to click on element '{}' of type '{}' ", name, elementType);
        List<WebElement> webElement = getWebElements();
        if (webElement.size() > 0) {
            webElement.get(0).click();
            LOGGER.info("Clicked element '{}' of type '{}'", name, elementType);
            return;
        }
        LOGGER.info("Skipped to click element '{}' of type '{}', element was not present", name, elementType);
    }

    /*
     *
     * */
    public void click() {
        LOGGER.debug("Trying to click on element '{}' of type '{}' ", name, elementType);
        WebElement webElement = getWebElement();
        assert webElement != null;
        webElement.click();
        LOGGER.info("Clicked element '{}' of type '{}'", name, elementType);
    }

    /*
     *
     * */
    public void doubleClick() {
        LOGGER.debug("Trying to double click on element '{}' of type '{}' ", name, elementType);
        WebElement webElement = getWebElement();
        assert webElement != null;
        //TODO - Perform Double click operation
        LOGGER.info("Double clicked element '{}' of type '{}'", name, elementType);
    }

    /*
     *
     * */
    private void putValue(String dataValue, boolean isPassword) {
        LOGGER.debug("Trying to enter value '{}' in element '{}' of type '{}'", isPassword ? "*******" : dataValue, name, elementType);
        WebElement webElement = getWebElement();
        assert webElement != null;
        webElement.sendKeys(dataValue);
        LOGGER.info("Entered value '{}' in element '{}' of type '{}'", isPassword ? "*******" : dataValue, name, elementType);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocatorType(LocatorType locatorType) {
        this.locatorType = locatorType;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }

    public void setPageFrame(PageFrame pageFrame) {
        this.pageFrame = pageFrame;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }
}
