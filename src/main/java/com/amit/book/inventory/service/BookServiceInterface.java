package com.amit.book.inventory.service;

import com.amit.book.inventory.exception.InvalidBookIDException;
import com.amit.book.inventory.exception.InvalidBookNameException;
import com.amit.book.inventory.exception.InvalidBookPriceException;
import com.amit.book.inventory.model.Book;

import java.sql.SQLException;

public interface BookServiceInterface {
    void acceptingBookInfo() throws InvalidBookIDException, InvalidBookNameException, InvalidBookPriceException, SQLException;
    void displayBookInfo();
    Book getBookById(int bookId);
    void removeBookById(int bookId);
    void updateBookPrice(int bookId, int newPrice);
}
