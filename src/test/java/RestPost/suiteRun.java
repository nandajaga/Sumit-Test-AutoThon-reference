package RestPost;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class suiteRun {

	public static void main(String arg[]) {
		TestNG testNG = new TestNG();
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("Test");
	    xmlSuite.setParallel(XmlSuite.ParallelMode.TESTS);
	    xmlSuite.setPreserveOrder(false);
	    xmlSuite.setThreadCount(5);
	    xmlSuite.setVerbose(1);
		XmlTest test = new XmlTest(xmlSuite);
		test.setName("TmpTest");
	    List<XmlClass> classes = new ArrayList<XmlClass>();
	    classes.add(new XmlClass("RestPost.multipleTest"));
	    test.setXmlClasses(classes) ;
	    List<XmlSuite> suiteList = new ArrayList<>();
        suiteList.add(xmlSuite);
        testNG.run();
	}

}
