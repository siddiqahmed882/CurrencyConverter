package finalproject;

import java.util.Date;
import java.util.ArrayList;

public class Order {
  Integer id;
  Date date;
  double discount;
  double grossAmount;
  double totalCost;
  boolean complete;
  Customer holder;
  static ArrayList<Order> orders = new ArrayList<Order>();

  Order(double discount, double grossAmount, Customer holder) {
    this.id = orders.size();
    this.date = new Date();
    this.discount = discount;
    this.grossAmount = grossAmount;
    this.totalCost = calculateTotal();
    this.complete = false;
    this.holder = holder;
  }

  public static void addOrder(Order order) {
    Order.orders.add(order);
  }

  public Integer getId() {
    return this.id;
  }

  private double calculateTotal() {
    return this.grossAmount - (this.discount * this.grossAmount);
  }

  public boolean getStatus() {
    return this.complete;
  }

  public Customer getHolder() {
    return this.holder;
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

  public static String getOrderSummary(Customer customer) {
    String res = "";
    for (Order order : orders) {
      if (order.getHolder().getId() == customer.getId()) {
        res += "Order ID: " + order.getId().toString() + "| Paid: " + order.getStatus() + "\n";
      }
    }
    if (res.equals("")) {
      res = "You have no orders";
    }
    return res;
  }
}