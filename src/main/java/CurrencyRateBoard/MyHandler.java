package CurrencyRateBoard;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

class MyHandler extends DefaultHandler {
    private static final String VALCURS_TAG = "ValCurs"; //ПЕРЕМЕННЫЕ
    private static final String VALUTE_TAG = "Valute";
    private static final String NUMCODE_TAG = "NumCode";
    private static final String CHARCODE_TAG = "CharCode";
    private static final String NOMINAL_TAG = "Nominal";
    private static final String NAME_TAG = "Name";
    private static final String VALUE_TAG = "Value";

    private static final String VALCURS_DATE_ATTRIBUTE = "Date";
    private static final String VALUTE_ID_ATTRIBUTE = "ID";

    private Report report; // ССЫЛОЧНЫЕ ПЕРЕМЕННЫЕ
    private Valute currentValute;
    private String currentElement;

    Report getReport() {
        return report;
    }

    public void startDocument() {
        System.out.println("Starting XML parsing...");
    }

    @Override
    public void startElement(String URL, String localName, String qName, Attributes attributes) {
        currentElement = qName;

        switch (currentElement) {
            case VALCURS_TAG: {
                report = new Report();
                report.dateXML = attributes.getValue(VALCURS_DATE_ATTRIBUTE);
                report.valutelist = new ArrayList<>();
            }
            break;

            case VALUTE_TAG: {
                currentValute = new Valute();
                currentValute.ID = attributes.getValue(VALUTE_ID_ATTRIBUTE);
                currentValute.date = report.dateXML;
            }
            break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length);

        if (text.contains("<") || currentElement == null) { //если текущий элемент не проинициализирован то выходим
            return;
        }

        switch (currentElement) {
            case NUMCODE_TAG: {
                currentValute.numCode = text;
            }
            break;

            case CHARCODE_TAG: {
                currentValute.charCode = text;
            }
            break;

            case NOMINAL_TAG: {
                currentValute.nominal = text;
            }
            break;

            case NAME_TAG: {
                currentValute.name = text;
            }
            break;

            case VALUE_TAG: {
                currentValute.value = text;
            }
            break;

            default: {
            }
        }
    }

    @Override
    public void endElement(String url, String localName, String qName) {
        switch (qName) {
            case VALUTE_TAG: {
                report.valutelist.add(currentValute);
                currentValute = null;
            }
            break;

            default: {
            }
        }

        currentElement = null;
    }

    public void endDocument() {
        System.out.println("XML parsing is completed.");
    }
}
