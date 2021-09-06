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
  static double discount = 0;
  ArrayList<Order> orders;

  Customer(int id, String AC, String userName, String password, String firstName, String lastName, double balance,
      String branchCode) {
    this.id = id;
    this.AC = AC;
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.balance = balance;
    this.branchCode = branchCode;
    this.orders = new ArrayList<>();
  }

  public int getId() {
    return this.id;
  }

  public ArrayList<Order> getOrders() {
    return this.orders;
  }

  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public double getBalance() {
    return balance;
  }

  public double getDiscount() {
    return Customer.discount;
  }

  public void setBalance(double balance) {
    this.balance = balance;
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
        + "Balance: " + balance + "\n" + "orders: " + this.orders.size();
  }

  public Object getFullName() {
    return this.firstName + " " + this.lastName;
  }
}