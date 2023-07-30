package com.endava.workshops.restexample.application.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
    private String title;
    private String author;
    private String publisher;
    @Column("year_published")
    private Integer yearPublished;
}
