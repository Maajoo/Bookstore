package bkstr.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bkstr.bookstore.domain.Book;
import bkstr.bookstore.domain.BookRepository;
import bkstr.bookstore.domain.Category;
import bkstr.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;
    

    @RequestMapping("/booklist")
    public String giveBooklist(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // LOGIN
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    // DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    // ADD
    @RequestMapping(value = "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }

    // SAVE
    @SuppressWarnings("null")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    // EDIT
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        @SuppressWarnings("null")
        Book book = repository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + bookId));

        model.addAttribute("book", book);
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }

    //RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

    //RESTful service to get a book by id
    @SuppressWarnings("null")
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return repository.findById(bookId);
    }

    // ETUSIVU
    @RequestMapping("/index")
    public String giveEtusivu() {
        return "Bookstore";
    }

}
