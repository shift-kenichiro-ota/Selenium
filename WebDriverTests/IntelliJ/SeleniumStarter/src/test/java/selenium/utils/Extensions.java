package selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created with IntelliJ IDEA.
 * User: lei.wang
 * Date: 13/11/03
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class Extensions {
    public static ExpectedCondition<Boolean> visibilityOfElement(
            final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return element.isDisplayed();
            }
        };
    }
}
