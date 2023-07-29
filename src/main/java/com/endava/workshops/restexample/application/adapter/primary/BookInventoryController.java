package com.endava.workshops.restexample.application.adapter.primary;

import java.util.Collection;
import java.util.Optional;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.endava.workshops.restexample.application.adapter.primary.model.BookInputDto;
import com.endava.workshops.restexample.application.adapter.primary.model.BookOutputDto;
import com.endava.workshops.restexample.application.adapter.primary.model.BookQueryCriteriaDto;
import com.endava.workshops.restexample.application.model.Book;
import com.endava.workshops.restexample.application.model.BookQueryCriteria;
import com.endava.workshops.restexample.application.service.BookInventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookInventoryController {
    private final BookInventoryService service;
    private final ConversionService conversionService;

    public BookInventoryController(
            BookInventoryService service,
            ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<?> insertBook(@Valid @RequestBody BookInputDto book) {
        return Optional.of(book)
            .map(value -> conversionService.convert(book, Book.class))
            .map(this.service::add)
            .map(result -> ResponseEntity.created(ResponseUtils.getLocationUri(result.getId())).build())
            .orElseThrow();
    }

    @GetMapping
    public ResponseEntity<? extends Collection<BookOutputDto>> getBooks(BookQueryCriteriaDto criteria) {
        return Optional.of(criteria)
                .map(value -> conversionService.convert(criteria, BookQueryCriteria.class))
                .map(this.service::find)
                .map(result -> result.stream()
                        .map(value -> conversionService.convert(value, BookOutputDto.class))
                        .toList())
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookOutputDto> getBook(@PathVariable("id") Integer bookId) {
        return Optional.of(bookId)
            .map(this.service::getById)
            .map(value -> conversionService.convert(value, BookOutputDto.class))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(
            @PathVariable("id") Integer bookId,
            @RequestBody BookInputDto updatedBook) {
        return Optional.of(updatedBook)
            .map(value -> conversionService.convert(updatedBook, Book.class))
            .map(value -> value.toBuilder().id(bookId).build())
            .map(this.service::update)
            .map(result -> ResponseEntity.created(ResponseUtils.replaceLocationUri(result.getId())).build())
            .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Integer bookId) {
        return Optional.of(bookId)
            .map(this.service::delete)
            .map(result -> ResponseEntity.noContent().build())
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
