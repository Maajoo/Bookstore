package bkstr.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bkstr.bookstore.domain.Book;
import bkstr.bookstore.domain.BookRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @RequestMapping("/booklist")
    public String giveBooklist(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // DELETE
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    // ADD
    @RequestMapping(value = "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    // SAVE
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    // EDIT
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        Book book = repository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + bookId));

        model.addAttribute("book", book);
        return "editbook";
    }

    @RequestMapping("/index")
    public String giveEtusivu() {
        return "Bookstore";
    }
}
