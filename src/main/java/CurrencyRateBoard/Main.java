package CurrencyRateBoard;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    static final String ADDRESS_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    static final String XML_FILE_NAME = "XML_daily.xml";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        GetXMLFile.getXMLFile(XML_FILE_NAME, ADDRESS_URL);

        Report report = XMLParser.parser(XML_FILE_NAME);

        System.out.println("\nReport date: " + report.dateXML);
        report.valutelist.forEach(System.out::println);
    }
}