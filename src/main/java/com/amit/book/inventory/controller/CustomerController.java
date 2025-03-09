package com.amit.book.inventory.controller;

import com.amit.book.inventory.service.CustomerService;

import java.util.Scanner;

public class CustomerController {

    private static final Scanner scanner = new Scanner(System.in);

    public void run() {
        int option = 0;
        CustomerService customerService = new CustomerService();
        do {
            System.out.println("Please select option from below list :");
            System.out.println("1. Fill the customer information");
            System.out.println("2. Display customer information");
            System.out.println("9: Go back to main menu");
            option = Integer.parseInt(scanner.nextLine());


            switch (option) {
                case 1:
                    customerService.acceptCustomerInfo();
                    break;

                case 2:
                    customerService.displayCustomerInfo();
                    break;
            }

        }
        while (option != 9);
       // System.out.println("Welcome back to main menu!");
    }
}
