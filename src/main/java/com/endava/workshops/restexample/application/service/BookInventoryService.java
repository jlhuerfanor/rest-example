package com.endava.workshops.restexample.application.service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.hglteam.validation.Validation;
import org.hglteam.validation.ValidationError;
import org.springframework.stereotype.Service;

import com.endava.workshops.restexample.application.adapter.secondary.BookRepository;
import com.endava.workshops.restexample.application.exceptions.InvalidInputException;
import com.endava.workshops.restexample.application.model.Book;
import com.endava.workshops.restexample.application.model.BookQueryCriteria;

@Service
public class BookInventoryService {
    public static final String ERROR_ID_SHOULD_NOT_BE_PROVIDED = "ID shoud not be provided!";
    
    private final BookRepository repository;

    public BookInventoryService(BookRepository repository) {
        this.repository = repository;
    }

    public Book add(Book book) {
        return Optional.of(book)
            .map(Validation.<Book>builder()
                .onProperty(Book::getId, name -> name
                        .when(Objects::nonNull)
                        .then(ValidationError.withMessage(InvalidInputException::new, ERROR_ID_SHOULD_NOT_BE_PROVIDED)))
                ::valid)
            .map(repository::save)
            .orElseThrow();
    }

    public Book getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Book update(Book input) {
        if(!repository.existsById(input.getId())) {
            return add(input.toBuilder().id(null).build());
        } else {
            repository.save(input);
            return null;
        }
    }

    public Book delete(Integer id) {
        return repository.findById(id)
                .map(book -> {
                    repository.delete(book);
                    return book;
                })
                .orElse(null);
    }

    public Collection<Book> find(BookQueryCriteria criteria) {
        return repository.findAllByCriteria(
            criteria.getTitle(),
            criteria.getAuthor(),
            criteria.getPublisher(),
            criteria.getYearPublished());
    }
}
