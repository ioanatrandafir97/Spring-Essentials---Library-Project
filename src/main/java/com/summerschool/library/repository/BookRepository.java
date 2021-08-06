package com.summerschool.library.repository;

import com.summerschool.library.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //TODO: Simple Derived Query (find books by author)
    List<Book> findByAuthor(String author);

    //TODO: Derived Query with multiple clauses (find books by author and published date before a given date)
    List<Book> findByAuthorAndPublishedDateBefore(String author, LocalDate publishedDate);

    //TODO: Derived Query with operators for String (find books by title starting with a given string)
    List<Book> getByTitleIgnoreCaseStartingWith(String prefix);

    //TODO: Derived Query with comparison condition (find available books)
    List<Book> findByAvailableCopiesGreaterThan(Integer copies);

    //TODO: Derived Query with sorted results (find books by category ordered by the title)
    List<Book> findByCategoryIdOrderByTitleDesc(Long categoryId);

    //TODO: JPQL Query (find books by language)
    @Query(value = "Select b From Book b Where b.language = :language")
    List<Book> queryBooksWrittenInLanguage(String language);

    //TODO: JPQL Query (find all available books by author)
    @Query(value = "Select b From Book b Where b.availableCopies > 0 And b.author like %?1%")
    List<Book> queryAllAvailableBooksWrittenBy(String author);

    //TODO: Native Query (find all books by publisher)
    @Query(nativeQuery = true, value = "Select * From books Where publisher = :publisher")
    List<Book> queryAllBooksPublishedBy(String publisher);

    //TODO: Native query (find all books borrowed by a user)
    @Query(nativeQuery = true, value = "Select * From books Where id IN (Select book_id From borrows Where user_id = :userId)")
    List<Book> queryBooksBorrowedByUser(Long userId);
}

