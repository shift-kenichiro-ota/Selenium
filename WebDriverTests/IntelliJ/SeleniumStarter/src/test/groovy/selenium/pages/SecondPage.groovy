package selenium.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.How
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.WebDriverWait
import selenium.utils.Extensions

import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Path

/**
 * Created with IntelliJ IDEA.
 * User: lei.wang
 * Date: 13/11/03
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class SecondPage extends PageTemplate {

    // fixed page url and needs to change to fit into your case
    private static String url;

    static {
        FileSystem fileSystem = FileSystems.getDefault();
        if (isWindows()) {
            Path path = fileSystem.getPath("..¥¥..¥¥..¥¥testHTML¥¥html¥¥formSample02.html").toRealPath();
            url = "file:///" + path.toString().replace("¥¥", "/");
        } else {
            Path path = fileSystem.getPath("../../../testHTML/html/formSample02.html").toRealPath();
            url = "file://" + path;
        }
    }

    // page loading wait object
    private Wait<WebDriver> wait;

    // page constructor
    public SecondPage(WebDriver driver) {
        super(driver, url);
    }

    // symbolic element to identify when loading this page
    @FindBy(how = How.XPATH, using = "//input[@type='submit' and @name='submit']")
    private WebElement buttonSubmit;

    // initialize and load the page until we can see the above symbolic element
    @Override
    public void init() {
        wait = new WebDriverWait(driver, 20);
        wait.until(Extensions.visibilityOfElement(buttonSubmit));
    }
}
