package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("========== FINANCIAL TRACKER ==========");
            System.out.println("1. Add Deposit");
            System.out.println("2. Make Payment (Debit)");
            System.out.println("3. Ledger");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Enter deposit description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine();

                    System.out.print("Enter deposit amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();

                    Transaction deposit = new Transaction(
                            date, time, description, vendor, amount);

                    System.out.println(deposit);
                    saveTransaction(deposit);
                    // Add Deposit
                }
                case "2" -> {
                    System.out.print("Enter payment description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine();

                    System.out.print("Enter payment amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();

                    Transaction payment = new Transaction(
                            date, time, description, vendor, -Math.abs(amount));

                    System.out.println(payment);
                    saveTransaction(payment);
                    // Make Payment
                }
                case "3" -> {
                    ledgerMenu(scanner);
                    // Ledger
                }
                case "4" -> {
                    System.out.println("Thank you for using Financial Tracker. Goodbye!");
                    running = false;
                    // Exit
                }
                default -> {
                    System.out.println("Option Not Valid. Please Try Again. ");
                    // Your error message goes here!
                }
            }


        }
        scanner.close();
    }

    public static void saveTransaction(Transaction transaction) {
        try {
            FileWriter writer = new FileWriter("transactions.csv", true);
            LocalDate date = transaction.getDate();
            LocalTime time = transaction.getTime();
            String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            String description = transaction.getDescription();
            String vendor = transaction.getVendor();
            double amount = transaction.getAmount();
            String formattedAmount = String.format("%.2f", amount);

            String line = date + "|" + formattedTime + "|" + description + "|" + vendor + "|" + formattedAmount;

            writer.write(line + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving transaction.");

        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("transactions.csv"));

            String line = reader.readLine(); // Skip CSV header

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction transaction = new Transaction(
                        date, time, description, vendor, amount);

                transactions.add(transaction);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error reading transactions.");
        }

        return transactions;
}

public static void displayTransactions() {
    List<Transaction> transactions = loadTransactions();

    for (int i = transactions.size() - 1; i >= 0; i--) {
        System.out.println(transactions.get(i));
    }
}

public static void displayDeposits() {
    List<Transaction> transactions = loadTransactions();

    for (int i = transactions.size() - 1; i >= 0; i--) {
        Transaction transaction = transactions.get(i);

        if (transaction.getAmount() > 0) {
            System.out.println(transaction);
        }
    }
}
    public static void displayPayments() {
        List<Transaction> transactions = loadTransactions();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }
    public static void monthToDate() {
        List<Transaction> transactions = loadTransactions();

        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (!transaction.getDate().isBefore(firstDay)
                    && !transaction.getDate().isAfter(today)) {
                System.out.println(transaction);
            }
        }
    }
    public static void previousMonth() {
        List<Transaction> transactions = loadTransactions();

        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDay = today.withDayOfMonth(1).minusDays(1);

        boolean foundTransactions = false;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (!transaction.getDate().isBefore(firstDay)
                    && !transaction.getDate().isAfter(lastDay)) {
                System.out.println(transaction);
                foundTransactions = true;
            }
        }
        if (!foundTransactions) {
            System.out.println("No transactions found for the previous month.");

        }
    }
        public static void yearToDate() {
            List<Transaction> transactions = loadTransactions();

            LocalDate today = LocalDate.now();
            LocalDate firstDay = today.withDayOfYear(1);

            boolean foundTransactions = false;

            for (int i = transactions.size() - 1; i >= 0; i--) {
                Transaction transaction = transactions.get(i);

                if (!transaction.getDate().isBefore(firstDay)
                        && !transaction.getDate().isAfter(today)) {
                    System.out.println(transaction);
                    foundTransactions = true;
                }
            }

            if (!foundTransactions) {
                System.out.println("No transactions found for the current year.");
            }
        }
    public static void previousYear() {
        List<Transaction> transactions = loadTransactions();

        LocalDate today = LocalDate.now();
        LocalDate firstDay = LocalDate.of(today.getYear() - 1, 1, 1);
        LocalDate lastDay = LocalDate.of(today.getYear() - 1, 12, 31);

        boolean foundTransactions = false;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (!transaction.getDate().isBefore(firstDay)
                    && !transaction.getDate().isAfter(lastDay)) {
                System.out.println(transaction);
                foundTransactions = true;
            }
        }

        if (!foundTransactions) {
            System.out.println("No transactions found for the previous year.");
        }
    }
    public static void searchByVendor(Scanner scanner) {
        List<Transaction> transactions = loadTransactions();

        System.out.print("Enter vendor name: ");
        String searchVendor = scanner.nextLine();

        boolean foundTransactions = false;

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);

            if (transaction.getVendor().equalsIgnoreCase(searchVendor)) {
                System.out.println(transaction);
                foundTransactions = true;
            }
        }

        if (!foundTransactions) {
            System.out.println("No transactions found for that vendor.");
        }
    }
    public static void ledgerMenu(Scanner scanner) {
        boolean viewingLedger = true;

        while (viewingLedger) {
            System.out.println("========== LEDGER ==========");
            System.out.println("1. All Transactions");
            System.out.println("2. Deposits");
            System.out.println("3. Payments");
            System.out.println("4. Reports");
            System.out.println("5. Home");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    displayTransactions();
                }
                case "2" -> {
                    displayDeposits();
                }
                case "3" -> {
                    displayPayments();
                }
                case "4" -> {
                    reportsMenu(scanner);
                }
                case "5" -> {
                    viewingLedger = false;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
    public static void reportsMenu(Scanner scanner) {
        boolean viewingReports = true;

        while (viewingReports) {
            System.out.println("========== REPORTS ==========");
            System.out.println("1. Month To Date");
            System.out.println("2. Previous Month");
            System.out.println("3. Year To Date");
            System.out.println("4. Previous Year");
            System.out.println("5. Search by Vendor");
            System.out.println("0. Back");
            System.out.print("Please select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    monthToDate();
                }
                case "2" -> {
                    previousMonth();
                }
                case "3" -> {
                    yearToDate();
                }
                case "4" -> {
                    previousYear();
                }
                case "5" -> {
                    searchByVendor(scanner);
                }
                case "0" -> {
                    viewingReports = false;
                }
                default -> {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}

