package com.shift.selenium.scripts;


import com.shift.selenium.pages.HomePage;
import com.shift.selenium.pages.SecondPage;
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
    private WebDriver driver;
    private HomePage homePage;
    private Wait<WebDriver> wait;

    @Test
    public void testElementsExistenceAndDefaultValues() {
        assertThat(homePage.isTextPresent("Seleniumテスト用ファイル"), is(true));
        assertThat(homePage.getInputElementsByType("checkbox").size(), is(3));
        assertThat(homePage.getFemaleRadioboxStatus(), is(false));
        assertThat(homePage.getMaleRadioboxStatus(), is(true));
        assertThat(homePage.getSelectedCountry(), is("日本"));
    }

    @Test
    public void testControlLogic() {
        // check alert
        homePage.enterName("Kevin Wang");
        homePage.fireNameAlert();
        verifyAlertWithText("Kevin Wang");
    }

    @Test
    public void testCalculation() {
        homePage.calculate(2.0, 3.1);
        wait = new WebDriverWait(driver, 10);
        assertThat(homePage.getCalculationResult(), is("5.1"));
    }

    @Test
    public void testPageTransition() {
        SecondPage secondPage = homePage.moveToNextPage();
        assertThat(secondPage.isTextPresent("Seleniumテスト用ファイル02"), is(true));
    }

    private void verifyAlertWithText(String text) {
        try {
            wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            assertThat(alert.getText(), is(text));
        } catch (Exception e) {
            //exception handling
        }
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
