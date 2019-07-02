package org.tgieralt.models.factories;

import org.tgieralt.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookFactory {
    public List<Book> getMyLibrary() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Game of Thrones", "G.R.R.Martin", 9780553103540L));
        books.add(new Book("Dune", "Frank Herbert", 9780441172719L));
        books.add(new Book("Children of Dune", "Frank Herbert", 9780593098240L));
        books.add(new Book("Treasure Island", "Robert Louis Stevenson", 9784909069023L));
        books.add(new Book("Edge of Darkness", "Christine Feehan", 9780515156218L));
        books.add(new Book("Heart of Darkness", "Joseph Conrad", 9781717294067L));
        return books;
    }
}
