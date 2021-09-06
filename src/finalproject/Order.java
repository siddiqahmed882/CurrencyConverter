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

  public int getId() {
    return this.id;
  }

  private double calculateTotal() {
    return this.grossAmount - (this.discount * this.grossAmount);
  }

  public boolean getStatus() {
    return this.complete;
  }

  public void setStatus(boolean flag) {
    this.complete = flag;
  }

  public double getTotalCost() {
    return this.totalCost;
  }

  public String updateStatus() {
    this.complete = true;
    return "Order has been set to completed...!!!";
  }

  public String toString() {
    return "Order Id: " + this.id + " Order Status: " + this.complete;
  }
}