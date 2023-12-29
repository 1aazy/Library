package com.max.controllers;

import com.max.models.Book;
import com.max.models.Person;
import com.max.services.BooksService;
import com.max.services.PeopleService;
import com.max.util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }


    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "page", required = false)Integer page,
                        @RequestParam(value = "books_per_page", required = false)Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false)Boolean sortByYear) {

        if (page != null && booksPerPage != null && sortByYear != null) {
            model.addAttribute("books", booksService.sortedPaginationIndex(page, booksPerPage));
        } else
        if (page != null && booksPerPage != null) {
            model.addAttribute("books", booksService.paginationIndex(page, booksPerPage));
        } else if (sortByYear != null) {
            model.addAttribute("books", booksService.sortByYearIndex());
        } else {
            model.addAttribute("books", booksService.index());
        }

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {

        model.addAttribute("book", booksService.getById(id).get());

        Person owner = booksService.getBookOwner(id);

        if (owner != null)
            model.addAttribute("owner", owner);
        else
            model.addAttribute("people", peopleService.index());

        return "books/show";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id) {
        model.addAttribute("book", booksService.getById(id).get());
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@Valid @ModelAttribute("book") Book book,
                       BindingResult bindingResult,
                       @PathVariable("id") int id) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        booksService.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String search() {
        return "books/search";
    }

    @PostMapping("/search")
    public String searchByTitle(Model model,
                                @RequestParam("startWith") String startWith) {
        model.addAttribute("books", booksService.findByTitleStartingWith(startWith));
        return "books/search";
    }
}
