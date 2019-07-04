package org.tgieralt.servlets;

import org.tgieralt.models.Book;
import org.tgieralt.models.dao.BookDAO;
import org.tgieralt.models.dao.impl.BookDAOimpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LibraryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDAO bookDAO = new BookDAOimpl();
        List<Book> myLibrary = bookDAO.getBooksFromDB();
        getServletContext().setAttribute("myLibrary", myLibrary);

        RequestDispatcher dispatcher = req.getRequestDispatcher("library.jsp");
        dispatcher.forward(req, resp);
    }
}
