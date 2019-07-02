package org.tgieralt.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.tgieralt.models.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibraryDBServlet extends HttpServlet {

    private DataSource dataSource;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        dataSource = new HikariDataSource(config);
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                books.add(new Book(title, author, isbn));
            }
            getServletContext().setAttribute("books", books);
            RequestDispatcher dispatcher = req.getRequestDispatcher("libraryDB.jsp");
            statement.close();
            connection.close();
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS books (title VARCHAR(30), author VARCHAR(30), isbn VARCHAR(15))");
            statement.executeUpdate("INSERT INTO books VALUES ('" + title + "', '" + author + "', " + isbn + ")");
            RequestDispatcher dispatcher = req.getRequestDispatcher("library.jsp");
            statement.close();
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
}
