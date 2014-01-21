package com.shift.selenium.scripts;


import selenium.pages.HomePage;
import selenium.pages.SecondPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: lei.wang
 * Date: 13/11/03
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class HomePageTest {
    def driver
    def homePage

    @Test
    public void testElementsExistenceAndDefaultValues() {
        assert homePage.isTextPresent("Seleniumテスト用ファイル")
        assert homePage.getInputElementsByType("checkbox").size() == 3
        assert !homePage.getFemaleRadioboxStatus()
        assert homePage.getMaleRadioboxStatus()
        assert homePage.getSelectedCountry() == "日本"
    }

    @Test
    public void testControlLogic() {
        // check alert
        homePage.enterName("Kevin Wang")
        homePage.fireNameAlert()
        verifyAlertWithText("名前: Kevin Wang")
    }

    @Test
    public void testCalculation() {
        homePage.calculate(2.0, 3.1)
        def wait = new WebDriverWait(driver, 10)
        assert homePage.getCalculationResult() == "5.1"
    }

    @Test
    public void testPageTransition() {
        SecondPage secondPage = homePage.moveToNextPage()
        assert secondPage.isTextPresent("Seleniumテスト用ファイル02")
    }

    private void verifyAlertWithText(String text) {
        def wait = new WebDriverWait(driver, 2)
        wait.until(ExpectedConditions.alertIsPresent())
        Alert alert = driver.switchTo().alert()
        assert alert.getText() == text
        alert.accept()
    }

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        homePage = (HomePage) new HomePage(driver).get();
        homePage.initializeWebElement();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
