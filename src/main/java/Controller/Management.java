package Controller;

import Model.Fruit;
import Model.Order;
import View.Validation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Management {

    public Scanner sc = new Scanner(System.in);
    ArrayList<Fruit> fruitList = new ArrayList<>();
    Hashtable<String, ArrayList<Order>> shoppingList = new Hashtable<>();

    public void addFruit() {
        System.out.println("Enter the details of the new fruit:");

        System.out.print("ID: ");
        String id = Validation.checkInputString();

        System.out.print("Name: ");
        String name = Validation.checkInputString();

        System.out.print("Price: ");
        int price = Validation.getInputInteger();

        System.out.print("Quantity: ");
        int quantity = Validation.getInputInteger();

        System.out.print("Origin: ");
        String origin = Validation.checkInputString();

        Fruit newFruit = new Fruit(id, name, price, quantity, origin);
        fruitList.add(newFruit);

        System.out.println("New fruit added to the list.");
    }

 public void takeOrder() {
    while (true) {
        // Filter the fruit list to only display fruits with quantity > 0
        List<Fruit> availableFruits = fruitList.stream()
                .filter(fruit -> fruit.getQuantity() > 0)
                .collect(Collectors.toList());

        if (availableFruits.isEmpty()) {
            System.out.println("Sorry, all fruits are out of stock.");
            break;
        }

        displayFruitList();

        System.out.print("Select an item (1-" + availableFruits.size() + ") or enter 0 to finish ordering: ");
        int choice = Validation.checkInputIntLimit(0, availableFruits.size());

        if (choice == 0) {
            break;
        }

        Fruit selectedFruit = availableFruits.get(choice - 1);
        System.out.println("You selected: " + selectedFruit.getName());

        // Move the input for the customer's name here
        System.out.print("Enter your name: ");
        String customerName = Validation.checkInputString();

        System.out.print("Please input quantity: ");
        int quantity = Validation.checkInputIntLimit(1, selectedFruit.getQuantity());

        // Check if the customer has already ordered the same fruit
        boolean found = false;
        ArrayList<Order> orderHistory = shoppingList.getOrDefault(customerName, new ArrayList<>());
        for (Order existingOrder : orderHistory) {
            if (existingOrder.getProname().equals(selectedFruit.getName())) {
                existingOrder.setQuantity(existingOrder.getQuantity() + quantity);
                existingOrder.setAmount(existingOrder.getAmount() + (quantity * selectedFruit.getPrice()));
                found = true;
                break;
            }
        }

        if (!found) {
            Order order = new Order(customerName, selectedFruit.getName(), quantity, selectedFruit.getPrice());
            orderHistory.add(order);
        }

        // Update the quantity of the fruit in stock if it remains greater than 0
        if (selectedFruit.getQuantity() > 0) {
            selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);
        }

        System.out.print("Do you want to order now (Y/N)? ");
        boolean orderConfirmation = Validation.checkInputYN();

        if (!orderConfirmation) {
            break;
        }

        // Update the customer's order history
        shoppingList.put(customerName, orderHistory);
    }
}


    public void displayFruitList() {
        System.out.println("List of Fruit:");
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ | ++ Quantity ++ |");
        int itemNumber = 1;
        for (Fruit fruit : fruitList) {
            if (fruit.getQuantity() > 0) {
                System.out.printf("%12d %16s %16s %6d$ %12d\n", itemNumber, fruit.getName(), fruit.getOrigin(), fruit.getPrice(), fruit.getQuantity());
                itemNumber++;
            }
        }
    }

    public void printAllOrders() {
        System.out.println("Customer Orders:");
        for (String customerName : shoppingList.keySet()) {
            System.out.println("Customer: " + customerName);
            ArrayList<Order> orderHistory = shoppingList.get(customerName);

            if (orderHistory != null && !orderHistory.isEmpty()) {
                System.out.printf("%-20s | %-15s | %-6s$ | %-6s$\n", "Product Name", "Quantity", "Price", "Amount");
                int totalAmount = 0; // Initialize the total amount for this customer

                for (Order order : orderHistory) {
                    System.out.printf("%-20s | %-15d | %6d$ | %6d$\n", order.getProname(), order.getQuantity(),
                            order.getPrice(), order.getAmount());
                    totalAmount += order.getAmount();
                }

                // Print the total amount spent by the customer
                System.out.println("Total Amount Spent: " + totalAmount + "$"); // Convert back to dollars
            } else {
                System.out.println("No orders found for this customer.");
            }

            System.out.println(); // Add a blank line to separate customers
        }
    }

}
