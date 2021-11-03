package com.bootcamp.library.service;

import com.bootcamp.library.exception.InvalidISBNException;
import com.bootcamp.library.model.domain.Book;
import com.bootcamp.library.model.domain.Category;
import com.bootcamp.library.repository.BookRepository;
import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bootcamp.library.exception.Constants.CATEGORY_NOT_FOUND;
import static java.util.Objects.isNull;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Book> getAll() {
        //TODO filter and sort by different fields
        return bookRepository.findAll();
    }

    public Optional<Book> get(Long id) {
        //TODO: Find book by id
        return Optional.empty();
    }

    public Book add(Long categoryId, Book book) {
        Category category = categoryService.get(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CATEGORY_NOT_FOUND));
        book.setCategory(category);
        ISBNValidator isbnValidator = new ISBNValidator();
        if(!isbnValidator.isValid(book.getIsbn())) {
            throw new InvalidISBNException();
        }

        if(isNull(book.getAvailableCopies())) {
            book.setAvailableCopies(book.getNrCopies());
        }
        //TODO: Save the book in the database
        return null;
    }

    public Book update(Book book, Category category) {
        ISBNValidator isbnValidator = new ISBNValidator();
        if(!isbnValidator.isValid(book.getIsbn())) {
            throw new InvalidISBNException();
        }

        book.setCategory(category);

        //TODO: Update the book in the database
        return null;
    }

    public void delete(Book book) {
        //TODO: Delete the book
    }

    public List<Book> getAvailableBooks() {
        //TODO: find all available books
        return new ArrayList<>();
    }

    public List<Book> getAllBooksByCategory(Long categoryId) {
        //TODO: find all books from the given category
        return new ArrayList<>();
    }

    public List<Book> getAllBooksBorrowedByUser(Long userId) {
        //TODO: find all the books borrowed by the given user
        return new ArrayList<>();
    }

}