package com.schwarz.onlinelibrary.repository;

import com.schwarz.onlinelibrary.entities.BookEntity;
import com.schwarz.onlinelibrary.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@org.springframework.stereotype.Repository
public class BookRepository implements Repository<BookEntity> {
    private static final Set<BookEntity> bookStore = new LinkedHashSet<>();

    @Autowired
    private IdGenerator bookIdGenerator;

    @Override
    public Set<BookEntity> findAll() {
        return bookStore;
    }

    @Override
    public BookEntity findById(Long id) {
        return bookStore.stream()
                .filter(book -> Objects.equals(book.getId(), id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Book with id: " + id + " not found"));
    }

    @Override
    public BookEntity delete(Long id) {
        BookEntity existingBook = findById(id);
        bookStore.remove(existingBook);
        return existingBook;
    }

    @Override
    public BookEntity save(BookEntity entity) {
        Long newId = bookIdGenerator.generateId(bookStore);
        entity.setId(newId);
        bookStore.add(entity);
        return entity;
    }

    @Override
    public BookEntity update(BookEntity entity) {
        BookEntity existingEntity = findById(entity.getId());
        bookStore.remove(existingEntity);
        bookStore.add(entity);
        return entity;
    }
}
