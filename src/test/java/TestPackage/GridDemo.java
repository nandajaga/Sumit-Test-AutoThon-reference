package TestPackage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ngoyal on 25/06/17.
 */
public class GridDemo {

    WebDriver driver = null;


    @BeforeTest
    public void setup(){

    }

    @Test
    @Parameters({"browser","platform","isRemoteExecution"})
    public void openGoogle(String browser,String platform,boolean isRemoteExecution) throws MalformedURLException, InterruptedException{

        driver = DriverFactory.createInstance(browser,platform,isRemoteExecution);
        ThreadLocalDriver.setThreadLocalDriver(driver);
        driver = ThreadLocalDriver.getThreadLocalDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.print("Browser has been openend");
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}