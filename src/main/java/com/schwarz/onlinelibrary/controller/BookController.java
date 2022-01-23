package com.schwarz.onlinelibrary.controller;

import com.schwarz.onlinelibrary.model.BookDto;
import com.schwarz.onlinelibrary.model.BuyRequestDto;
import com.schwarz.onlinelibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = "application/json")
    public Set<BookDto> getBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findBook(id);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public BookDto deleteBookById(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    @PostMapping(produces = "application/json")
    public BookDto createBook(@RequestBody @Valid BookDto bookDto) {
        return bookService.saveBook(bookDto);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public BookDto updateBook(@RequestBody @Valid BookDto bookDto, @PathVariable Long id) {
        bookDto.setId(id);
        return bookService.updateBook(bookDto);
    }

    @PostMapping(path = "/{id}/buy", produces = "application/json")
    public Set<BookDto> buyBook(@RequestBody @Valid BuyRequestDto buyRequest, @PathVariable Long id) {
        return bookService.buyBook(buyRequest.getCustomerId(), id);
    }
}
