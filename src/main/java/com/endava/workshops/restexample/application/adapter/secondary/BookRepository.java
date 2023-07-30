package com.endava.workshops.restexample.application.adapter.secondary;

import java.util.Collection;
import java.util.Optional;

import com.endava.workshops.restexample.application.model.Book;

public interface BookRepository {    
  Book save(Book book);
  void delete(Book book);
  boolean existsById(Integer id);
  Optional<Book> findById(Integer id);
  Collection<Book> findAllByCriteria(
        String title, 
        String author, 
        String publisher,
        Integer yearPublished);
}
