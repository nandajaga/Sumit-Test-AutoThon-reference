package TestPackageDetail;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class OneInstance {
	WebDriver driver;
	
	@BeforeTest()
	public void startUp() {
		AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingPort(4723).usingDriverExecutable(new File("C://Program Files (x86)//Appium//node.exe"))
				.withAppiumJS(new File("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js")));
		appiumService.start();

		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
		capabilities.setCapability("udid", "emulator-5554");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "MyDevice");
		capabilities.setCapability(MobileCapabilityType.VERSION, "8.1.0");
		{
			URL url = null;
			try {
				url = new URL("http://0.0.0.0:4723/wd/hub"); // 16265 4723
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new AndroidDriver<MobileElement>(url, capabilities);
		}
	}
	
	@Test
	public void runningTest() {
		driver.get("https://www.google.com/shopping");
		driver.findElement(By.xpath("//*[@id=\"lst-ib\"]")).sendKeys("Blue Cap");
		driver.findElement(By.xpath("//*[@id=\"tsbb\"]/div")).click();
		driver.findElement(By.xpath("//*[@id=\"rso\"]/div[2]/div[1]/div[2]/a[1]/div/div[1]")).click();
	}

}
