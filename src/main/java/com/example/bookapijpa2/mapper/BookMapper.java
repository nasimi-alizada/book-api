package com.example.bookapijpa2.mapper;

import com.example.bookapijpa2.dao.entity.BookEntity;
import com.example.bookapijpa2.model.enums.BookStatus;
import com.example.bookapijpa2.model.request.BookRequest;
import com.example.bookapijpa2.model.request.PatchBookRequest;
import com.example.bookapijpa2.model.request.UpdateBookRequest;
import com.example.bookapijpa2.model.response.BookResponse;

import static com.example.bookapijpa2.model.enums.BookStatus.AVAILABLE;

public class BookMapper {
    public static BookResponse buildBookResponse(BookEntity book) {
        return BookResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .page(book.getPage())
                .build();
    }

    public static BookEntity buildBookEntity(BookRequest book) {
        return BookEntity.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .page(book.getPage())
                .status(AVAILABLE)
                .build();

    }

    public static void updateBookEntity(BookEntity bookEntity, UpdateBookRequest bookRequest) {
        bookEntity.setName(bookRequest.getName());
        bookEntity.setAuthor(bookRequest.getAuthor());
        bookEntity.setGenre(bookRequest.getGenre());
        bookEntity.setPage(bookEntity.getPage());

    }

    public static void partialUpdateBookEntity(BookEntity bookEntity,
                                         PatchBookRequest patchBookRequest) {
        bookEntity.setPage(patchBookRequest.getPage());
    }
}
