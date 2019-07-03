package org.tgieralt.servlets;

import org.tgieralt.models.Book;
import org.tgieralt.models.factories.BookFactory;

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
        List<Book> myLibrary = BookFactory.getMyLibrary();
        getServletContext().setAttribute("myLibrary", myLibrary);

        RequestDispatcher dispatcher = req.getRequestDispatcher("library.jsp");
        dispatcher.forward(req, resp);
    }
}
