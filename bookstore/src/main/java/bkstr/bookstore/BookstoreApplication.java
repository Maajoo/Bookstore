package bkstr.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bkstr.bookstore.domain.Book;
import bkstr.bookstore.domain.BookRepository;
import bkstr.bookstore.domain.Category;
import bkstr.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {

			crepository.save(new Category ("Fantasy"));
			crepository.save(new Category ("Thriller"));
			crepository.save(new Category ("Romance"));
			crepository.save(new Category ("Action & Adventure"));
			crepository.save(new Category ("Science Fiction"));

			Book book1 = new Book("Muumipappa ja meri", "Tove Jansson", "1965", "9780374350321", "15,95€");
			Book book2 = new Book("Kapteeni Sinikarhun 13 1/2 elämää", "Walter Moers ", "2006", "9783813505726", "25,00€");
			repository.save(book1);
			repository.save(book2);

		};
	}
}
