using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium.Support.UI;
using OpenQA.Selenium.Support.PageObjects;
using OpenQA.Selenium;

namespace Shift.Selenium.Pages
{
    public abstract class PageTemplate 
    {
        protected string pageUrl;
        protected IWebDriver driver;

        public PageTemplate(IWebDriver driver, string url)
        {
            this.driver = driver;
            PageFactory.InitElements(driver, this);
            this.pageUrl = url;
            driver.Navigate().GoToUrl(pageUrl);
            Init();
        }

        public abstract void Init();

        public bool IsTextPresent(string text)
        {
            return driver.FindElements(By.XPath("//*[contains(.,'" + text + "')]"))
                   .Count() > 0;
        }

        public ICollection<IWebElement> GetInputElementsByType(string type)
        {
            return driver.FindElements(By.XPath("//input[@type='" + type + "']"));
        }

        public IWebElement GetElementById(String id)
        {
            return driver.FindElement(By.XPath("//*[@id='" + id + "']"));
        }
    }
}
