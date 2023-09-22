/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.Management;
import java.util.Scanner;

/**
 *
 * @author viet
 */
public class Manager extends Menu<String> {

    public static Scanner sc = new Scanner(System.in);
    static String[] mc = {"Create Fruit", "View orders", "Shopping (for buyer)", "Exit"};
    protected Management managers;

    public Manager() {
        super("FRUIT SHOP SYSTEM", mc);
        managers = new Management();
    }

    @Override
    public void execute(String n) {
        switch (n) {
            case "1" -> createNew();
            case "2" -> managers.printAllOrders();
            case "3" -> managers.takeOrder();
            case "4" -> System.exit(0);

        }
    }

    public void createNew() {
        while (true) {
            managers.addFruit();
            System.out.print("Do you want to continue adding fruits (Y/N)? ");

            if (!Validation.checkInputYN()) {
                break;
            }
        }

        // Display all fruits that were created
        managers.displayFruitList();
    }

}
