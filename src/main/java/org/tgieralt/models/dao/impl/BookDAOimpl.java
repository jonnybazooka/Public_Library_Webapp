package org.tgieralt.models.dao.impl;

import org.tgieralt.datasource.Datasource;
import org.tgieralt.models.Book;
import org.tgieralt.models.dao.BookDAO;
import org.tgieralt.models.factories.BookFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAOimpl implements BookDAO {
    @Override
    public List<Book> getBooksFromDB() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = Datasource.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                books.add(new Book(title, author, isbn));
            }
            connection.close();
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            return BookFactory.getMyLibrary();
        }
    }
}
