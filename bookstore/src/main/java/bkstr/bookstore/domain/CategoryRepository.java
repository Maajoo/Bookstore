package bkstr.bookstore.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);
}
