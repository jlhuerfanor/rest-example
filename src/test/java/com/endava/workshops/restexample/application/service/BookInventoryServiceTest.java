package com.endava.workshops.restexample.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.endava.workshops.restexample.application.adapter.secondary.BookRepository;
import com.endava.workshops.restexample.application.exceptions.InvalidInputException;
import com.endava.workshops.restexample.application.model.Book;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class BookInventoryServiceTest {
    private static final Faker faker = new Faker();
    private static final Map<String, Book> invalidInputCases = Map.ofEntries(
        Map.entry(BookInventoryService.ERROR_ID_SHOULD_NOT_BE_PROVIDED, 
                Book.builder()
                    .id(ThreadLocalRandom.current().nextInt())
                    .title(faker.book().title())
                    .author(faker.book().author())
                    .publisher(faker.book().publisher())
                    .build())
    );

    @Mock
    private BookRepository repository;

    private BookInventoryService service;

    @BeforeEach
    void setup() {
        service = new BookInventoryService(repository);
    }

    @Test
    void given_invalidBookInput_when_add_then_throwInvalidInputException() {
        for(var entry : invalidInputCases.entrySet()) {
            var exception = assertThrows(InvalidInputException.class, 
                    () -> this.service.add(entry.getValue()));

            assertEquals(entry.getKey(), exception.getMessage());
        }
    }

    @Test
    void given_validBookInput_when_add_then_bookGetsPersistedInRepository() {
        var validBook = Book
                .builder()
                .title(faker.book().title())
                .author(faker.book().author())
                .publisher(faker.book().publisher())
                .build();

        doReturn(validBook)
            .when(repository)
            .save(validBook);

        assertEquals(validBook, service.add(validBook));
    }
}
