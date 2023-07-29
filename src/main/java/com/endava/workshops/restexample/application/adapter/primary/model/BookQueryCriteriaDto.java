package com.endava.workshops.restexample.application.adapter.primary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class BookQueryCriteriaDto {
    private String title;
    private String author;
    private String publisher;
    private Integer yearPublished;
}
