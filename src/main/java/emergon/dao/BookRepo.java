package emergon.dao;

import emergon.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {

    public List<Book> findByAuthorId(Integer id);
    
}
