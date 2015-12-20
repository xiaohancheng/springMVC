package app04a.service;

import java.util.List;

import app04a.domain.Book;
import app04a.domain.Category;

public interface BookService {
    
    List<Category> getAllCategories();
    Category getCategory(int id);
    List<Book> getAllBooks();
    Book save(Book book);
    Book update(Book book);
    Book get(long id);
    long getNextId();
    boolean deleteBook(long id);
}
