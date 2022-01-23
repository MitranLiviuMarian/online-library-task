package com.schwarz.onlinelibrary.service;

import com.schwarz.onlinelibrary.model.BookDto;
import com.schwarz.onlinelibrary.model.CustomerDto;

import java.util.Set;

public interface BookService {
    
    /**
     * Saves a book.
     * @param book submitted book
     * @return the new created book
     */
    BookDto saveBook(BookDto book);

    /**
     * Upates a book if exists.
     * @param book updated book
     * @return the updated book
     */
    BookDto updateBook(BookDto book);

    /**
     * Deletes a book by id.
     * @param id of the book
     * @return deleted book
     */
    BookDto deleteBook(Long id);

    /**
     * Finds a book by id.
     * @param id id of the book
     * @return found book
     */
    BookDto findBook(Long id);

    /**
     * Finds all books available.
     * @return a set of all books
     */
    Set<BookDto> findAllBooks();

    /**
     * Adds a new book to the customer purchased books.
     * If the price is higher than the customer budget it will throw an exception.
     * @param bookId id of the book that will be bought
     * @param customerId id of the customer who will buy the book
     * @return the updated customer book list
     */
    Set<BookDto> buyBook(Long customerId, Long bookId);

}
