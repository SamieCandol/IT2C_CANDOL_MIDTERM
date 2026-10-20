package com.scc.ui;

import com.scc.dao.ScenarioDao;
import com.scc.model.ScenarioEntity;
import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {
    private final ScenarioDao dao = new ScenarioDao();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("            COFFEE SHOP ORDERS SYSTEM             ");
            System.out.println("--------------------------------------------------");
            System.out.println("[1] Add New Order");
            System.out.println("[2] View Active Orders");
            System.out.println("[3] Exit System");
            System.out.println("--------------------------------------------------");
            System.out.print("Enter choice: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    addOrderFlow();
                    break;
                case 2:
                    viewOrdersFlow();
                    break;
                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 3.");
            }
        }
    }

    private void addOrderFlow() {
        System.out.println("\n--- ADD NEW ORDER ---");
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Drink Item: ");
        String drink = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = 0.0;
        if (scanner.hasNextDouble()) {
            price = scanner.nextDouble();
            scanner.nextLine();
        } else {
            System.out.println("Invalid price format.");
            scanner.next();
            return;
        }

        System.out.print("Enter Status (PENDING / PREPARING / COMPLETED): ");
        String status = scanner.nextLine().trim().toUpperCase();

        ScenarioEntity newOrder = new ScenarioEntity(name, drink, price, status);
        dao.addRecord(newOrder);
    }

    private void viewOrdersFlow() {
        System.out.println("\n==================================================");
        System.out.println("              ACTIVE COFFEE ORDERS                ");
        System.out.println("==================================================\n");

        List<ScenarioEntity> records = dao.getAllRecords();

        if (records.isEmpty()) {
            System.out.println("No active orders found in the database.");
        } else {
            System.out.println("+-----+-----------------------+-----------------------+------------+---------------+");
            System.out.printf("| %-3s | %-21s | %-21s | %-10s | %-13s |\n", "ID", "CUSTOMER NAME", "DRINK ITEM", "PRICE", "STATUS");
            System.out.println("+-----+-----------------------+-----------------------+------------+---------------+");

            for (ScenarioEntity s : records) {
                System.out.printf("| %-3d | %-21s | %-21s | %-10.2f | %-13s |\n",
                        s.getId(),
                        s.getCustomerName(),
                        s.getDrinkItem(),
                        s.getPrice(),
                        s.getStatus());
            }
            System.out.println("+-----+-----------------------+-----------------------+------------+---------------+");
            System.out.println("Total Records Displayed: " + records.size());
        }
        System.out.println("\n--------------------------------------------------");
    }
}