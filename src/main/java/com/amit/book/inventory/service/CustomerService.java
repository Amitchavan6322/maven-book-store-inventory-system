package com.amit.book.inventory.service;

import com.amit.book.inventory.model.Book;
import com.amit.book.inventory.model.Customer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class CustomerService implements CustomerServiceInterface{

    private HashMap<Integer, Customer> customers = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);


    public void acceptCustomerInfo(){

        Customer customer = new Customer();

        System.out.println("Enter customer id");
        int customerId = Integer.parseInt(scanner.nextLine());
        //customer.setCustomerID(customerId);

        System.out.println("Enter customer name");
        String name = scanner.nextLine();
        customer.setCustomerName(name);

        System.out.println("Enter customer address");
        String address = scanner.nextLine();
        customer.setCustomerAddress(address);

        System.out.println("Enter customer contact");
        Long contact = Long.valueOf(scanner.nextLine());
        customer.setCustomerContact(contact);

        System.out.println("Enter customer Email ID");
        String emailID = scanner.nextLine();
        customer.setEmailID(emailID);

        customers.put(customerId, customer);
    }

    public void displayCustomerInfo(){

        //old for each loop
        /*for(Map.Entry<Integer, Customer>set : customers.entrySet()){
            System.out.println("Customer ID : " +set.getKey() + " = " + "Customer Info : "
                    + set.getValue());
        }*/

        // Java 8 features forEach loop
        customers.forEach((customerId, customer) -> System.out.println("Customer ID: " + customerId + " = Customer Info: " + customer));
    }
}
