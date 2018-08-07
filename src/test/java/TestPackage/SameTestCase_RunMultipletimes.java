package TestPackage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SameTestCase_RunMultipletimes extends Base{

	@BeforeTest()
	public void setup() throws InterruptedException {
		//initDriver();
	}
	
	@Test(invocationCount = 10)
	public void myTest1() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.google.com/");
	System.out.println(driver.getTitle());
	}
	
	
	@AfterTest()
	public void quitTest() throws InterruptedException {
		driver.quit();
	}
	
}
