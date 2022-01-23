package com.schwarz.onlinelibrary.service;

import com.schwarz.onlinelibrary.entities.BookEntity;
import com.schwarz.onlinelibrary.entities.CustomerEntity;
import com.schwarz.onlinelibrary.exception.InsufficientDepositException;
import com.schwarz.onlinelibrary.model.BookDto;
import com.schwarz.onlinelibrary.repository.Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private Repository<BookEntity> bookRepository;

    @Autowired
    private Repository<CustomerEntity> customerRepository;

    @Autowired
    private ModelMapper bookMapper;

    @Override
    public BookDto saveBook(BookDto book) {
        BookEntity bookEntity = toEntity(book);
        return toDto(bookRepository.save(bookEntity));
    }

    @Override
    public BookDto updateBook(BookDto book) {
        BookEntity bookEntity = toEntity(book);
        BookEntity newBook = bookRepository.update(bookEntity);
        updateBookReferences(newBook);
        return toDto(newBook);
    }

    @Override
    public BookDto deleteBook(Long id) {
        BookEntity removedBook = bookRepository.delete(id);
        removeBookReferences(removedBook);
        return toDto(removedBook);
    }

    /*
     * These 2 methods for updating references are questionable
     * as we may or we may not want to keep references of sold books.
     * This comment was added for the purpose of the task evaluation, not as a clean code concept.
     */
    private void removeBookReferences(BookEntity removedBook) {
        customerRepository.findAll()
                .forEach(customerEntity -> customerEntity.removeBook(removedBook));
    }

    /*
     * These 2 methods for updating references are questionable
     * as we may or we may not want to keep references of sold books.
     * This comment was added for the purpose of the task evaluation, not as a clean code concept.
     */
    private void updateBookReferences(BookEntity updatedBook) {
        customerRepository.findAll()
                .forEach(customerEntity -> customerEntity.updateBook(updatedBook));
    }

    @Override
    public BookDto findBook(Long id) {
        return toDto(bookRepository.findById(id));
    }

    @Override
    public Set<BookDto> findAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<BookDto> buyBook(Long customerId, Long bookId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId);
        BookEntity bookEntity = bookRepository.findById(bookId);
        if (customerEntity.getDeposit() < bookEntity.getPrice()) {
            throw new InsufficientDepositException("Not enough money in the deposit to but the book");
        }
        customerEntity.buyBook(bookEntity);
        customerRepository.update(customerEntity);
        return customerEntity.getPurchasedBooks().stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    private BookDto toDto(BookEntity bookEntity) {
        return bookMapper.map(bookEntity, BookDto.class);
    }

    private BookEntity toEntity(BookDto bookDto) {
        return bookMapper.map(bookDto, BookEntity.class);
    }
}
