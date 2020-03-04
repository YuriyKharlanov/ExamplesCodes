package VendorСodeForMaxPrice;

/*
В конструктор класса VendorCodeForMaxPrice приходит имя файла.
В этом файле каждая строка имеет следующий вид: артикул значение
где [артикул] - String, [значение] - double.
[артикул] и [значение] разделены пробелом.

Для каждого артикула посчитать сумму всех его значений.
Геттер getVendorCodeList должен отдавать List<String> содержащий артикулы в алфавитном порядке, у которых максимальная сумма.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class VendorCodeForMaxPrice {
    private static Double max = Double.MIN_VALUE;
    private static String fileName = "";

    public static List<String> getVendorCodeList() { //отдает лист
        return vendorCodeList;
    }

    private static List<String> vendorCodeList = new ArrayList<>();

    public VendorCodeForMaxPrice(String fileName) throws IOException {
        VendorCodeForMaxPrice.fileName = fileName;
        getList();
    }

    private void getList() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        TreeMap<String, Double> treeMap = new TreeMap<>();

        while (bufferedReader.ready()) {
            String stringLine;
            if ((stringLine = bufferedReader.readLine().trim()).matches("^\\w+\\s\\d+[.]\\d+$")) { //проверяем что строка соответствует формату, 1 пробел между артикулом и значением
                String[] stringArray = stringLine.split(" "); //разбираем строку
                treeMap.merge(stringArray[0], Double.parseDouble(stringArray[1]), (a, b) -> a + b); //кладем в TreeMap который сразу сортирует по алфавиту, если ключа нет, то создает, иначе суммирует и заменяет
            }
        }

        treeMap.forEach((s, aDouble) -> { //ищем максимальное значение
            if (aDouble > max) max = aDouble;
        });

        treeMap.forEach((s, aDouble) -> { //кладем в ЛИСТ все строки из treeMap у которых значение равно максимальному
            if (Double.compare(max, aDouble) == 0) {
                vendorCodeList.add(s); // кладет в лист
            }
        });

        bufferedReader.close();
    }

    public static void main(String[] args) throws IOException {
        VendorCodeForMaxPrice vendorCodeForMaxPrice = new VendorCodeForMaxPrice("./src/main/resources/test.txt");
        System.out.println(vendorCodeForMaxPrice.getVendorCodeList());
    }
}
