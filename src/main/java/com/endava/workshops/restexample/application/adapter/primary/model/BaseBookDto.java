package com.endava.workshops.restexample.application.adapter.primary.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class BaseBookDto {
    @NotNull(message = "Title should not be null")
    private String title;
    @NotNull(message = "Author should not be null")
    private String author;
    @NotNull(message = "Publisher should not be null")
    private String publisher;
    private Integer yearPublished;    
}
