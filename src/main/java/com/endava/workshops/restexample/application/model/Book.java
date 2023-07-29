package com.endava.workshops.restexample.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(schema = "library", name = "book")
public class Book {
    @Id
    private Integer id;
    @NotNull(message = "Title should not be null")
    private String title;
    @NotNull(message = "Author should not be null")
    private String author;
    @NotNull(message = "Publisher should not be null")
    private String publisher;
    @Column("year_published")
    private Integer yearPublished;
}
