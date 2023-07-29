package com.endava.workshops.restexample.application.adapter.secondary;

import java.util.Collection;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.endava.workshops.restexample.application.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {    
    

    @Query(value = "select b.* from library.book b where "
            + "(:title is null or b.title like concat('%', :title, '%'))"
            + " and (:author is null or b.author like concat('%', :author, '%'))"
            + " and (:publisher is null or b.publisher like concat('%', :publisher, '%'))"
            + " and (:yearPublished is null or b.year_published = :yearPublished)")
    Collection<Book> findAllByCriteria(
        @Param("title") String title,
        @Param("author") String author,
        @Param("publisher") String publisher,
        @Param("yearPublished") Integer yearPublished);
}
