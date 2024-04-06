package bkstr.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import bkstr.bookstore.domain.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.course.domain.Department;
import fi.haagahelia.course.domain.Student;

//@RunWith(SpringRunner.class)  //JUnit4
@ExtendWith(SpringExtension.class)  // JUnit5 eli Jupiter
@DataJpaTest
public class WebLayer {

    @Autowired
    private BookRepository brepository;

    @Test  // testataan BookRepositoryn findByLastName()-metodin toimivuutta
    public void findByLastnameShouldReturnStudent() {
        List<Student> students = repository.findByLastName("Johnson");
        
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getFirstName()).isEqualTo("John");
    }
    
    @Test // testataan StudentRepositoryn save()-metodin toimivuutta
    public void createNewStudent() {
    	Student student = new Student("Mickey", "Mouse", "mm@mouse.com", null);
    	repository.save(student);
    	assertThat(student.getId()).isNotNull();
    }    

}
}
