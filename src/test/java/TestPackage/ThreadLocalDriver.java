package TestPackage;

import org.openqa.selenium.WebDriver;

/**
 * Created by ngoyal on 25/06/17.
 */
public class ThreadLocalDriver {

    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getThreadLocalDriver(){
        return threadLocalDriver.get();
    }

    public static void setThreadLocalDriver(WebDriver driver){
        threadLocalDriver.set(driver);
    }

}