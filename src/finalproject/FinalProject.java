package finalproject;

import java.util.*;
import java.io.IOException;

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

    /* Generate Order Id */
    public static int generateOrderId(ArrayList<Order> orders) {
        return orders.size();
    }

    static Scanner input = new Scanner(System.in);

    /* Create a new User */
    public static void createNewUser(ArrayList<Customer> customers) {
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
        int id = generateCustomerId(customers);
        // create customer object
        Customer C1 = new Customer(id, generateAccountNumber(), userName, password, firstName, lastName, balance,
                "Branch789");
        customers.add(C1);
        System.out.println("\nThis is your id. It will be used for future transactions: " + C1.getId());
    }

    /* Create new Orer */
    public static Order createNewOrder(int id) {
        System.out.println("\nPlease Enter the discount in this format;\n\t10%% --> 0.1\n\t20% --> 0.2 ....");
        System.out.print("Enter the discount: ");
        double discount = input.nextDouble();
        System.out.print("Enter the total amount: ");
        double grossAmount = input.nextDouble();
        return new Order(id, discount, grossAmount);
    }

    /* Place Order */
    public static void placeAnOrder(ArrayList<Customer> customers, ArrayList<Order> orders) {
        System.out.print("Enter your User Name: ");
        String userName = input.next();
        System.out.print("Enter your password: ");
        String pw = input.next();
        for (Customer customer : customers) {
            if (userName.equals(customer.getUserName())) {
                if (pw.equals(customer.getPassword())) {
                    int orderId = generateOrderId(orders);
                    Order O1 = createNewOrder(orderId);
                    orders.add(O1);
                    customer.addOrder(O1);
                    System.out.println(
                            "\n" + O1.getId() + ": This is your order id. This will be used for further transactions");
                    break;
                }
            }
            System.out.println("\nUser Name or password you entered is incorrect");
        }
    }

    /* Make Payment */
    public static void makePayment(ArrayList<Customer> customers) {
        System.out.print("Enter your User Name: ");
        String userName = input.next();
        System.out.print("Enter your password: ");
        String pw = input.next();
        for (Customer customer : customers) {
            if (userName.equals(customer.getUserName()) && pw.equals(customer.getPassword())) {
                System.out.print("Enter your Order ID: ");
                int id = input.nextInt();
                for (Order order : customer.getOrders()) {
                    if (order.getId() != id)
                        return;
                    if (order.getStatus()) {
                        System.out.println("You have already Paid for it");
                        return;
                    } else {
                        String[] Currency_Codes = { "\n" + "United States->USD", "Australian Dollar->AUD",
                                "British Pound->GBP", "Canadian Dollar->CAD", "Turkish Lira->TRY",
                                "Indonesian Rupiya->IDR", "Afghan Afghani->AFN", "Pakistani Rupee->PKR" };
                        System.out.println(Arrays.toString(Currency_Codes));
                        System.out.print("\nEnter the currency of payment: ");
                        String currencyOut = input.next();
                        Currency C1 = new Currency(1, "PKR", currencyOut);
                        System.out.println("Your payable amount is: "
                                + C1.convertCurrency(order.getTotalCost(), "PKR", currencyOut));
                        System.out.println("Are you paying right now? 'Yes' OR 'NO'");
                        String choice = input.next().toLowerCase();
                        boolean flag = (choice.equals("yes")) ? true : false;
                        if (flag) {
                            var success = makePayment(customer, order);
                            if (success) {
                                System.out.println("Thank you For The Payment.");
                                order.setStatus(flag);
                            } else {
                                System.out.println("Insuufficient balance..!!!");
                            }
                        } else {
                            System.out.println("Its Okay :((");
                        }
                    }
                }
            }
        }
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

    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        System.out.println("**********Welcome**********");
        System.out.println("***********************");
        while (true) {
            System.out.println(
                    "Enter: \n1 to create new Customer \n2 to place an order \n3 to make a payment \n4 To quit");
            byte userChoice = input.nextByte();
            if (userChoice != 4) {
                switch (userChoice) {
                    case 1:
                        createNewUser(customers);
                        continue;
                    case 2:
                        placeAnOrder(customers, orders);
                        continue;
                    case 3:
                        makePayment(customers);
                        continue;
                }
            }
            System.out.println("\n<<< SUMMARY >>>\n");
            break;
        }

        for (Customer customer : customers) {
            System.out.println(customer);
        }

    }
}