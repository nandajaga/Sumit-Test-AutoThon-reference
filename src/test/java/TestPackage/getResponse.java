package TestPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;

public class getResponse{
	String responseFromESL;
	HashMap<String, List<String>> protocolSpeed = new HashMap<String, List<String>>();
	XPath xpath;
	Document doc = null;
	File responseFile;

	
	
	public void getResponseFromService(String endPoint, String myRequest) throws IOException {
		//RestAssured.useRelaxedHTTPSValidation();
		Response response = given().auth().basic("eslsvc", "W0rkgppw1").contentType("application/xml").body(myRequest)
				.when().get(endPoint).then().extract().response();
		responseFromESL = response.asString();
		BufferedWriter out = null;
		responseFile = new File("temp.xml");
		try {
			out = new BufferedWriter(new FileWriter(responseFile));
			out.write(responseFromESL);
		} catch (IOException e) {
			System.out.println("Exception ");
		} finally {
			out.close();
		}
	}

	public void settingNameSpaceForXMLRead()
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		//responseFile=new File("C:\\Users\\ab36194\\git\\SfaTests\\temp.xml");
		doc = builder.parse(responseFile);
		xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NamespaceContext() {
			String nameSpace = "http://www.centurylink.com/XMLSchema/ESL";

			public String getNamespaceURI(String prefix) {
				if (prefix.equals("edx")) {
					return nameSpace;
				}
				return "";
			}

			public String getPrefix(String namespaceURI) {
				if (namespaceURI.equals(nameSpace)) {
					return "edx";
				}
				return "";
			}

			@SuppressWarnings("rawtypes")
			public Iterator getPrefixes(String namespaceURI) {
				List<String> list = new ArrayList<String>();
				if (namespaceURI.equals(nameSpace)) {
					list.add("edx");
				}
				return list.iterator();
			}
		});
	}

	public HashMap<String, List<String>> speedValueOfProtocol(String productName)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		settingNameSpaceForXMLRead();
		HashMap<String, List<String>> protocolSpeed = new HashMap<String, List<String>>();
		List<String> listProtocol = new ArrayList<String>();
		String isServiceAvailableXapth = "//edx:ServiceName[text()='" + productName
				+ "']/following-sibling::edx:IsServiceAvailable";
		List<String> tempSpeeds = new ArrayList<String>();
		if ((elementValue(isServiceAvailableXapth).get(0)).equalsIgnoreCase("true")) {
			String accessProtocol = "//edx:ServiceName[text()='" + productName
					+ "']/following-sibling::edx:AdditionalInfoList/edx:AccessAvailabilityInfoList/edx:AccessAvailabilityInfo/edx:AvailabilityByProtocol/edx:AccessProtocol";
			listProtocol = elementValue(accessProtocol);
			for (String protocol : listProtocol) {
				String expression = "//edx:ServiceName[text()='" + productName
						+ "']/following-sibling::edx:AdditionalInfoList/edx:AccessAvailabilityInfoList/edx:AccessAvailabilityInfo/edx:AvailabilityByProtocol/"
						+ "edx:AccessProtocol[text()='" + protocol
						+ "']/following-sibling::edx:AccessSpeedList[1]/edx:AccessSpeed/edx:Speed";
				tempSpeeds = elementValue(expression);
//				System.out.println("Speed Details From ESL");
//				System.out.println(protocol);
//				System.out.println(tempSpeeds);
				protocolSpeed.put(protocol, tempSpeeds);
			}
		} else {
			protocolSpeed.put("No accessProtocol", tempSpeeds);
		}	
		return protocolSpeed;
	}

	public List<String> elementValue(String xpathExpression) throws XPathExpressionException {
		NodeList names = (NodeList) xpath.evaluate(xpathExpression, doc, XPathConstants.NODESET);
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < names.getLength(); i++) {
			Element firstElement = (Element) names.item(i);
			try {
				String rowString = firstElement.getTextContent();
				if (rowString.indexOf(".") == -1) {
					temp.add(rowString);
				} else {
					temp.add(rowString.substring(0, rowString.indexOf(".") + 2));
				}

			} catch (Exception e) {
				temp.add(firstElement.getTextContent());

			}
		}
		return temp;
	}

	public boolean isServiceAvailabeForHybridSDWAN(String productName, String accessProtocol)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, customException {
		settingNameSpaceForXMLRead();

		List<String> listAccessSpeeds = new ArrayList<String>();
		
		boolean flag = true;

		String accessSpeeds = "//edx:ServiceName[text()='" + productName
				+ "']/following-sibling::edx:AdditionalInfoList/edx:AccessAvailabilityInfoList/"
				+ "edx:AccessAvailabilityInfo/edx:AvailabilityByProtocol/edx:AccessProtocol[text()='" + accessProtocol
				+ "']/following-sibling::edx:AccessSpeedList/edx:AccessSpeed/edx:Speed";
		listAccessSpeeds = elementValue(accessSpeeds);
		
		//**********************************Validation with out CABLE protocol*****************************************
		if (listAccessSpeeds.isEmpty())
			flag=false;		
		
		for (String speed : listAccessSpeeds) {
			List<String> vendorType = new ArrayList<String>();
			
			//**********************************CHARTER Validation*****************************************
			try {
				String expression1 = "//edx:ServiceName[text()='" + productName
						+ "']/following-sibling::edx:AdditionalInfoList/edx:AccessAvailabilityInfoList/"
						+ "edx:AccessAvailabilityInfo/edx:AvailabilityByProtocol/edx:AccessProtocol[text()='"
						+ accessProtocol
						+ "']/following-sibling::edx:AccessSpeedList/edx:AccessSpeed/edx:Speed[text()='" + speed + "']/"
						+ "following-sibling::edx:AccessVendorList/edx:AccessVendor/edx:VendorName[text()='Charter']/following-sibling::edx:VendorType";
				vendorType.add(elementValue(expression1).get(0));
				System.out.println("VendorType " + elementValue(expression1).get(0));
			} catch (Exception e) {

				try {
					String expression1 = "//edx:ServiceName[text()='" + productName
							+ "']/following-sibling::edx:AdditionalInfoList"
							+ "/edx:AccessAvailabilityInfoList/edx:AccessAvailabilityInfo/edx:AvailabilityByProtocol/edx:AvailabilityExceptionList/edx:ESLException"
							+ "/edx:ErrorSource[text()='CHARTER']/following-sibling::edx:Code";
					vendorType.add(elementValue(expression1).get(0));
				} catch (Exception e1) {
					throw new customException("Relevent tag is missing in response xml");
				}
			}
			
			
			//**********************************COMCAST Validation*****************************************
			try {
				String expression2 = "//edx:AccessProtocol[text()='" + accessProtocol
						+ "']/following-sibling::edx:AccessSpeedList/edx:AccessSpeed/edx:Speed[text()='" + speed + "']/"
						+ "following-sibling::edx:AccessVendorList/edx:AccessVendor/edx:VendorName[text()='Comcast']/following-sibling::edx:VendorType";
				vendorType.add(elementValue(expression2).get(0));
				System.out.println("VendorType " + elementValue(expression2).get(0));
			} catch (Exception e) {

				try {
					String expression2 = "//edx:ServiceName[text()='" + productName
							+ "']/following-sibling::edx:AdditionalInfoList"
							+ "/edx:AccessAvailabilityInfoList/edx:AccessAvailabilityInfo/edx:AvailabilityByProtocol/edx:AvailabilityExceptionList/edx:ESLException"
							+ "/edx:ErrorSource[text()='COMCAST']/following-sibling::edx:Code";
					vendorType.add(elementValue(expression2).get(0));
				} catch (Exception e1) {
					throw new customException("Relevent tag is missing in response xml");
				}

			}
					
			if (vendorType.get(0)=="5000" && vendorType.get(1)=="5000") {
				flag = false;
			}

		}
		String isServiceAvailableXapth = "//edx:ServiceName[text()='" + productName
				+ "']/following-sibling::edx:IsServiceAvailable";
		if (Boolean.parseBoolean(elementValue(isServiceAvailableXapth).get(0))==flag)
			return true;
		else
			return false; 
	}

	public boolean verifyHighCostThresholdValue(String highThresholdPrice, String fiberMiles)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, customException {
		settingNameSpaceForXMLRead();
		if ((elementValue("//edx:GeoWSAddressInfo/edx:FiberMiles").get(0).equalsIgnoreCase(fiberMiles))
				&& (highThresholdPrice.contains(elementValue("//edx:EFMInfo/edx:HighCostThreshold").get(0))))
			return true;
		else
			return false;
	}

	public boolean verifyTermAndPrices(Map<String, String> termPrices) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		settingNameSpaceForXMLRead();
		for (String key : termPrices.keySet()) {	
			if(!termPrices.get(key).contains(elementValue("(//edx:TermThresholdList/edx:TermThreshold/edx:Term[text()='"+ key +"']/following::edx:TermMRC)[1]").get(0)))
			return false;
		}
		return true;
	}
}

@SuppressWarnings("serial")
class customException extends Exception
{
  public customException(String message)
  {
    super(message);
  }
}
