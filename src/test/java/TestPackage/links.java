package TestPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class links {

	public static void main(String[] args) {
		try {
			//System.setProperty("webdriver.gecko.driver",
			//		"C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\geckodriver.exe");
			//WebDriver driver = new FirefoxDriver();
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.centurylink.com");
			Thread.sleep(5000);

			final Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(5000))
					.takeScreenshot(driver);

			final BufferedImage image = screenshot.getImage();
			ImageIO.write(image, "PNG", new File("c:\\tmp1\\Home.png"));
			List<WebElement> no = driver.findElements(By.tagName("a"));

			Map<String, String> urls = new HashMap<String, String>();

			for (WebElement pagelink : no) {
				String linktext = pagelink.getText();
				String link = pagelink.getAttribute("href");
				if ((!pagelink.getText().isEmpty()) && (!pagelink.getAttribute("href").contains("#"))
						&& (!pagelink.getAttribute("href").equalsIgnoreCase("https://www.centurylink.com/"))) {
					System.out.println("Name: "+linktext+" : "+link);
					urls.put(linktext, link);
				}
			}

			for (String link : urls.keySet()) {
				try {
					driver.get(urls.get(link));
					Screenshot screenshot1 = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(5000))
							.takeScreenshot(driver);
					BufferedImage image1 = screenshot1.getImage();
					ImageIO.write(image1, "PNG", new File("c:\\tmp1\\" + link + ".png"));
				} catch (Exception e) {
					System.out.println("issue with url" + link + " ->" + urls.get(link));
				}
			}

		} 
		catch (Exception e) {
			System.out.println("error " + e);
		}

	}
}
