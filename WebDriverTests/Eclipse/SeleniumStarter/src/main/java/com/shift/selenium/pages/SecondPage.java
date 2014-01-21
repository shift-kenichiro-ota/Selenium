package com.shift.selenium.pages;

import com.shift.selenium.utils.Criteria;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created with IntelliJ IDEA.
 * User: lei.wang
 * Date: 13/11/03
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class SecondPage extends PageTemplate {

    // fixed page url and needs to change to fit into your case
    private static final String url = "file:///C:/Users/lei.wang/Desktop/Selenium/%E3%83%86%E3%82%B9%E3%83%88%E7%94%A8HTML/html/formSample02.html";

    // page loading wait object
    private Wait<WebDriver> wait;

    // page constructor
    public SecondPage(WebDriver driver) {
        super(driver, url);
    }

    // symbolic element to identify when loading this page
    @FindBy(how = How.XPATH, using = "//input[@type='submit' and @name='submit']")
    private WebElement buttonSubmit;

    // initialize and load the page until we can see the above symbolic element
    @Override
    public void init() {
        wait = new WebDriverWait(driver, 20);
        wait.until(Criteria.visibilityOfElementLocated(buttonSubmit));
    }
}
