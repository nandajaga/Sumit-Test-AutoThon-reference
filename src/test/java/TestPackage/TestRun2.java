package TestPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
//import static org.hamcrest.MatcherAssert.assertThat;
import java.net.MalformedURLException;
import java.net.URL;

public class TestRun2  extends Base{

	//@BeforeTest()
	public void setup() throws InterruptedException, MalformedURLException {
        DesiredCapabilities capabilities=DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");       
        capabilities.setCapability(MobileCapabilityType.VERSION,"8.1");
        capabilities.setCapability("app", "C:/App/CTLConsumer-3.10.30.apk");
       // capabilities.setCapability("appPackage", "com.android.chrome");
        URL url = new URL ("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(url, capabilities);
		}
	
	@Test()
	public void myTest1() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.msn.com/");
	System.out.println(driver.getTitle());
	}
	
	
	
	
	
	
	
	
	//@Test()
	public void androidTest() throws MalformedURLException, InterruptedException {
		driver.findElement(By.xpath("//*[@resource-id='android:id/button2']")).click();
        Thread.sleep(5000);
        System.out.println("Test");

	}
		
	@AfterTest()
	public void quitTest() throws InterruptedException {
		driver.quit();
	}
	
}
