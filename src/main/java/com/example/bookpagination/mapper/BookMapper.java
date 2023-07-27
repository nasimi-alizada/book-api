package com.example.bookpagination.mapper;

import com.example.bookpagination.dao.entity.BookEntity;
import com.example.bookpagination.model.request.BookRequest;
import com.example.bookpagination.model.request.PatchBookRequest;
import com.example.bookpagination.model.request.UpdateBookRequest;
import com.example.bookpagination.model.response.BookResponse;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bookpagination.model.enums.BookStatus.AVAILABLE;

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


    public static List<BookResponse> mapToBookResponseList(List<BookEntity> bookEntities) {
        return bookEntities.stream()
                .map(BookMapper::buildBookResponse)
                .collect(Collectors.toList());
    }


}
