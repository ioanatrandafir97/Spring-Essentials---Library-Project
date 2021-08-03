package com.summerschool.library.service;

import com.summerschool.library.model.domain.Book;
import com.summerschool.library.model.domain.Category;
import com.summerschool.library.model.dto.SortFieldDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAll(String author, String publisher, String title, String language, LocalDate published, Boolean available, Integer page, SortFieldDTO sorted);
    Optional<Book> get(Long id);
    Book add(Long categoryId, Book book);
    Book update(Book book, Category category);
    void delete(Book book);
    List<Book> getAllBooksBorrowedByUser(Long userId);
    List<Book> getAvailableBooks();
    List<Book> getAllBooksByCategory(Long categoryId);
}
