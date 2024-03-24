package bkstr.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bkstr.bookstore.domain.Book;
import bkstr.bookstore.domain.BookRepository;
import bkstr.bookstore.domain.Category;
import bkstr.bookstore.domain.CategoryRepository;
import bkstr.bookstore.domain.User;
import bkstr.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
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


			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user.user@email.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin.admin@email.com", "ADMIN");
			User user3 = new User("mio", "$2y$10$t7S8Sooj7pg3SmtcIf2pvOWRo1WDlzEK4LzkTu/TwVuUYik9xjxT6", "mio.kantola@email.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
