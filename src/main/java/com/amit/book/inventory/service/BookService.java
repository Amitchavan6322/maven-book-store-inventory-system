package com.amit.book.inventory.service;

import com.amit.book.inventory.exception.InvalidBookPriceException;
import com.amit.book.inventory.model.Book;
import com.amit.book.inventory.model.BookCategory;
import com.amit.book.inventory.exception.InvalidBookNameException;
import com.amit.book.inventory.exception.InvalidBookIDException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookService extends LibraryService implements BookServiceInterface {

    private HashMap<Integer, Book> books = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void acceptingBookInfo() throws InvalidBookIDException, InvalidBookNameException, InvalidBookPriceException {

        if (isBookCollectionEmpty()) {
            System.out.println("No books currently in the inventory.");
        }

        Book book = new Book();
        System.out.println("Enter book id");
        int bookId;
        try {
            bookId = Integer.parseInt(scanner.nextLine());
            // book.setBookID(bookId);
        } catch (NumberFormatException e) {
            //System.out.println("Invalid input, please provide a valid numeric ID.");
            //return;
            throw new InvalidBookIDException("Invalid book id please provide valid id");
        }

        System.out.println("Enter book Name");
        String name = scanner.nextLine();
        try {
            if ((name.isEmpty())) {
                throw new InvalidBookNameException("Book name can't be empty");
            }
            book.setName(name);
        } catch (InvalidBookNameException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Enter book Author");
        String author = scanner.nextLine();
        book.setAuthor(author);

        System.out.println("Enter book Publisher");
        String publisher = scanner.nextLine();
        book.setPublisher(publisher);

        System.out.println("Enter no of book copies");

        try {
            int noOfCopies = Integer.parseInt(scanner.nextLine());
            book.setNoOfCopies(noOfCopies);
        } catch (NumberFormatException e) {
            throw new InvalidBookIDException("Please provide no. of copies in number");
        }


        // Updated category input
        System.out.println("Enter book category (Options: ACADEMIC, FICTION, HISTORY, MUSIC)");
        String categoryInput = scanner.nextLine().toUpperCase(); // Convert input to uppercase for enum compatibility
        try {
            BookCategory category = BookCategory.valueOf(categoryInput); // Convert input to enum value
            book.setCategory(category);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Please enter one of the following: ACADEMIC, FICTION, ACTION, MUSIC");
            return; // Exit the method if invalid input
        }

        System.out.println("Enter book store location");
        String storeLocation = scanner.nextLine();
        book.setStoreLocation(storeLocation);

        System.out.println("Enter book price");
        try {
            int price = Integer.parseInt(scanner.nextLine());
            book.setPrice(price);
        } catch (NumberFormatException e) {
            throw new InvalidBookPriceException("Invalid book price please provide valid id");
        }
        //System.out.println("Book to be added: " + book);
        books.put(bookId, book);
    }

        /*public void displayBookInfo () {
            System.out.println("inside display book info map size = " +books.size());

            // old for each loop
            *//*for (Map.Entry<Integer, Book> set : books.entrySet()) {
                System.out.println("Book ID: " + set.getKey() + " = Book Info: " + set.getValue());
            }*//*

            // java8 feature forEach loop
            books.forEach((id, book) -> System.out.println("Book ID: " + id + " = Book Info: " + book));
        }*/

    // using stream api
    public void displayBookInfo() {
        books.entrySet().stream().parallel()
                //.filter(entry -> entry.getValue().getPrice()>200)
                .forEach(entry -> System.out.println("Book ID: " + entry.getKey() + " = Book Info: " + entry.getValue()));
    }

    // method to retrieve book by id
    public Book getBookById(int bookId) {
        if (books.containsKey(bookId)) {
            return books.get(bookId);
        } else {
            System.out.println("Book ID " + bookId + " not found.");
            return null;
        }
    }

    // Method to remove a book by ID
    public void removeBookById(int bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            System.out.println("Book ID " + bookId + " removed.");
        } else {
            System.out.println("Book ID " + bookId + " not found.");
        }
    }

    // Method to update a book's price
    public void updateBookPrice(int bookId, int newPrice) {
        if (books.containsKey(bookId)) {
            Book book = books.get(bookId);
            book.setPrice(newPrice);
            books.put(bookId, book); // Update the map with the new price
            System.out.println("Book ID " + bookId + " updated with new price.");
        } else {
            System.out.println("Book ID " + bookId + " not found.");
        }
    }

    // Method to check if the collection is empty
    public boolean isBookCollectionEmpty() {
        return books.isEmpty();
    }

    // Method to clear the book collection
    public void clearBookCollection() {
        books.clear();
        System.out.println("All books have been removed.");
    }

    // Retrieve books by author using Stream API
    public List<Book> getBooksByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    // Retrieve books by category using Stream API
    public List<Book> getBooksByCategory(BookCategory category) {
        return books.values().stream()
                .filter(book -> book.getCategory() == category)
                .collect(Collectors.toList());
    }

    // Sort books by price using Stream API
    public List<Book> getBooksSortedByPrice() {
        return books.values().stream()
                .sorted((book1, book2) -> Integer.compare(book1.getPrice(), book2.getPrice()))
                .collect(Collectors.toList());
    }

    // Remove books below a price using Stream API
    public void removeBooksBelowPrice(int priceThreshold) {
        books.entrySet().removeIf(entry -> entry.getValue().getPrice() < priceThreshold);
    }

    // Count books in a category using Stream API
    public long countBooksInCategory(BookCategory category) {
        return books.values().stream()
                .filter(book -> book.getCategory() == category)
                .count();
    }
}

