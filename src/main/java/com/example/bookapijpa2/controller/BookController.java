package com.example.bookapijpa2.controller;

import com.example.bookapijpa2.model.request.BookRequest;
import com.example.bookapijpa2.model.request.PatchBookRequest;
import com.example.bookapijpa2.model.request.UpdateBookRequest;
import com.example.bookapijpa2.model.response.BookResponse;
import com.example.bookapijpa2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController
@RequestMapping("v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveBook(@RequestBody BookRequest book) {
        bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateBook(@PathVariable Long id,
                           @RequestBody UpdateBookRequest bookRequest) {
        bookService.updateBook(id, bookRequest);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void partialUpdateBook(@PathVariable Long id,
                                  @RequestBody PatchBookRequest patchBookRequest) {
        bookService.partialUpdateBook(id, patchBookRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}


