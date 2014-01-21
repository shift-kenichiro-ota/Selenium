using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using OpenQA.Selenium.Support.UI;
using OpenQA.Selenium;

namespace Shift.Selenium.Helpers
{
    public class Extensions
    {
        public static Func<IWebDriver, bool> ElementIsVisible(IWebElement element)
        {
            return driver =>
     {
         return (element != null && element.Displayed);
     };
        }

        public static Func<IWebDriver, IAlert> AlertIsPresent()
        {
            return (driver) =>
            {
                try
                {
                    return driver.SwitchTo().Alert();
                }
                catch (NoAlertPresentException)
                {
                    return null;
                }
            };
        }
    }
}

