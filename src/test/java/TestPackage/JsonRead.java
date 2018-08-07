package TestPackage;

import static com.jayway.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;

public class JsonRead {
	static HashMap<String, JsonDataResponse> jsonData = new HashMap<String, JsonDataResponse>();
	static Object[][] obj;
	public WebDriver driver = null;
	 

	@SuppressWarnings("rawtypes")
	@DataProvider(name = "DataJason", parallel = true)
	public Object[][] credentials() {
		Iterator it = jsonData.entrySet().iterator();
		int i = 0;
		obj = new Object[jsonData.keySet().size()][2];
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			obj[i][0] = pair.getKey().toString();
			obj[i][1] = pair.getValue();
			i++;
		}
		return obj;

	}

	@BeforeClass
	public void autoThan() throws FileNotFoundException, IOException, ParseException {
		JsonDataResponse rowData = new JsonDataResponse();
		int n1 = 0, n2 = 1, n3, i, count = 100;
		for (i = 2; i < count; ++i) {
			n3 = n1 + n2;
			if (n3 > 100) {
				break;
			}
			Response response = given().when().get("https://jsonplaceholder.typicode.com/posts/?id=" + n3).then()
					.assertThat().statusCode(200).and().extract().response();

			rowData = readMethod(response);
			jsonData.put(rowData.getUserID(), rowData);
			n1 = n2;
			n2 = n3;
		}

	}

	public JsonDataResponse readMethod(Response response) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JsonDataResponse data = new JsonDataResponse();

		Object obj = parser.parse(response.asString());
		JSONArray array = (JSONArray) obj;
		JSONObject jsonObject = (JSONObject) array.get(0);
		String userId = jsonObject.get("userId").toString();
		data.setUserId(userId);
		String id = jsonObject.get("id").toString();
		data.setID(id);
		String job = (String) jsonObject.get("title");
		data.setTitle(job);
		String body = (String) jsonObject.get("body");
		data.setBody(body);
		// System.out.println("*************************");
		return (data);

	}

	@Test(dataProvider = "DataJason")
	@Parameters({ "browser", "platform", "isRemoteExecution" })
	public void test(String id, JsonDataResponse rowData)
			throws MalformedURLException, InterruptedException {
		Long id1 = Thread.currentThread().getId();
		System.out.println("Thread " + id1);
		System.out.println(rowData.getUserID());
		Thread.sleep(100);
		driver = DriverFactory.createInstance("chrome", "winoows", false);
		ThreadLocalDriver.setThreadLocalDriver(driver);
		driver = ThreadLocalDriver.getThreadLocalDriver();
		driver.manage().window().maximize();
		driver.get("https://jsonplaceholder.typicode.com/posts/?id="+rowData.getUserID());
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
}
