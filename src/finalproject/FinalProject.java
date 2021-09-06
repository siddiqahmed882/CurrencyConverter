package finalproject;

import java.util.*;

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

    /* Generate Customer Id */
    public static int generateCustomerId(ArrayList<Customer> noCustomer) {
        return noCustomer.size();
    }

    static Scanner input = new Scanner(System.in);

    /**
     * Create a new user
     * 
     * @param customers List of all the customers
     */
    public static void createNewUser(ArrayList<Customer> customers) {
        System.out.println("Please fill the required fields properly");
        int id = generateCustomerId(customers);
        String AC = generateAccountNumber();
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
        System.out.println(
                "\n\nDo you want our Silver or Gold membership?\nYou will get 5% off on all order in SILVER MEMBERSHIP and 10% off on all orders in GOLD MEMBERSHIP...!!!");
        System.out.print(
                "Enter 1 to become a Silver Customer\nEnter 2 to become a Gold Customer\nEnter 3 if you don't want to avail this offer at the moment.\n");
        System.out.print("Enter your choice :");
        byte choice = input.nextByte();
        // create customer object
        Customer C1;
        switch (choice) {
            case 1:
                C1 = new SilverCustomer(id, AC, userName, password, firstName, lastName, balance, "789");
                customers.add(C1);
                break;
            case 2:
                C1 = new GoldCustomer(id, AC, userName, password, firstName, lastName, balance, "789");
                customers.add(C1);
                break;
            case 3:
                C1 = new Customer(id, generateAccountNumber(), userName, password, firstName, lastName, balance, "789");
                customers.add(C1);
                break;
        }
    }

    /* Login */
    public static void loginToExistingAccount(ArrayList<Customer> customers) {
        Customer currCustomer = null;
        do {
            System.out.print("Please Enter your UserName: ");
            String userName = input.nextLine();
            System.out.print("Please Enter your password: ");
            String password = input.nextLine();
            for (Customer c : customers) {
                if (userName.equals(c.getUserName()) && password.equals(c.getPassword())) {
                    currCustomer = c;
                } else {
                    System.out.println("Incorrect userName/password combination. Please try again");
                }
            }
        } while (currCustomer == null);

        showCustomerMenu(currCustomer);
    }

    public static void showCustomerMenu(Customer currCustomer) {
        System.out.printf("\n\nWelcome %s\n\n", currCustomer.getFullName());
        System.out.println("Please Enter \n\t1 to edit your details");
        System.out.println("\t2 to place a new order");
        System.out.println("\t3 to make payment");
        System.out.print("Enter :");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                editPersonalInfo(currCustomer);
                break;
            case 2:
                createNewOrder(currCustomer);
                break;
            case 3:
                makePayment(currCustomer);
                break;
        }
    }

    /* Edit Personal Info */
    public static void editPersonalInfo(Customer currCustomer) {
        System.out.println("Babu bhaiya mera kam baqi hai abi :))");
        showCustomerMenu(currCustomer);
    }

    /* Create new Order */
    public static void createNewOrder(Customer currCustomer) {
        if (currCustomer instanceof SilverCustomer) {
            currCustomer = (SilverCustomer) currCustomer;
        } else if (currCustomer instanceof GoldCustomer) {
            currCustomer = (GoldCustomer) currCustomer;
        }
        double discount = currCustomer.getDiscount();
        System.out.print("Please Enter the total cost: ");
        double grossAmount = input.nextDouble();
        Order newOrder = new Order(discount, grossAmount, currCustomer);
        Order.orders.add(newOrder);
        currCustomer.orders.add(newOrder);
        System.out.printf("This is your order id [%d]. It will be user for further transaction", newOrder.getId());
        showCustomerMenu(currCustomer);
    }

    /* Make Payment */
    public static void makePayment(Customer currCustomer) {
        // init
        boolean repeat = false;
        Integer choice;
        String currencyOut;
        double payableAmount;
        do {
            System.out.println(Order.getOrderSummary(currCustomer));
            System.out.print("Please Enter a valid order id: ");
            choice = input.nextInt();
            for (Order order : currCustomer.getOrders()) {
                if (!order.getId().equals(choice)) {
                    System.out.println("Invalid ID. please try again");
                    repeat = true;
                } else {
                    repeat = false;
                }
            }
        } while (repeat);

        /* user has entered a valid orderId. Now Prompt him to make a payment */

        // check if he has already paid. and return if it is true
        Order order = currCustomer.getOrders().get(choice);
        if (order.getStatus()) {
            System.out.println("You have already paid...!!!");
            return;
        }

        // user has to make a payment
        System.out.print("\n\nWhich currency do you want to make payment in?\n\n");
        String[] currencyCodes = { "United States -> USD", "Australian Dollar -> AUD", "British Pound -> GBP",
                "Canadian Dollar -> CAD", "Turkish Lira -> TRY", "Indonesian Rupiya -> IDR", "Afghan Afghani -> AFN",
                "Pakistani Rupee -> PKR" };
        String[] codes = { "USD", "AUD", "GBP", "CAD", "TRY", "IDR", "AFN", "PKR" };
        for (String code : currencyCodes)
            System.out.println(code);
        System.out.println();
        do {
            System.out.print("Enter :");
            currencyOut = input.next();
            for (String code : codes) {
                if (!code.equals(currencyOut)) {
                    repeat = true;
                } else {
                    repeat = false;
                }
            }
            if (repeat) {
                System.out.print("\nPlease Enter a valid Code: ");
            }
        } while (repeat);

        // Displaying total cost
        double totalCost = currCustomer.getOrders().get(choice).getTotalCost();
        payableAmount = Currency.convertCurrency(totalCost, currencyOut);
        System.out.println("Your payable amount is: " + payableAmount);
        if (currCustomer.getBalance() < totalCost) {
            System.out.printf("Insufficient Balance.\n" + "Your balance is :" + currCustomer.getBalance());
            // ask user if you want to deposit money in his account
            return;
        }

        int confirmPayment;
        do {
            System.out.println("Enter 1 to confirm payment or 0 to exit");
            confirmPayment = input.nextInt();
        } while (confirmPayment < 0 || confirmPayment > 1);

        if (confirmPayment == 1) {
            currCustomer.setBalance(currCustomer.getBalance() - totalCost);
            System.out.print("\nThank you for the payment.\nYour remaining Balance is " + currCustomer.getBalance());
        } else if (confirmPayment == 0) {
            System.out.println("Its Okay :((");
        }

        showCustomerMenu(currCustomer);
    }

    public static boolean makePayment(Customer customer, Order order) {
        double balance = customer.getBalance();
        double cost = order.getTotalCost();
        if (balance < cost) {
            return false;
        } else {
            double newBalance = balance - cost;
            customer.setBalance(newBalance);
            return true;
        }
    }

    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        System.out.println("**********Welcome**********");
        System.out.println("***********************");
        byte choice;
        while (true) {
            do {
                System.out.println("Please Enter\n\t1 to create an account;\n\t2 to login");
                choice = input.nextByte();
                // check if the input option is valid
                if (choice < 1 || choice > 2) {
                    System.out.println("Please choose a valid option");
                }
            } while (choice < 1 || choice > 2); // continue looping till user enter a correct option
            switch (choice) {
                case 1:
                    createNewUser(customers);
                    break;
                case 2:
                    loginToExistingAccount(customers);
                    break;
            }
        }

    }
}