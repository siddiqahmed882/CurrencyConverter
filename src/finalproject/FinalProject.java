package finalproject;

import java.util.*;

public class FinalProject {
    /* Scanner Object for input */
    static Scanner input = new Scanner(System.in);

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

    /**
     * Create a new user
     * 
     * @param customers List of all the customers
     */
    public static void createNewUser() {
        // init
        String userName;
        String password;
        String firstName;
        String lastName;
        double balance;
        boolean userNameTaken = false;
        Customer C1;
        // generating id and account number
        System.out.println("Please fill the required fields properly");
        int id = generateCustomerId(Customer.getCustomers());
        String AC = generateAccountNumber();

        // continue looping if username is already taken
        do {
            System.out.print("Enter your user name: ");
            userName = input.next();
            userNameTaken = checkUserName(userName);
            if (userNameTaken)
                System.out.println("username has already been taken");
        } while (userNameTaken);

        System.out.print("Enter your password: ");
        password = input.next();
        System.out.print("Enter your first name: ");
        firstName = input.next();
        System.out.print("Enter your last name: ");
        lastName = input.next();
        System.out.print("Enter your initial balance: ");
        balance = input.nextDouble();
        System.out.println(
                "\n\nDo you want our Silver or Gold membership?\nYou will get 5% off on all order in SILVER MEMBERSHIP and 10% off on all orders in GOLD MEMBERSHIP...!!!");
        System.out.print(
                "Enter 1 to become a Silver Customer\nEnter 2 to become a Gold Customer\nEnter 3 if you don't want to avail this offer at the moment.\n");
        System.out.print("Enter your choice :");
        byte choice = input.nextByte();

        // create customer object
        switch (choice) {
            case 1:
                C1 = new SilverCustomer(id, AC, userName, password, firstName, lastName, balance, "789");
                Customer.addCustomers(C1);
                break;
            case 2:
                C1 = new GoldCustomer(id, AC, userName, password, firstName, lastName, balance, "789");
                Customer.addCustomers(C1);
                break;
            case 3:
                C1 = new Customer(id, generateAccountNumber(), userName, password, firstName, lastName, balance, "789");
                Customer.addCustomers(C1);
                break;
        }
    }

    /* Check User Name */
    public static boolean checkUserName(String name) {
        // init
        boolean userNameTaken = false;
        for (Customer c : Customer.getCustomers()) {
            if (c.getUserName().equals(name)) {
                userNameTaken = true;
            }
        }
        return userNameTaken;
    }

    /* Login */
    public static void loginToExistingAccount() {
        Customer currCustomer = null;
        do {
            System.out.print("Please Enter your UserName: ");
            String userName = input.nextLine();
            System.out.print("Please Enter your password: ");
            String password = input.nextLine();
            for (Customer c : Customer.getCustomers()) {
                if (userName.equals(c.getUserName()) && password.equals(c.getPassword())) {
                    currCustomer = c;
                } else {
                    System.out.println("Incorrect userName/password combination. Please try again");
                }
            }
        } while (currCustomer == null);
        System.out.printf("\n\nWelcome %s\n\n", currCustomer.getFullName());
        showCustomerMenu(currCustomer);
    }

    public static void showCustomerMenu(Customer currCustomer) {
        System.out.println("Please Enter \n\t1 to edit your details");
        System.out.println("\t2 to deposit money in your account");
        System.out.println("\t3 to place a new order");
        System.out.println("\t4 to make payment");
        System.out.println("\t5 to logout and exit");
        System.out.print("Enter :");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                editPersonalInfo(currCustomer);
                break;
            case 2:
                depositAmount(currCustomer);
                break;
            case 3:
                createNewOrder(currCustomer);
                break;
            case 4:
                makePayment(currCustomer);
                break;
            case 5:
                System.out.printf("Thank you Mr %s. It was nice interacting with you. Hope to see you again");
                loginToExistingAccount();
                break;
        }
    }

    /* Edit Personal Info */
    public static void editPersonalInfo(Customer currCustomer) {
        // init
        int choice;

        // continue looping unless correct option is selected
        do {
            System.out.println("Account's Summary:");
            System.out.printf("First Name: %s | Last Name: %s | Balance: %f\n\n", currCustomer.getFirstName(),
                    currCustomer.getLastName(), currCustomer.getBalance());
            System.out.println("Please Enter\n\t1 to edit your First Name.");
            System.out.println("\t2 to edit your Last Name.");
            System.out.println("\t3 to edit your User Name.");
            System.out.println("\t4 to edit Password");
            System.out.println("\t5 to exit");
            choice = input.nextInt();
            if (choice < 1 || choice > 5)
                System.out.println("Invalid Choice...!!!");
        } while (choice < 1 || choice > 5);

        // user has selected a valid option. process the choice
        switch (choice) {
            case 1:
                System.out.print("Enter Your First Name: ");
                String fname = input.next();
                currCustomer.setFirstName(fname);
                break;
            case 2:
                System.out.print("Enter Your Last Name: ");
                String lname = input.next();
                currCustomer.setLastName(lname);
                break;
            case 3:
                System.out.print("Enter Your User Name: ");
                String userName = input.next();
                currCustomer.setUserName(userName);
                break;
            case 4:
                boolean repeat = false;
                do {
                    System.out.print("Please Enter Your Old Password to proceed: ");
                    String oldPassword = input.next();
                    if (oldPassword.equals(currCustomer.getPassword())) {
                        System.out.print("Enter A New Password: ");
                        String password = input.next();
                        currCustomer.changePassword(password);
                        repeat = false;
                    } else {
                        System.out.println("You have entered invalid password.");
                        System.out.print("Enter 1 to retry and 0 to exit");
                        int retryOrExit = input.nextInt();
                        repeat = (retryOrExit == 1) ? true : false;
                    }
                } while (repeat);
                break;
            case 5:
                showCustomerMenu(currCustomer);
                break;
        }
        editPersonalInfo(currCustomer);
    }

    /* Deposit Amount */
    public static void depositAmount(Customer currCustomer) {
        // init
        double amount;
        System.out.printf("Your current balance is: %f", currCustomer.getBalance());
        do {
            System.out.print("Enter amount to deposit: ");
            amount = input.nextDouble();
            if (amount < 0)
                System.out.println("Amount can not be less than zero");
        } while (amount < 0);
        currCustomer.setBalance(currCustomer.getBalance() + amount);
        System.out.printf("\nYour balance has been added. Your new balance is: %f", currCustomer.getBalance());
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
            System.out.print("Do you want to deposit some money in your account?\nEnter 1 to deposit or 0 to exit");
            int askToDeposit = input.nextInt();
            if (askToDeposit == 1) {
                depositAmount(currCustomer);
            } else {
                return;
            }
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
        System.out.println("**********Welcome**********");
        System.out.println("***************************");
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
                    createNewUser();
                    break;
                case 2:
                    loginToExistingAccount();
                    break;
            }
        }

    }
}