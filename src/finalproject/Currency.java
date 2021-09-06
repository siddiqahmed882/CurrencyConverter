package finalproject;

public class Currency {
  String outputCode;

  static double USD = 0.0060;
  static double AUD = 0.0081; // Australian Dollar
  static double GBP = 0.0043; // British Pound
  static double CAD = 0.0075; // Canadian Dollar
  static double TRY = 0.050; // Turkish Lira
  static double IDR = 85.23; // Indonesian Rupiya
  static double AFN = 0.52; // Afghan Afghani
  static double PKR = 1;

  public static double convertCurrency(double amount, String outputCode) {

    double convertedAmount;
    switch (outputCode) {
      case "USD":
        convertedAmount = amount * USD;
        break;

      case "AUD":
        convertedAmount = amount * AUD;
        break;

      case "GBP":
        convertedAmount = amount * GBP;
        break;

      case "CAD":
        convertedAmount = amount * CAD;
        break;

      case "TRY":
        convertedAmount = amount * TRY;
        break;

      case "IDR":
        convertedAmount = amount * IDR;
        break;

      case "AFN":
        convertedAmount = amount * AFN;
        break;

      case "PKR":
        convertedAmount = amount * PKR;
        break;

      default:
        convertedAmount = amount * 0.0;

    }
    return convertedAmount;

  }
}