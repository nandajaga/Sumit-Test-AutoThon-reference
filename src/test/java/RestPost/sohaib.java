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

public class sohaib {
		
	String[] objData= new String[] { "1","2",  "3",  "4",  "5",
		 "6",  "7", "8","9","10"};

	@BeforeClass
	public void autoThan() throws FileNotFoundException, IOException, ParseException {
	}

	@Test	
	public void Testing1() throws MalformedURLException, InterruptedException {
		int n=0;
		while(n<2) {
		Long id1 = Thread.currentThread().getId();
		System.out.println("Thread " + id1);
		System.out.println("Random Data " + objData[generateRandomNumberWithRange(0,9)]);
		System.out.println("###############");
		n++;
		}
	}

	@Test	
	public void Testing2() throws MalformedURLException, InterruptedException {
		int n=0;
		while(n<2) {
		Long id1 = Thread.currentThread().getId();
		System.out.println("Thread " + id1);
		System.out.println("Random Data " + objData[generateRandomNumberWithRange(0,9)]);
		System.out.println("###############");
		n++;
		}
		
	}
	
	@Test
	public void Testing3() throws MalformedURLException, InterruptedException {
		int n=0;
		while(n<2) {
		Long id1 = Thread.currentThread().getId();
		System.out.println("Thread " + id1);
		System.out.println("Random Data " + objData[generateRandomNumberWithRange(0,9)]);
		System.out.println("###############");
		n++;
		}
	}
	
	@Test
	public void Testing4() throws MalformedURLException, InterruptedException {
		int n=0;
		while(n<2) {
		Long id1 = Thread.currentThread().getId();
		System.out.println("Thread " + id1);
		System.out.println("Random Data " + objData[generateRandomNumberWithRange(0,9)]);
		System.out.println("###############");
		n++;
		}
	}
	
	@Test
	public void Testing5() throws MalformedURLException, InterruptedException {
		int n=0;
		while(n<2) {
		Long id1 = Thread.currentThread().getId();
		System.out.println("Thread " + id1);
		System.out.println("Random Data " + objData[generateRandomNumberWithRange(0,9)]);
		System.out.println("###############");
		n++;
		}
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
