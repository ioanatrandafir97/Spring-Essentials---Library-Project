package com.bootcamp.library.service;

import com.bootcamp.library.model.domain.Book;
import com.bootcamp.library.model.domain.Category;
import com.bootcamp.library.model.domain.User;
import com.bootcamp.library.repository.BookRepository;
import com.bootcamp.library.repository.BorrowRepository;
import com.bootcamp.library.repository.CategoryRepository;
import com.bootcamp.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    public void init() {
        deleteAll();

        List<User> users = saveUsers();
        List<Category> categories = saveCategories();

        List<Book> books = saveBooks(categories);
    }

    private void deleteAll() {
        borrowRepository.deleteAll();
        bookRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
    }

    private List<User> saveUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("Ioana");
        user1.setLastName("Trandafir");
        user1.setEmail("itrandafir@gmail.com");
        user1.setTitle("Mrs.");
        user1.setMobilePhone("+40772305435");
        users.add(user1);
        User user2 = new User();
        user2.setFirstName("Alex");
        user2.setLastName("Petrescu");
        user2.setEmail("anamoisescu@gmail.com");
        user2.setTitle("Mr.");
        user2.setMobilePhone("+407723535884");
        users.add(user2);
        return userRepository.saveAll(users);
    }

    private List<Category> saveCategories() {
        List<Category> categories = new ArrayList<>();
        Category thriller = new Category();
        thriller.setName("Thriller");
        thriller.setDescription("Thrillers are a genre of fiction in which tough, resourceful, but essentially ordinary heroes are pitted against villains determined to destroy them, their country, or the stability of the free world.");
        categories.add(thriller);

        Category romance = new Category();
        romance.setName("Romance");
        romance.setDescription("A romance book is a genre fiction which places its primary focus on the relationship and romantic love between two people, and usually has an emotionally satisfying and optimistic ending.");
        categories.add(romance);

        Category horror = new Category();
        horror.setName("Horror");
        horror.setDescription("Meant to cause discomfort and fear for both the character and readers, horror writers often make use of supernatural and paranormal elements in morbid stories that are sometimes a little too realistic.");
        categories.add(horror);
        return categoryRepository.saveAll(categories);
    }

    private List<Book> saveBooks(List<Category> categories) {
        List<Book> books = new ArrayList<>();

        Book book1 = new Book();
        book1.setTitle("Murder on the Orient Express");
        book1.setAuthor("Agatha Christie");
        book1.setPublisher("Grove Press");
        book1.setLanguage("English");
        book1.setPublishedDate(LocalDate.of(2015, 1, 1));
        book1.setPages(300);
        book1.setNrCopies(5);
        book1.setBorrowDays(6);
        book1.setIsbn("9780008268879");
        book1.setCategory(categories.get(0));
        books.add(book1);

        Book book2 = new Book();
        book2.setTitle("And Then There Were None");
        book2.setAuthor("Agatha Christie");
        book2.setPublisher("Harper");
        book2.setLanguage("English");
        book2.setPublishedDate(LocalDate.of(2011, 6, 30));
        book2.setPages(240);
        book2.setNrCopies(4);
        book2.setBorrowDays(5);
        book2.setIsbn("0062073486");
        book2.setCategory(categories.get(0));
        books.add(book2);

        Book book3 = new Book();
        book3.setTitle("Pride and Prejudice");
        book3.setAuthor("Jane Austen");
        book3.setPublisher("Wordsworth");
        book3.setLanguage("English");
        book3.setPublishedDate(LocalDate.of(1992, 6, 16));
        book3.setPages(352);
        book3.setNrCopies(4);
        book3.setBorrowDays(6);
        book3.setIsbn("9781853260001");
        book3.setCategory(categories.get(1));
        books.add(book3);

        Book book4 = new Book();
        book4.setTitle("Anna Karenina");
        book4.setAuthor("Lev Tolstoi");
        book4.setPublisher("Polirom");
        book4.setLanguage("English");
        book4.setPublishedDate(LocalDate.of(2016, 6, 12));
        book4.setPages(936);
        book4.setNrCopies(2);
        book4.setBorrowDays(10);
        book4.setIsbn("9789734627943");
        book4.setCategory(categories.get(1));
        books.add(book4);

        Book book5 = new Book();
        book5.setTitle("Bird Box: Orbeste");
        book5.setAuthor("Josh Malerman");
        book5.setPublisher("Corint");
        book5.setLanguage("Romanian");
        book5.setPublishedDate(LocalDate.of(2019, 9, 15));
        book5.setPages(352);
        book5.setNrCopies(4);
        book5.setBorrowDays(5);
        book5.setIsbn("9786067935646");
        book5.setCategory(categories.get(2));
        books.add(book5);

        return bookRepository.saveAll(books);
    }

}
