package com.amit.book.inventory.controller;

import com.amit.book.inventory.service.SupplierService;

import java.util.Scanner;

public class SupplierController {

    private static final Scanner scanner = new Scanner(System.in);

    public void run() {
        int option = 0;
        SupplierService supplierService = new SupplierService();
        do {
            System.out.println("Please select option from below list :");
            System.out.println("1. Fill supplier information");
            System.out.println("2. Display supplier information");
            System.out.println("9: Go back to main menu");
            option = Integer.parseInt(scanner.nextLine());


            switch (option) {
                case 1:
                    supplierService.acceptingSupplierInfo();
                    break;

                case 2:
                    supplierService.displaySupplierInfo();
                    break;
            }

        }
        while (option != 9);
        //System.out.println("Welcome back to main menu!");
    }
}
