package com.amit.book.inventory.service;

import com.amit.book.inventory.model.Customer;
import com.amit.book.inventory.model.Supplier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class SupplierService implements SupplierServiceInterface {

    private HashMap<Integer, Supplier> suppliers = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void acceptingSupplierInfo(){

        Supplier supplier = new Supplier();

        System.out.println("Enter supplier id");
        int supplierId = Integer.parseInt(scanner.nextLine());
        //supplier.setSupplierID(supplierId);

        System.out.println("Enter supplier name");
        String name = scanner.nextLine();
        supplier.setSupplierName(name);

        System.out.println("Enter supplier address");
        String address = scanner.nextLine();
        supplier.setSupplierAddress(address);

        System.out.println("Enter supplier contact number");
        Long contact = Long.valueOf(scanner.nextLine());
        supplier.setSupplierContact(contact);

        System.out.println("Enter supplier Email ID");
        String emailID = scanner.nextLine();
        supplier.setSupplierEmailId(emailID);

        suppliers.put(supplierId, supplier);
    }

    public void displaySupplierInfo(){

        // old for each loop
        /*for(Map.Entry<Integer, Supplier>set : suppliers.entrySet()){
            System.out.println("Supplier ID : " +set.getKey() + " = " + "Supplier Info : "
                    + set.getValue());
        }*/

        // java8 feature forEach loop
        suppliers.forEach((supplierID, supplier) -> System.out.println("Supplier ID: " + supplierID + " = Supplier Info: " + supplier));
    }

}
