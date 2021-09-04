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
        System.out.print("Enter your discount status: ");
        boolean discountStatus = input.nextBoolean();
        int id = generateCustomerId(customers);
        // create customer object
        Customer C1 = new Customer(id, generateAccountNumber(), userName, password, firstName, lastName, balance,
                "Branch789", discountStatus);
        customers.add(C1);
        System.out.println("This is your id. It will be used for future transactions: " + C1.getId());
    }

    /* Place Order */
    public static void placeAnOrder(ArrayList<Customer> customers, ArrayList<Order> orders) {
        System.out.print("Enter your ID: ");
        int id = input.nextInt();
        for (Customer customer : customers) {
            if (id == customer.getId()) {
                Order O1 = new Order(generateOrderId(orders), 0.1, 5000);
                orders.add(O1);
                customer.addOrder(O1);
            } else {
                System.out.println("Please Enter a Valid Id");
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        System.out.println("*******************************Welcome*******************************");
        System.out.println("*********************************************************************");
        while (true) {
            System.out.println("Enter: \n1 to create new Customer \2 to place an order");
            byte userChoice = input.nextByte();
            switch (userChoice) {
                case 1:
                    createNewUser(customers);
                    continue;
                case 2:
                    placeAnOrder(customers, orders);
                    continue;
                case 3:
                    break;
            }
            break;
        }

        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
