using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using OpenQA.Selenium.Firefox;
using Shift.Selenium.Helpers;
using Shift.Selenium.Pages;

namespace Shift.Selenium.Scripts
{
    [TestClass]
    public class HomePageTest
    {
        private IWebDriver driver;
        private HomePage homePage;
        private IWait<IWebDriver> wait;

        [TestInitialize]
        public void TestSetUp()
        {
            FirefoxBinary ffBinary = new FirefoxBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            FirefoxProfile ffProfile = new FirefoxProfile();
            driver = new FirefoxDriver(ffBinary, ffProfile);
            homePage = (HomePage)new HomePage(driver);
            homePage.initializeWebElement();
        }

        [TestCleanup]
        public void TestTearDown()
        {
            driver.Quit();
        }

        [TestMethod]
        public void ElementsExistenceAndDefaultValuesTest()
        {
            Assert.IsTrue(homePage.IsTextPresent("Seleniumテスト用ファイル"));
            Assert.AreEqual(homePage.GetInputElementsByType("checkbox").Count, 3);
            Assert.IsFalse(homePage.FemaleRadioboxStatus);
            Assert.IsTrue(homePage.MaleRadioboxStatus);
            Assert.AreEqual(homePage.SelectedCountry, "日本");
        }

        [TestMethod]
        public void ControlLogicTest()
        {
            // check alert
            homePage.EnterName("Kevin Wang");
            homePage.FireNameAlert();
            VerifyAlertWithText("Kevin Wang");
        }


        [TestMethod]
        public void testCalculation()
        {
            homePage.Calculate(2.0, 3.1);
            wait = new WebDriverWait(driver, new TimeSpan(0, 0, 10));
            Assert.AreEqual(homePage.CalculationResult, "5.1");

        }

        [TestMethod]
        public void PageTransitionTest()
        {
            SecondPage secondPage = homePage.MoveToNextPage();
            Assert.IsTrue(secondPage.IsTextPresent("Seleniumテスト用ファイル02"));

        }

        private void VerifyAlertWithText(string text)
        {
            try
            {
                wait = new WebDriverWait(driver, new TimeSpan(0, 0, 2));
                wait.Until(Extensions.AlertIsPresent());
                IAlert alert = driver.SwitchTo().Alert();
                alert.Accept();
                Assert.Equals(alert.Text, text);
            }
            catch (Exception e)
            {
                //exception handling
            }
        }
    }
}