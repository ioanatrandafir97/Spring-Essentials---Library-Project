package com.summerschool.library.service;

import com.summerschool.library.exception.InvalidISBNException;
import com.summerschool.library.model.domain.Book;
import com.summerschool.library.model.domain.Category;
import com.summerschool.library.model.dto.SortFieldDTO;
import com.summerschool.library.repository.BookRepository;
import org.apache.commons.validator.routines.ISBNValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.summerschool.library.exception.Constants.CATEGORY_NOT_FOUND;
import static java.util.Objects.isNull;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Book> getAll(String author, LocalDate published, String title, Integer page, SortFieldDTO sorted) {
        //TODO filter and sort by different fields
        if (StringUtils.hasText(author) && published != null) {
            return bookRepository.findByAuthorAndPublishedDateBefore(author, published);
        }
        if (StringUtils.hasText(author)) {
            return bookRepository.findByAuthor(author);
        }
        if (StringUtils.hasText(title)) {
            return bookRepository.getByTitleIgnoreCaseStartingWith(title);
        }
        if (page != null) {
            Pageable pageable = PageRequest.of(page, 3);
            return bookRepository.findAll(pageable).toList();
        }
        if (sorted != null) {
            return bookRepository.findAll(Sort.by(Sort.Direction.ASC, sorted.getName()));
        }
        return bookRepository.findAll();
    }

    public Optional<Book> get(Long id) {
        //TODO: Find book by id
        return bookRepository.findById(id);
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
        return bookRepository.save(book);
    }

    public Book update(Book book, Category category) {
        ISBNValidator isbnValidator = new ISBNValidator();
        if(!isbnValidator.isValid(book.getIsbn())) {
            throw new InvalidISBNException();
        }

        book.setCategory(category);

        //TODO: Update the book in the database
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        //TODO: Delete the book
        bookRepository.delete(book);
//        bookRepository.deleteById(book.getId());
    }

    public List<Book> getAvailableBooks() {
        //TODO: find all available books
        return bookRepository.findByAvailableCopiesGreaterThan(0);
    }

    public List<Book> getAllBooksByCategory(Long categoryId) {
        //TODO: find all books from the given category
        return bookRepository.findByCategoryIdOrderByTitleDesc(categoryId);
    }

    public List<Book> getAllBooksBorrowedByUser(Long userId) {
        //TODO: find all the books borrowed by the given user
        return bookRepository.queryBooksBorrowedByUser(userId);
    }

}
