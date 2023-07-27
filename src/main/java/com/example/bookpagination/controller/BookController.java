package com.example.bookpagination.controller;

import com.example.bookpagination.model.criteria.BookCriteria;
import com.example.bookpagination.model.criteria.PageCriteria;
import com.example.bookpagination.model.request.BookRequest;
import com.example.bookpagination.model.request.PatchBookRequest;
import com.example.bookpagination.model.request.UpdateBookRequest;
import com.example.bookpagination.model.response.BookResponse;
import com.example.bookpagination.model.response.PageableBookResponse;
import com.example.bookpagination.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public PageableBookResponse getBooks(PageCriteria pageCriteria, BookCriteria bookCriteria) {
       return bookService.getAllBook(pageCriteria, bookCriteria);

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


