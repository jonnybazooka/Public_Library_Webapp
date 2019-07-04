package org.tgieralt.servlets;

import org.tgieralt.datasource.Datasource;
import org.tgieralt.models.Book;
import org.tgieralt.models.dao.BookDAO;
import org.tgieralt.models.dao.impl.BookDAOimpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class LibraryDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDAO bookDAO = new BookDAOimpl();
        List<Book> books = bookDAO.getBooksFromDB();
        getServletContext().setAttribute("books", books);

        RequestDispatcher dispatcher = req.getRequestDispatcher("libraryDB.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = Datasource.getConnection()){
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
        }
    }
}
