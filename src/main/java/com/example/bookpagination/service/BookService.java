package com.example.bookpagination.service;

import com.example.bookpagination.dao.entity.BookEntity;
import com.example.bookpagination.dao.repository.BookRepository;
import com.example.bookpagination.mapper.BookMapper;
import com.example.bookpagination.model.criteria.BookCriteria;
import com.example.bookpagination.model.criteria.PageCriteria;
import com.example.bookpagination.model.request.BookRequest;
import com.example.bookpagination.model.request.PatchBookRequest;
import com.example.bookpagination.model.request.UpdateBookRequest;
import com.example.bookpagination.model.response.BookResponse;
import com.example.bookpagination.model.response.PageableBookResponse;
import com.example.bookpagination.service.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bookpagination.mapper.BookMapper.*;
import static com.example.bookpagination.model.enums.BookStatus.*;

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

    public PageableBookResponse getAllBook(PageCriteria pageCriteria, BookCriteria bookCriteria) {
        var booksPage = bookRepository.findAll(
                new BookSpecification(bookCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount())
        );

        return mapToPageableResponse(booksPage);
    }


    private BookEntity fetchBookIfExist(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("BOOK_NOT_FOUND")
                );
    }

    private PageableBookResponse mapToPageableResponse(Page<BookEntity> booksPage) {
        return PageableBookResponse.builder()
                .users(booksPage.getContent().stream().map(BookMapper::buildBookResponse).collect(Collectors.toList()))
                .hasNextPage(booksPage.hasNext())
                .lastPageNumber(booksPage.getTotalPages())
                .totalElements(booksPage.getTotalElements())
                .build();

    }

}
