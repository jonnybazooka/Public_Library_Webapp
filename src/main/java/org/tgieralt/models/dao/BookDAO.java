package org.tgieralt.models.dao;

import org.tgieralt.models.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBooksFromDB();
}
