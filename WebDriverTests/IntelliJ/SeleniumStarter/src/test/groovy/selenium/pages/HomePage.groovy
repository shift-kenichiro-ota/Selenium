package selenium.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.How
import org.openqa.selenium.support.ui.Select
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
public class HomePage extends PageTemplate {

    // fixed page url and needs to change to fit into your case
    private static String url;
    static {
        FileSystem fileSystem = FileSystems.getDefault();
        if (isWindows()) {
            Path path = fileSystem.getPath("..¥¥..¥¥..¥¥testHTML¥¥index.html").toRealPath();
            url = "file:///" + path.toString().replace("¥¥", "/");
        } else {
            Path path = fileSystem.getPath("../../../testHTML/index.html").toRealPath();
            url = "file://" + path;
        }
    }

    // page loading wait object
    private Wait<WebDriver> wait;

    // page constructor
    public HomePage(WebDriver driver) {
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

    // necessary data structure
    public enum Reasons {
        BOOKSTORE,
        FRIEND
    }

    public enum Countries {
        JAPAN,
        ENGLAND,
        USA,
        CHINA
    }

    public enum Browsers {
        IE5,
        IE4,
        IE3,
        NN6,
        NN4,
        NN3,
        OTHER
    }

    // necessary private web elements
    @FindBy(how = How.XPATH, using = "//input[@type='text' and @name='PRICE01']")
    private WebElement textPrice1;
    @FindBy(how = How.XPATH, using = "//input[@type='text' and @name='PRICE02']")
    private WebElement textPrice2;
    @FindBy(how = How.XPATH, using = "//input[@type='text' and @name='RESULT']")
    private WebElement textResult;
    @FindBy(how = How.XPATH, using = "//input[@type='button' and @name='calcBtn']")
    private WebElement buttonCalculate;
    @FindBy(how = How.XPATH, using = "//input[@type='button' and @name='ALERT']")
    private WebElement buttonAlert;
    @FindBy(how = How.XPATH, using = "//input[@type='text' and @id='name1']")
    private WebElement textName;
    @FindBy(how = How.XPATH, using = "//textarea[@id='kansou1']")
    private WebElement textareaComment;
    @FindBy(how = How.XPATH, using = "//input[@type='radio' and @id='seibetsu2']")
    private WebElement radioFemale;
    @FindBy(how = How.XPATH, using = "//input[@type='radio' and @id='seibetsu1']")
    private WebElement radioMale;
    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @id='riyuu1']")
    private WebElement checkReason1;
    @FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @id='riyuu2']")
    private WebElement checkReason2;
    private Select selectCountry;
    private Select selectBrowser;

    // web element initialization, you can use either PageFactory or an initialization method
    public void initializeWebElement() {
        selectCountry = new Select(getElementById("kokumei1"));
        selectBrowser = new Select(getElementById("browser1"));
    }

    // actions
    public void enterName(String name) {
        textName.sendKeys(name);
    }

    public void fireNameAlert() {
        buttonAlert.click();
    }

    public void enterComment(String comment) {
        textareaComment.sendKeys(comment);
    }

    public void selectGender(boolean gender) {
        if (gender) {             // female is true
            radioFemale.click();
        } else {                  // male is false
            radioMale.click();
        }
    }

    public void selectReason(Reasons reason) {
        switch (reason) {
            case BOOKSTORE:
                checkReason1.click();
                break;

            case FRIEND:
                checkReason2.click();
                break;

            default:
                break;
        }
    }

    public void selectACountry(Countries country) {
        selectCountry.selectByValue(country.toString());
    }

    public void selectBrowsers(Browsers browser) {
        selectBrowser.selectByValue(browser.toString());
    }

    public void calculate(double price1, double price2) {
        textPrice1.sendKeys(String.valueOf(price1));
        textPrice2.sendKeys(String.valueOf(price2));
        buttonCalculate.click();
    }

    public SecondPage moveToNextPage() {
        buttonSubmit.click();
        return (SecondPage) new SecondPage(driver).get();
    }

    // retrieve information
    public String getCalculationResult() {
        return textResult.getAttribute("value");
    }

    public boolean getMaleRadioboxStatus() {
        return radioMale.isSelected();
    }

    public boolean getFemaleRadioboxStatus() {
        return radioFemale.isSelected();
    }

    public String getSelectedCountry() {
        return selectCountry.getFirstSelectedOption().getText();
    }
}
