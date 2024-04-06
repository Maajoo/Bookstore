package bkstr.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bkstr.bookstore.web.CategoryController;


@SpringBootTest
class CategoryControllerSmoke {

	@Autowired 
	private CategoryController ccontroller;

	@Test
	void contextLoads() {
		assertThat(ccontroller).isNotNull();
	}

}
