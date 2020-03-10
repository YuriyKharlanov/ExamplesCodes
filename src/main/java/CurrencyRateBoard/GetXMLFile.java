package CurrencyRateBoard;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

class GetXMLFile {
    static void getXMLFile(String xmlFileName, String addressUrl) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(addressUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(xmlFileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            fileOutputStream.flush();

        } catch (IOException e) {
        }
    }

    /*static void XMLInspection() { //TODO сделать проверку что файл существует/обновился, он корректен и соответствует схеме
     File file = new File(XML_FILE_NAME);
        if (file.exists() && file.isFile()) {
        }
    }*/
}
