package org.example;

import java.util.Scanner;

//This template is to help you know where certain code should live
//This is to help you along if you're stuck
public class Main {
    public static void main(String[] args) {
        //You will only need ONE scanner
        Scanner scanner = new Scanner(System.in);

        // Create 20 Book objects manually in an array
        //Or have ChatGPT help you
        Book[] books = {
                new Book(0, "ISBN-PLACEHOLDER-001", "The Call of Cthulhu", false, ""),
        new Book(1, "ISBN-PLACEHOLDER-002", "The Shadow over Innsmouth", true, ""),
        new Book(2, "ISBN-PLACEHOLDER-003", "At the Mountains of Madness", false, ""),
        new Book(3, "ISBN-PLACEHOLDER-004", "The Dunwich Horror", false, ""),
        new Book(4, "ISBN-PLACEHOLDER-005", "The Colour Out of Space", true, ""),
        new Book(5, "ISBN-PLACEHOLDER-006", "The Shadow Out of Time", false, ""),
        new Book(6, "ISBN-PLACEHOLDER-007", "The Case of Charles Dexter Ward", false, ""),
        new Book(7, "ISBN-PLACEHOLDER-008", "The Dreams in the Witch House", false, ""),
        new Book(8, "ISBN-PLACEHOLDER-009", "The Whisperer in Darkness", false, ""),
        new Book(9, "ISBN-PLACEHOLDER-010", "The Haunter of the Dark", true, ""),
        new Book(10, "ISBN-PLACEHOLDER-011", "The Rats in the Walls", false, ""),
        new Book(11, "ISBN-PLACEHOLDER-012", "The Thing on the Doorstep", false, ""),
        new Book(12, "ISBN-PLACEHOLDER-013", "The Music of Erich Zann", true, ""),
        new Book(13, "ISBN-PLACEHOLDER-014", "The Horror at Red Hook", false, ""),
        new Book(14, "ISBN-PLACEHOLDER-015", "The Lurking Fear", false, ""),
        new Book(15, "ISBN-PLACEHOLDER-016", "Dagon", false, ""),
        new Book(16, "ISBN-PLACEHOLDER-017", "The Festival", false, ""),
        new Book(17, "ISBN-PLACEHOLDER-018", "The Temple", false, ""),
        new Book(18, "ISBN-PLACEHOLDER-019", "The Outsider", true, " Vorhees "),
        new Book(19, "ISBN-PLACEHOLDER-020", "The Tomb", false, ""),
};
        //This while loop runs forever
        //That means the program will run until we tell it to stop.
        while (true) {
            //Show a menu to the user
            //Use the scanner to take in the user input
            //For example
            System.out.println("\n=== BOOK STORE HOME SCREEN ===");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int userInput = scanner.nextInt();

            //Now use if statements depending on what the person typed
            //How many possibilities are there? Write your if statement structure first

            //Loop through your array of books and use if statements
            //Do we show all books? Only checked-in books?

            //How do we exit the program?
            //This command kills the program at whatever point its at.
            System.exit(0);

            //break
        }
    }
}