package TestPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	//WebDriver driver;
	public WebDriver driver =null;
	   public WebDriver initDriver() {

	    if (driver == null) {
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }
	    return driver;
	   }
}
