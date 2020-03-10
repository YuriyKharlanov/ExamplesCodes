package CurrencyRateBoard;

class Valute {
    String ID;
    String date;
    String numCode;
    String charCode;
    String nominal;
    String name;
    String value;

    @Override
    public String toString() {
        return "Valute{" +
                "ID='" + ID + '\'' +
                ", date='" + date + '\'' +
                ", numCode='" + numCode + '\'' +
                ", charCode='" + charCode + '\'' +
                ", nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}