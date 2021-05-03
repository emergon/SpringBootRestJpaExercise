package emergon.dao;

import emergon.entity.Author;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
public class AuthorRepoTests {

    @Autowired
    private AuthorRepo authorRepo;

    @Test
    @Rollback(false)
    public void testSaveNewAuthor() {
        Author author = new Author();
        author.setName("Robert");
        Author savedAuthor = authorRepo.save(author);
        assertThat(savedAuthor.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindByName() {
        Author author = authorRepo.getByName("Robert");
        assertThat(author.getName()).isEqualTo("Robert");
    }

    @Test
    @Rollback(false)
    public void testUpdateAuthor() {
        Author author = authorRepo.getByName("Robert");
        author.setName("Jack");
        authorRepo.save(author);
        Author updatedAuthor = authorRepo.getByName("Jack");
        assertThat(updatedAuthor.getName()).isEqualTo("Jack");
    }
}
