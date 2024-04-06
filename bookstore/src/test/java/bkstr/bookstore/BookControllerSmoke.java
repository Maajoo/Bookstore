package bkstr.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bkstr.bookstore.web.BookController;

@SpringBootTest
class BookControllerSmoke {

	@Autowired 
	private BookController bcontroller;

	@Test
	void contextLoads() {
		assertThat(bcontroller).isNotNull();
	}

}
