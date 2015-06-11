package dominio;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	
	Element settings;
	Element months;
	Element days;
	Element defaultSettings;
	Vector<String> headerColumns;
	
	protected XMLParser() {
		try {
			File fXmlFile = new File("src/settings/settings-it.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc =  dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			settings=(Element)doc.getElementsByTagName("settings-timesheet").item(0);
			months=(Element)settings.getElementsByTagName("months").item(0);
			days=(Element)settings.getElementsByTagName("days").item(0);
			defaultSettings=(Element)settings.getElementsByTagName("default-settings").item(0);
			headerColumns=initiateHeaderColumns();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Vector<String> initiateHeaderColumns() {
		Vector<String> output=new Vector<String>();
		NodeList headerList=settings.getElementsByTagName("column-headers").item(0).getChildNodes();
		for (int i = 1; i < headerList.getLength(); i=i+2) {
			Node node=headerList.item(i);
			output.add(node.getTextContent());
		}
		return output;
	}
	
	protected Vector<String> getHeader() {
		return headerColumns;
	}
	
	protected String getNameMonth(int i) {
		Element month=(Element)months.getElementsByTagName("month-"+i).item(0);
		return month.getTextContent();
	}
	
	protected String getNameDay(int i) {
		Element day=(Element)days.getElementsByTagName("day-"+i).item(0);
		return day.getTextContent();
	}
	
	protected String getCurrProj() {
		Element currProj=(Element)defaultSettings.getElementsByTagName("curr-proj-title").item(0);
		return currProj.getTextContent();
	}
	
	protected String getCurrPlace() {
		Element currProj=(Element)defaultSettings.getElementsByTagName("curr-place").item(0);
		return currProj.getTextContent();
	}
	
	protected String getWeReasonOfAbsence() {
		Element currProj=(Element)defaultSettings.getElementsByTagName("reason-of-absence-weekend").item(0);
		return currProj.getTextContent();
	}
}
