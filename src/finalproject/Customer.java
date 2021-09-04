package finalproject;

import java.util.ArrayList;

public class Customer {
  int id;
  String AC;
  String userName;
  String password;
  String firstName;
  String lastName;
  double balance;
  String branchCode;
  boolean discountStatus;
  ArrayList<Order> orders;

  Customer(int id, String AC, String userName, String password, String firstName, String lastName, double balance,
      String branchCode, boolean discountStatus) {
    this.id = id;
    this.AC = AC;
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.balance = balance;
    this.branchCode = branchCode;
    this.discountStatus = discountStatus;
    this.orders = new ArrayList<>();
  }

  public int getId() {
    return this.id;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String changeDiscountStatus(boolean discountStatus) {
    this.discountStatus = discountStatus;
    return "Discount Status Updated....!!!";
  }

  public String changePassword(String password) {
    this.password = password;
    return "Password Updated...!!!";
  }

  public void addOrder(Order order) {
    this.orders.add(order);
  }

  public String toString() {
    return "ID: " + id + "\n" + "Account Number: " + AC + "\n" + "Name: " + firstName + " " + lastName + "\n"
        + "Balance: " + balance + "orders: " + this.orders.size();
  }
}
