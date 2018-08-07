package TestPackage;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by ngoyal on 25/06/17.
 */
public class DriverFactory {

    public static WebDriver driver = null;


    public static WebDriver createInstance (String browser,String platform,boolean isRemoteExecution) throws MalformedURLException{
        DesiredCapabilities caps = new DesiredCapabilities();
        if(isRemoteExecution){
            if(platform.equalsIgnoreCase("mac")){
                caps.setPlatform(Platform.MAC);
            }
            if(browser.equalsIgnoreCase("chrome")){
                caps = DesiredCapabilities.chrome();
            }
            if(platform.equalsIgnoreCase("winoows")){
                caps.setPlatform(Platform.WINDOWS);
            }
            if(browser.equalsIgnoreCase("firefox")){
                caps = DesiredCapabilities.firefox();
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
            return driver;

        }else {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\chromedriver.exe");
                driver = new ChromeDriver();
                return driver;
            } else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver",  "C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                return driver;
            } else {
                System.setProperty("webdriver.gecko.driver",  "C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                return driver;
            }
        }
    }

}
