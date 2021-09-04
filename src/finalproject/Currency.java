package finalproject;

public class Currency {
  int id;
  String code;
  String name;
  double exchangeRate;

  Currency(int id, String code, String name, double exchangeRate) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.exchangeRate = exchangeRate;
  }

  public double convertPkrToOthers(double amount, String code) {
    return amount * exchangeRate;
  }

  public double convertOthersToPkr(double amount, String code) {
    return amount * exchangeRate;
  }

}
