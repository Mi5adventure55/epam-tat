package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage
{
    private final String BASE_URL = "https://5element.by/";
    private final Logger logger = LogManager.getRootLogger();

    private By searchBarInputLocator = By.xpath("//input[@id='q']");
    private By searchProduts = By.xpath("//div[@class='multi-content']//span");

    @FindBy (xpath = "//li[@class='top-bar-right-log _in']")
    private WebElement logInButton;

    @FindBy (xpath = "//li[@class='top-bar-right-log ']/a/span")
    private WebElement greetingText;

    @FindBy (xpath = "//div[@class='searchbox js-search-main']")
    private WebElement searchBarInputButton;

    @FindBy (xpath = "//div[@class='multi-content']//span")
    private WebElement searchFirstProduct;

    public MainPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    public MainPage openPage()
    {
        driver.get(BASE_URL);
        logger.info("Opened page " + BASE_URL);
        return this; }

    public AuthorizationPage openLogInDialog() {
        logInButton.click();
        return new AuthorizationPage(driver); }

    public MainPage acceptAlert(){
        this.acceptAnyAlert();
        return this; }

    public MainPage searchForQuery(String query) {
        searchBarInputButton.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.
                        presenceOfElementLocated(searchBarInputLocator)).sendKeys(query);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.
                        elementToBeClickable(searchProduts));
        logger.info("Searching for " + query);
        return this; }

    public String getFirstItemName() {
        return searchFirstProduct.getText();
    }

}
