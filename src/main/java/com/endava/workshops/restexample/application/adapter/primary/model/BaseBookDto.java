package com.endava.workshops.restexample.application.adapter.primary.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseBookDto {
    private String title;
    private String author;
    private String publisher;
    private Integer yearPublished;    
}
