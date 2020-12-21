package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends AbstractPage {
    private final int WAIT_TIMEOUT_SECOND = 50;

    private final static String XPATH_FOR_ADD_TO_COMPARE_BUTTON = "//div[@class='btn-product-actions']/div[1]";
    private final static String XPATH_FOR_VALUE_INDICATOR = "//span[@id='compare-count-extra']";
    private final static String XPATH_FOR_LINK_TO_COMPARE_PAGE = "//div[@class='panel-fr-bottom']/a[1]";

    private By searchOpenBucketPopup = By.xpath("//div[@class='white-popup precart-popup']//a[@class='button js-modal__footer-cell js-goto-cart']");
    private By searchAddToBucketButton = By.xpath("//button[@class='spec-product-right-button button js-to-cart']");

    private boolean isPress = false;
    private String itemPageURL;

    public ProductPage(WebDriver driver, String url) {
        super(driver);
        itemPageURL = url;
    }

    @FindBy(xpath = "//section[@id='description']")
    private WebElement productDescriptionFind;

//    @FindBy(xpath = XPATH_FOR_ADD_TO_COMPARE_BUTTON)
//    private WebElement compareButtonFind;
//
//    @FindBy(xpath = XPATH_FOR_VALUE_INDICATOR)
//    private WebElement valueIndicatoOfCompareProduct;
//
//    @FindBy(xpath = XPATH_FOR_LINK_TO_COMPARE_PAGE)
//    private WebElement openComparePageBytton;

    @FindBy(xpath = "//button[@class='spec-product-right-button button js-to-cart']")
    private WebElement addToBucket;
//    @FindBy(xpath = "//div[@class='js-modal__footer-summary js-summary']")
//    private WebElement a;


    @Override
    public ProductPage openPage() {
        int t=0;
        driver.get(itemPageURL);
        return this;
    }

    private void cliclToDescription(){
        productDescriptionFind.click();
    }

    public String getUseualButtonPosition(){
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECOND)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='_action-pic to-compare']")))
                .getCssValue("background-position"); }

    public String getDataId(){
        this.cliclToDescription();
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECOND)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_ADD_TO_COMPARE_BUTTON)))
                .getAttribute("data-id"); }

    public ProductPage pressCompareButtonForAdd(){
        if(isPress) {
            (new WebDriverWait(driver, 100))
                    .until(ExpectedConditions
                            .attributeContains(By.xpath(XPATH_FOR_VALUE_INDICATOR), "class", "compare-count-active")); }
        this.cliclToDescription();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECOND)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_ADD_TO_COMPARE_BUTTON)))
                .click();
        isPress = !isPress;
        return this; }


//    public String getIndicatorValueById(){
//        boolean r;
//        r = (new WebDriverWait(driver, 100))
//                .until(ExpectedConditions
//                        .not(ExpectedConditions
//                                .presenceOfAllElementsLocatedBy(By.xpath("//span[@class='compare-count-active']"))));
//        return (new WebDriverWait(driver, WAIT_TIMEOUT_SECOND).until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_VALUE_INDICATOR))).getText()); }

//    public String getIndicatorValueByActiveClass(){
//        boolean isAttributeContains = false;
//        while(!isAttributeContains){
//            isAttributeContains = (new WebDriverWait(driver, 100))
//                    .until(ExpectedConditions.attributeContains(By.xpath(XPATH_FOR_VALUE_INDICATOR),"class","compare-count-active")); }
//        return (new WebDriverWait(driver, WAIT_TIMEOUT_SECOND).until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_VALUE_INDICATOR))).getText()); }

    public ComparePage openComparePage(WebDriver driver){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECOND)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_VALUE_INDICATOR)))
                .click();
        driver.get(new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_LINK_TO_COMPARE_PAGE)))
                .getAttribute("href"));
        return new ComparePage(driver); }

    public ProductPage returnDriverToTheProductPage(){
        driver.get(itemPageURL);
        return this; }

    public ProductPage checkInterferingNotifications(){
        deleteNotification();
        return this; }


    public ProductPage acceptAlert(){
        this.acceptAnyAlert();
        return this; }


    public ProductPage addItemToBucket(){
        addToBucket.click();
        return this;
    }
}