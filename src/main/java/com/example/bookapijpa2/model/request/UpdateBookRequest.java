package com.example.bookapijpa2.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {
    private String name;

    private String author;

    private String genre;

    private Integer page;
}
