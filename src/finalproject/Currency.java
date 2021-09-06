package finalproject;

public class Currency {
  int id;
  String inputCode;
  String outputCode;

  double USD = 0.0060;
  double AUD = 0.0081; // Australian Dollar
  double GBP = 0.0043; // British Pound
  double CAD = 0.0075; // Canadian Dollar
  double TRY = 0.050; // Turkish Lira
  double IDR = 85.23; // Indonesian Rupiya
  double AFN = 0.52; // Afghan Afghani
  double PKR = 1;

  Currency(int id, String inputCode, String outputCode) {
    this.id = id;
    this.inputCode = inputCode;
    this.outputCode = outputCode;
  }

  public double convertCurrency(double amount, String inputCode, String outputCode) {

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