package emergon.controller;

import emergon.dao.AuthorRepo;
import emergon.entity.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//@RestController = @Controller + @ResponseBody
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepo authorRepo;

    @GetMapping
    public List<Author> getAuthors() {
        return authorRepo.findAll();
    }

    @GetMapping("/{myvariable}")
    public Author getAuthorById(@PathVariable(value = "myvariable") Integer authorId) throws Exception {
        Optional<Author> optionalAuthor = authorRepo.findById(authorId);
        return optionalAuthor.orElseThrow(() -> new Exception("Author not exists with id:" + authorId));
        //return optionalAuthor.get();
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorRepo.save(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthorById(@PathVariable(value = "id") Integer authorId) {
        authorRepo.deleteById(authorId);
        return ResponseEntity.ok("Author deleted successfully, ID:" + authorId);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable(value = "id") Integer authorId,
            @RequestBody Author newAuthorDetails) throws Exception {
        Optional<Author> optionalAuthor = authorRepo.findById(authorId);
        Author authorToUpdate = optionalAuthor.orElseThrow(() -> new Exception("Author not exists with id:" + authorId));
        
        authorToUpdate.setName(newAuthorDetails.getName());
        return authorRepo.save(authorToUpdate);
    }
    
    @GetMapping("/search/{name}")
    public List<Author> getAuthorsByName(@PathVariable(value = "name") String name){
        return authorRepo.findByName(name);
    }

}
