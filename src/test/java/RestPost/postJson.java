package RestPost;


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

import TestPackage.DriverFactory;
import TestPackage.JsonDataResponse;
import TestPackage.ThreadLocalDriver;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class postJson {
	@Test
	public void test()
			throws MalformedURLException, InterruptedException {
		Map<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("firstName", "Sumit");
		jsonAsMap.put("lastName", "Ghosh");
		given().contentType("application/json; charset=UTF-16").body(jsonAsMap).when().post("http://localhost:3000/posts");	
	}

}
