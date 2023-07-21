package com.example.bookapijpa2.service;

import com.example.bookapijpa2.dao.entity.BookEntity;
import com.example.bookapijpa2.dao.repository.BookRepository;
import com.example.bookapijpa2.mapper.BookMapper;
import com.example.bookapijpa2.model.request.BookRequest;
import com.example.bookapijpa2.model.request.PatchBookRequest;
import com.example.bookapijpa2.model.request.UpdateBookRequest;
import com.example.bookapijpa2.model.response.BookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bookapijpa2.mapper.BookMapper.*;
import static com.example.bookapijpa2.model.enums.BookStatus.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookResponse getBookById(Long id) {
        var book = fetchBookIfExist(id);
        if (book.getStatus() != AVAILABLE) {
            return null;
        }
        return buildBookResponse(book);
    }

    public List<BookResponse> getBooks() {
        return bookRepository.findAll().stream().map(
                        BookMapper::buildBookResponse)
                .collect(Collectors.toList());


    }

    public void saveBook(BookRequest book) {
        bookRepository.save(buildBookEntity(book));
    }

    public void updateBook(Long id, UpdateBookRequest updateBookRequest) {
        var book = fetchBookIfExist(id);
        updateBookEntity(book, updateBookRequest);
        bookRepository.save(book);
    }

    public void partialUpdateBook(Long id, PatchBookRequest patchBookRequest) {
        var book = fetchBookIfExist(id);
        partialUpdateBookEntity(book, patchBookRequest);
        bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        var book = fetchBookIfExist(id);
        book.setStatus(SOLD_OUT);
        bookRepository.save(book);
    }

    private BookEntity fetchBookIfExist(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("BOOK_NOT_FOUND")
                );
    }
}
