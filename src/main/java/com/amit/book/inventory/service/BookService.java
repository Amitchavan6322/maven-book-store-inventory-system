package com.amit.book.inventory.service;

import com.amit.book.inventory.exception.InvalidBookIDException;
import com.amit.book.inventory.exception.InvalidBookNameException;
import com.amit.book.inventory.exception.InvalidBookPriceException;
import com.amit.book.inventory.model.Book;
import com.amit.book.inventory.model.BookCategory;
import com.amit.book.inventory.repository.BookRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;


public class BookService extends LibraryService implements BookServiceInterface {

    private HashMap<Integer, Book> books = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private static final BookRepository bookRepository = new BookRepository();

    public void acceptingBookInfo() throws InvalidBookIDException, InvalidBookNameException, InvalidBookPriceException, SQLException {

        if (isBookTableEmpty()) {
            boolean value = bookRepository.isTableEmpty();
            if (value) System.out.println("No books currently in the inventory.");
        }

        Book book = new Book();
        System.out.println("Enter book id");
        int bookId;
        try {
            bookId = Integer.parseInt(scanner.nextLine());
            book.setBookId(bookId);
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
        //books.put(bookId, book);
        boolean isBookAdded = bookRepository.fillTheBookInfo(book);
        System.out.println(isBookAdded ? "Book entry added in DB" : "Failed to add book entry in DB");
    }


    public void displayBookInfo() throws SQLException {
        bookRepository.displayTheBookInfo();
    }

    // method to retrieve book by id
    public Book getBookById(int book_Id) throws SQLException {
        bookRepository.getBookById(book_Id);
        return null;
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

    @Override
    public void updateBookPrice(int bookId, int newPrice) {

    }

    public void updateBookInfo(int book_Id) throws InvalidBookNameException, SQLException, InvalidBookIDException, InvalidBookPriceException {
        Book book = new Book();
        book.setBookId(book_Id);

        System.out.println("Enter new book name (leave empty to keep the existing value as is)");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            book.setName(name);
        }

        System.out.println("Enter new book author (leave empty to keep the existing value as is)");
        String author = scanner.nextLine();
        if (!author.isEmpty()) {
            book.setAuthor(author);
        }

        System.out.println("Enter new book publisher (leave empty to keep the existing value as is)");
        String publisher = scanner.nextLine();
        if (!publisher.isEmpty()) {
            book.setPublisher(publisher);
        }

        System.out.println("Enter new no of book copies (leave empty to keep the existing value as is)");
        String copiesInput = scanner.nextLine();
        if (!copiesInput.isEmpty()) {
            try {
                int noOfCopies = Integer.parseInt(copiesInput);
                book.setNoOfCopies(noOfCopies);
            } catch (NumberFormatException e) {
                throw new InvalidBookIDException("Please provide the number of copies in a valid format.");
            }
        }

        System.out.println("Enter new book category (leave empty to keep the existing value as is)");
        String categoryInput = scanner.nextLine();
        if (!categoryInput.isEmpty()) {
            try {
                BookCategory category = BookCategory.valueOf(categoryInput.toUpperCase());
                book.setCategory(category);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please enter a valid category.");
                return;
            }
        }

        System.out.println("Enter new store location (leave empty to keep the existing value as is)");
        String storeLocation = scanner.nextLine();
        if (!storeLocation.isEmpty()) {
            book.setStoreLocation(storeLocation);
        }

        System.out.println("Enter new book price (leave empty to keep the existing value as is)");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            try {
                int price = Integer.parseInt(priceInput);
                book.setPrice(price);
            } catch (NumberFormatException e) {
                throw new InvalidBookPriceException("Invalid book price. Please provide a valid price.");
            }
        }
        boolean isUpdated = bookRepository.updateBookInfo(book);
        System.out.println(isUpdated ? "Book info updated successfully." : "Failed to update book info.");
    }

    // Method to check if the collection is empty
    public boolean isBookTableEmpty() {
        return books.isEmpty();
    }

    public boolean isBookExist(int bookId) throws SQLException {
        return bookRepository.isBookExists(bookId);
    }
}

