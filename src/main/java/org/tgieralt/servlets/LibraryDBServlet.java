package org.tgieralt.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.tgieralt.models.Book;
import org.tgieralt.models.factories.BookFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDBServlet extends HttpServlet {

    private String configFile = "db.properties";
    private HikariConfig config = new HikariConfig(configFile);
    private DataSource dataSource = new HikariDataSource(config);

    private Connection getConnection() throws SQLException {
        /*String dbUrl = System.getenv("JDBC_DATABASE_URL");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setMaximumPoolSize(1);
        dataSource = new HikariDataSource(config);*/
        return dataSource.getConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = getBooksFromDB();
        getServletContext().setAttribute("books", books);

        RequestDispatcher dispatcher = req.getRequestDispatcher("libraryDB.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        try {
            connection = getConnection();
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String isbn = req.getParameter("isbn");
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS books (title VARCHAR(30), author VARCHAR(30), isbn VARCHAR(13))");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, isbn);
            preparedStatement.executeUpdate();
            RequestDispatcher dispatcher = req.getRequestDispatcher("library.jsp");
            statement.close();
            preparedStatement.close();
            connection.close();
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Book> getBooksFromDB() {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
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
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
