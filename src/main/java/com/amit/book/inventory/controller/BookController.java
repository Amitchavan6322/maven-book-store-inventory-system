package com.amit.book.inventory.controller;

import com.amit.book.inventory.exception.InvalidBookIDException;
import com.amit.book.inventory.exception.InvalidBookNameException;
import com.amit.book.inventory.exception.InvalidBookPriceException;
import com.amit.book.inventory.service.BookService;

import java.sql.SQLException;
import java.util.Scanner;

public class BookController {

    private static final Scanner scanner = new Scanner(System.in);

    public void run() throws SQLException {
        int option = 0;
        BookService bookService = new BookService();
        do {
            System.out.println("Please select option from below list :");
            System.out.println("1. Fill the book information");
            System.out.println("2. Display book information");
            System.out.println("3. Get book by id");
            System.out.println("9: Go back to main menu");
            option = Integer.parseInt(scanner.nextLine());


            switch (option) {
                case 1:
                    try {
                        bookService.acceptingBookInfo();
                        break;
                    } catch (InvalidBookIDException | InvalidBookNameException |
                             InvalidBookPriceException invalidBookIDException) {
                        System.err.println(invalidBookIDException.getMessage());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                case 2:
                    bookService.displayBookInfo();
                    break;

                case 3:
                    System.out.println("Enter book id");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    bookService.getBookById(bookId);
                    break;
            }

        }
        while (option != 9);
        //System.out.println("Welcome back to main menu!");
    }
}
