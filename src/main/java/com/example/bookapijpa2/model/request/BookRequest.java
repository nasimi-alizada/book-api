package com.example.bookapijpa2.model.request;

import com.example.bookapijpa2.model.enums.BookStatus;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private String name;

    private String author;

    private String genre;

    private Integer page;


}
