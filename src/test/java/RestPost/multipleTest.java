package RestPost;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class multipleTest {
	public WebDriver driver = null;
	@DataProvider(name = "DataJason", parallel = true)
	public Object[][] credentials(Method m) {
		if (m.getName().equals("Testing1")) {
			return new Object[][] { { "1", "India" }, { "2", "UK" }, { "3", "India" }, { "4", "UK" }, { "5", "India" },
					{ "6", "UK" }, { "7", "India" }, { "8", "UK" } };
		} else {
			return new Object[][] { { "9", "India" }, { "10", "UK" } };
		}

	}

	@BeforeClass
	public void autoThan() throws FileNotFoundException, IOException, ParseException {
	}

	@Test(dataProvider = "DataJason")	
	public synchronized void Testing1(String id, String rowData) throws MalformedURLException, InterruptedException {
		Long id1 = Thread.currentThread().getId();
		System.out.println("Thread " + id1);
		System.out.println("zz " + id);
	}


	@Test(dataProvider = "DataJason")
	public synchronized void Testing2(String id, String rowData) throws MalformedURLException, InterruptedException {
		Long id1 = Thread.currentThread().getId();
		System.out.println("Thread " + id1);
		System.out.println("ss " + id);
	}
}
