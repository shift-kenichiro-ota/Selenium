using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OpenQA.Selenium.Support.UI;
using OpenQA.Selenium.Support.PageObjects;
using OpenQA.Selenium;
using Shift.Selenium.Helpers;

namespace Shift.Selenium.Pages
{
    public class HomePage : PageTemplate
    {
        // fixed page url and needs to change to fit into your case
        private const string url = "file:///C:/Users/lei.wang/Desktop/Selenium/%E3%83%86%E3%82%B9%E3%83%88%E7%94%A8HTML/index.html";

        // page loading wait object
        private IWait<IWebDriver> wait;

        // page constructor
        public HomePage(IWebDriver driver)
            : base(driver, url)
        {
        }

        // symbolic element to identify when loading this page
        [FindsBy(How = How.XPath, Using = "//input[@type='submit' and @name='submit']")]
        private IWebElement buttonSubmit;

        // initialize and load the page until we can see the above symbolic element
        public override void Init()
        {
            wait = new WebDriverWait(driver, new TimeSpan(0, 0, 20));
            wait.Until(Extensions.ElementIsVisible(buttonSubmit));
        }

        // necessary data structure
        public enum Reasons
        {
            BOOKSTORE,
            FRIEND
        }

        public enum Countries
        {
            JAPAN,
            ENGLAND,
            USA,
            CHINA
        }

        public enum Browsers
        {
            IE5,
            IE4,
            IE3,
            NN6,
            NN4,
            NN3,
            OTHER
        }

        // necessary private web elements
        [FindsBy(How = How.XPath, Using = "//input[@type='text' and @name='PRICE01']")]
        private IWebElement textPrice1;
        [FindsBy(How = How.XPath, Using = "//input[@type='text' and @name='PRICE02']")]
        private IWebElement textPrice2;
        [FindsBy(How = How.XPath, Using = "//input[@type='text' and @name='RESULT']")]
        private IWebElement textResult;
        [FindsBy(How = How.XPath, Using = "//input[@type='button' and @name='calcBtn']")]
        private IWebElement buttonCalculate;
        [FindsBy(How = How.XPath, Using = "//input[@type='button' and @name='ALERT']")]
        private IWebElement buttonAlert;
        [FindsBy(How = How.XPath, Using = "//input[@type='text' and @id='name1']")]
        private IWebElement textName;
        [FindsBy(How = How.XPath, Using = "//textarea[@id='kansou1']")]
        private IWebElement textareaComment;
        [FindsBy(How = How.XPath, Using = "//input[@type='radio' and @id='seibetsu2']")]
        private IWebElement radioFemale;
        [FindsBy(How = How.XPath, Using = "//input[@type='radio' and @id='seibetsu1']")]
        private IWebElement radioMale;
        [FindsBy(How = How.XPath, Using = "//input[@type='checkbox' and @id='riyuu1']")]
        private IWebElement checkReason1;
        [FindsBy(How = How.XPath, Using = "//input[@type='checkbox' and @id='riyuu2']")]
        private IWebElement checkReason2;
        private SelectElement selectCountry;
        private SelectElement selectBrowser;

        // web element initialization, you can use either PageFactory or an initialization method
        public void initializeWebElement()
        {
            selectCountry = new SelectElement(GetElementById("kokumei1"));
            selectBrowser = new SelectElement(GetElementById("browser1"));
        }

        // actions
        public void EnterName(string name)
        {
            textName.SendKeys(name);
        }

        public void FireNameAlert()
        {
            buttonAlert.Click();
        }

        public void EnterComment(string comment)
        {
            textareaComment.SendKeys(comment);
        }

        public void SelectGender(bool gender)
        {
            if (gender)
            {             // female is true
                radioFemale.Click();
            }
            else
            {             // male is false
                radioMale.Click();
            }
        }

        public void SelectReason(Reasons reason)
        {
            switch (reason)
            {
                case Reasons.BOOKSTORE:
                    checkReason1.Click();
                    break;

                case Reasons.FRIEND:
                    checkReason2.Click();
                    break;

                default:
                    break;
            }
        }

        public void SelectCountry(Countries country)
        {
            selectCountry.SelectByValue(country.ToString());
        }

        public void SelectBrowsers(Browsers browser)
        {
            selectBrowser.SelectByValue(browser.ToString());
        }

        public void Calculate(double price1, double price2)
        {
            textPrice1.SendKeys(price1.ToString());
            textPrice2.SendKeys(price2.ToString());
            buttonCalculate.Click();
        }

        public SecondPage MoveToNextPage()
        {
            buttonSubmit.Click();
            return (SecondPage)new SecondPage(driver);
        }

        // properties
        public string CalculationResult
        {
            get
            {
                return textResult.GetAttribute("value");
            }
        }

        public bool MaleRadioboxStatus
        {
            get
            {
                return radioMale.Selected;
            }
        }

        public bool FemaleRadioboxStatus
        {
            get
            {
                return radioFemale.Selected;
            }
        }

        public string SelectedCountry
        {
            get
            {
                return selectCountry.SelectedOption.Text;
            }
        }
    }
}
