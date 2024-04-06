package bkstr.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bkstr.bookstore.domain.Book;
import bkstr.bookstore.domain.BookRepository;
import bkstr.bookstore.domain.Category;
import bkstr.bookstore.domain.CategoryRepository;

@SpringBootTest
class BookJPAtest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testSaveBook() {
        // SAMPLE CATEGORY
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        // SAMPLE BOOK
        Book book = new Book("Test Book", "Test Author", "9999", "1234567890", "10");
        book.setCategory(category);

        // SAVE BOOK
        Book savedBook = bookRepository.save(book);

        // VERIFY
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
    }

    @Test
    public void testDeleteBook() {
        // SAMPLE CATEGORY
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        // SAMPLE BOOK
        Book book = new Book("Test Book", "Test Author", "9999", "1234567890", "10");
        book.setCategory(category);
        bookRepository.save(book);

        // DELETE BOOK
        bookRepository.deleteById(book.getId());

        // VERIFY
        Optional<Book> deletedBook = bookRepository.findById(book.getId());
        assertThat(deletedBook).isEmpty();
    }
}