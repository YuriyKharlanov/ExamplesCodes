package CurrencyRateBoard;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class XMLParser {

    public static Report parser(String XMLFileName) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance(); //Исапользуем фабрику SAX
        SAXParser saxParser = spf.newSAXParser(); // Достаем конкретный экземпляр
        XMLReader xmlReader = saxParser.getXMLReader(); // Чтобы начать парсинг подключаем ридер
        MyHandler myHandler = new MyHandler(); // Создаем новый экземпляр класса хандлер который расширяет встроенный стандартный хандлер
        xmlReader.setContentHandler(myHandler);
        xmlReader.parse(XMLFileName); // Передаем ридеру ссылку на XML документ

        Report report = myHandler.getReport(); // Внутри хандлера будет сформирован объект report
        return report;
    }
}
