package selenium.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.LoadableComponent

/**
 * Created with IntelliJ IDEA.
 * User: lei.wang
 * Date: 13/11/03
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public abstract class PageTemplate extends
        LoadableComponent<PageTemplate> {

    protected String pageUrl;
    protected WebDriver driver;

    public PageTemplate(WebDriver driver, String url) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.pageUrl = url;
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        assert url == pageUrl, "Error: the required page is not loaded."
    }

    @Override
    protected void load() {
        driver.get(pageUrl);
        init();
    }

    abstract void init();

    public boolean isTextPresent(String text) {
        return driver.findElements(By.xpath("//*[contains(.,'" + text + "')]"))
                .size() > 0;
    }

    public List<WebElement> getInputElementsByType(String type) {
        return driver.findElements(By.xpath("//input[@type='" + type + "']"));
    }

    public WebElement getElementById(String id){
        return driver.findElement(By.xpath("//*[@id='" + id + "']"));
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").contains("Windows");
    }
}
