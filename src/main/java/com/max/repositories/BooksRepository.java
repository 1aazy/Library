package com.max.repositories;

import com.max.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);

    List<Book> findByTitleStartingWith(String startWith);
}
