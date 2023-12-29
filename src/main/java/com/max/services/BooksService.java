package com.max.services;

import com.max.models.Book;
import com.max.models.Person;
import com.max.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> index() {
        return booksRepository.findAll();
    }

    public Optional<Book> getById(int id) {
        return booksRepository.findById(id);
    }

    public Person getBookOwner(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void release(int id) {
        Optional<Book> book = booksRepository.findById(id);

        book.ifPresent(value -> value.setOwner(null));
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        Optional<Book> book = booksRepository.findById(id);

        book.ifPresent(value -> value.setOwner(selectedPerson));
    }

    public Optional<Book> findByTitle(String title) {
        return booksRepository.findByTitle(title);
    }

    public List<Book> paginationIndex(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> sortByYearIndex() {
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> sortedPaginationIndex(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public List<Book> findByTitleStartingWith(String startWith) {
        return booksRepository.findByTitleStartingWith(startWith);
    }
}
