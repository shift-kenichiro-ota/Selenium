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
    public class SecondPage : PageTemplate
    {
        // fixed page url and needs to change to fit into your case
        private const string url = "file:///C:/Users/lei.wang/Desktop/Selenium/%E3%83%86%E3%82%B9%E3%83%88%E7%94%A8HTML/html/formSample02.html";

        // page loading wait object
        private IWait<IWebDriver> wait;

        // page constructor
        public SecondPage(IWebDriver driver)
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
    }
}