package finalproject;

import java.util.*;

class Customer {
    int id;
    String AC;
    String userName;
    String password;
    String firstName;
    String lastName;
    double balance;
    String branchCode;
    boolean discountStatus;

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

    public String toString() {
        return "ID: " + id + "\n" + "Account Number: " + AC + "\n" + "Name: " + firstName + " " + lastName + "\n"
                + "Balance: " + balance;
    }
}

class Order {
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

public class FinalProject {
    /* Generate Account Number */
    public static String generateAccountNumber() {
        String[] arr = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String accountNumber = "";
        for (int i = 0; i < 13; i++) {
            int randomNumber = (int) (Math.random() * 35);
            accountNumber += arr[randomNumber];
        }
        // check if account number is already taken
        return accountNumber;
    }

    /* Generate Id */
    public static int generateId(ArrayList<Customer> noCustomer) {
        return noCustomer.size();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Customer> customers = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        byte choice = 0;
        do {
            System.out.print("Enter your user name: ");
            String userName = input.next();
            System.out.print("Enter your password: ");
            String password = input.next();
            System.out.print("Enter your first name: ");
            String firstName = input.next();
            System.out.print("Enter your last name: ");
            String lastName = input.next();
            System.out.print("Enter your initial balance: ");
            double balance = input.nextDouble();
            System.out.print("Enter your discount status: ");
            boolean discountStatus = input.nextBoolean();
            int id = generateId(customers);
            // create customer object
            Customer C1 = new Customer(id, generateAccountNumber(), userName, password, firstName, lastName, balance,
                    "Branch789", discountStatus);
            customers.add(C1);
            // ask if want to create another user
            System.out.println("Enter 1 to create new user");
            choice = input.nextByte();
        } while (choice == 1);
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
    }

}
