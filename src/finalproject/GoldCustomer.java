package finalproject;

public class GoldCustomer extends Customer {
  static double discount = 0.1;

  GoldCustomer(int id, String AC, String userName, String password, String firstName, String lastName, double balance,
      String branchCode) {
    super(id, AC, userName, password, firstName, lastName, balance, branchCode);
  }

  public double getDiscount() {
    return GoldCustomer.discount;
  }
}
