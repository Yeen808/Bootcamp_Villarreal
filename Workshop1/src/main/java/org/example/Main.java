package org.example;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final int MONTHS_IN_A_YEAR = 12;
    public static void main(String[] args) {

        Scanner scanner  = new Scanner(System.in);

int choice = 0;
        boolean running = true;
        System.out.println(" welcome to calculator ");
        System.out.println( " 1 financial calculator");
        System.out.println( " 2 future value calculator ");
        System.out.println(" 3 Exit ");
        System.out.println(" choose one of the options ");

        choice = scanner.nextInt();
        switch(choice){
            case 1: {
                System.out.println(" financial calculator ");

                System.out.println(" What is your principal ");
                double principal = scanner.nextDouble();

                System.out.println( "What is your interest rate ");
                float annualintrestrate = scanner.nextFloat();

                System.out.println(" Enter term in years ");
                int terminyears = scanner.nextInt();
                float monthlyintrestrate = annualintrestrate / MONTHS_IN_A_YEAR;
                int numberOfPayments = terminyears * MONTHS_IN_A_YEAR;

                double monthlypayment = principal *
                        ((monthlyintrestrate * Math.pow(1 + monthlyintrestrate, numberOfPayments)) /
                                (Math.pow(1 + monthlyintrestrate, numberOfPayments) - 1));
                System.out.println(String.format(" monthly payment " + NumberFormat.getCurrencyInstance().format(monthlypayment)));






            }

            case 2: {
                System.out.println("Future Value Calculator");

                scanner = new Scanner(System.in);

                System.out.println("Enter your initial deposit: ");
                double principal = scanner.nextDouble();

                System.out.println("Enter the annual interest rate (percent): ");
                double annualInterestRate = scanner.nextDouble();

                System.out.println("Enter the number of years: ");
                int years = scanner.nextInt();

                // Convert the percentage to a decimal
                double rate = annualInterestRate / 100.0;

                // Daily compounding
                int daysPerYear = 365;
                int totalDays = daysPerYear * years;

                // Calculate future value
                double futureValue = principal *
                        Math.pow(1 + (rate / daysPerYear), totalDays);

                // Calculate total interest earned
                double interestEarned = futureValue - principal;

                // Display the results as currency
                System.out.println("Ending Balance: " +
                        NumberFormat.getCurrencyInstance().format(futureValue));

                System.out.println("Total Interest Earned: " +
                        NumberFormat.getCurrencyInstance().format(interestEarned));

                break;

            }

            case 3: {
                running = false;



            }
        }








    }
}

