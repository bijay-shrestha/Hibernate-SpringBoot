package com.bookstore.controller;

import com.bookstore.service.BookstoreService;
import java.util.Collections;
import com.bookstore.entity.Author;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    // Open Session In View will "force" lazy initialization of books
    @RequestMapping("/fetchlazy")
    public Author authorWithBooksLazyInitialized() {

        Author author = bookstoreService.fetchAuthor();

        return author;
    }

    // Open Session In View will NOT "force" lazy initialization of books
    @RequestMapping("/fetchnolazy")
    public Author authorWithoutBooks() {

        Author author = bookstoreService.fetchAuthor();

        // explicitly set Books of the Author
        // in order to avoid fetching them from the database
        author.setBooks(Collections.emptyList());

        return author;
    }
}
