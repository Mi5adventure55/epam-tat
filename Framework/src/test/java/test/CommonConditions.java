package test;


import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.TestListener;

import java.io.IOException;

@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

//    @AfterMethod(alwaysRun = true)
//    public void browserClose() {
//        driver.close();
//    }

    @AfterTest(alwaysRun = true)
    public void stopBrowser() {
        driver = DriverSingleton.closeDriver();
    }
}
