package com.amit.book.inventory.repository;

import com.amit.book.inventory.model.Book;
import com.amit.book.inventory.service.ConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BookRepository {

    private static Connection connection =null;

  private void initConnection() throws SQLException {
      if(connection == null || connection.isClosed()){
          connection = new ConnectionService().getConnection();
      }
  }

  public boolean fillTheBookInfo(Book book) throws SQLException {
      this.initConnection();
      String query = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
          preparedStatement.setInt(1, book.getBookId());
          preparedStatement.setString(2, book.getName());
          preparedStatement.setString(3, book.getAuthor());
          preparedStatement.setString(4, book.getPublisher());
          preparedStatement.setInt(5, book.getNoOfCopies());
          preparedStatement.setString(6, String.valueOf(book.getCategory()));
          preparedStatement.setString(7, book.getStoreLocation());
          preparedStatement.setInt(8, book.getPrice());

          int rowsInserted = preparedStatement.executeUpdate();

          return rowsInserted > 0;

      } catch (SQLException e) {
          e.printStackTrace();
      }
return false;
  }

}



