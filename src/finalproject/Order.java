package finalproject;

import java.util.Date;

public class Order {
  int id;
  Date date;
  double discount;
  double grossAmount;
  double totalCost;
  boolean complete;

  Order(int id, double discount, double grossAmount) {
    this.id = id;
    this.date = new Date();
    this.discount = discount;
    this.grossAmount = grossAmount;
    this.totalCost = calculateTotal();
    this.complete = false;
  }

  private double calculateTotal() {
    return this.grossAmount - (this.discount * this.grossAmount);
  }

  public String updateStatus() {
    this.complete = true;
    return "Order has been set to completed...!!!";
  }
}
