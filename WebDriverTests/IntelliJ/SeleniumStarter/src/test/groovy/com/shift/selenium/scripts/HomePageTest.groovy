package com.shift.selenium.scripts

import org.openqa.selenium.Alert
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import selenium.pages.HomePage
import selenium.pages.SecondPage
import spock.lang.Specification

/**
 * Created by kenichiro_ota on 2014/01/22.
 */
class HomePageTest extends Specification {
    WebDriver driver
    HomePage homePage

    void setup() {
        driver = new FirefoxDriver()
        homePage = (HomePage) new HomePage(driver).get()
        homePage.initializeWebElement()
    }

    void cleanup() {
        driver.quit()
    }

    def "ElementsExistenceAndDefaultValues"() {
        expect:
        assert homePage.isTextPresent("Seleniumテスト用ファイル")
        assert homePage.getInputElementsByType("checkbox").size() == 3
        assert !homePage.getFemaleRadioboxStatus()
        assert homePage.getMaleRadioboxStatus()
        assert homePage.getSelectedCountry() == "日本"
    }


    def "ControlLogic"() {
        when:
        // check alert
        homePage.enterName("Kevin Wang")
        homePage.fireNameAlert()

        then:
        verifyAlertWithText("名前: Kevin Wang")
    }

    def "Calculation"() {
        when:
        homePage.calculate(2.0, 3.1)
        def wait = new WebDriverWait(driver, 10L)

        then:
        assert homePage.getCalculationResult() == "5.1"
    }

    public void testPageTransition() {
        when:
        SecondPage secondPage = homePage.moveToNextPage()

        then:
        assert secondPage.isTextPresent("Seleniumテスト用ファイル02")
    }

    void verifyAlertWithText(String text) {
        def wait = new WebDriverWait(driver, 2L)
        wait.until(ExpectedConditions.alertIsPresent())
        Alert alert = driver.switchTo().alert()
        assert alert.getText() == text
        alert.accept()
    }

}
