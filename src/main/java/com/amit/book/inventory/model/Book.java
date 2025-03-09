package com.amit.book.inventory.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

   /* @Getter
    @Setter
    @ToString*/

@Data
public class Book {
    private String name;
    private String author;
    private String publisher;
    private int noOfCopies;
    private BookCategory category; // Use BookCategory enum instead of String
    private String storeLocation;
    private int price;
}