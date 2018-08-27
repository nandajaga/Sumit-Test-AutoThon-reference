package RestPost;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Random;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import TestPackage.DriverFactory;
import TestPackage.ThreadLocalDriver;

public class parallelData {
		
	String[] objData= new String[] { "1","2",  "3",  "4",  "5",
		 "6",  "7", "8","9","10"};

	@BeforeClass
	public void autoThan() throws FileNotFoundException, IOException, ParseException {
	}

	@Test	
	public void Testing1() throws MalformedURLException, InterruptedException {
		ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	}

		
	public int generateRandomNumberWithRange(int minimum, int maximum){
		Random rn = new Random();
		int range = maximum - minimum + 1;
		int number =  rn.nextInt(range) + minimum;
		//String tVal = number + "";
		return number;
	} 
	
	public int read(int minimum, int maximum){
		
		Random rn = new Random();
		int range = maximum - minimum + 1;
		int number =  rn.nextInt(range) + minimum;
		//String tVal = number + "";
		return number;
	} 
	
	
	
}
