/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.controller;

import emergon.dao.AuthorRepo;
import emergon.dao.BookRepo;
import emergon.entity.Author;
import emergon.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @book user
 */
@RestController
@RequestMapping("/api")
public class BookController {
    
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable(value = "id") Integer bookId) throws Exception {
        Optional<Book> optionalBook = bookRepo.findById(bookId);
        return optionalBook.orElseThrow(() -> new Exception("Book not exists with id:" + bookId));
    }
    
    @PostMapping("/authors/{authorId}/books")
    public Book createBook(@PathVariable(value = "authorId") Integer authorId,
            @RequestBody Book book) {
        Author author = authorRepo.findById(authorId).get();
        book.setAuthor(author);
        return bookRepo.save(book);
    }
    ///authors/3/books
    @GetMapping("/authors/{id}/books")
    public List<Book> getBooksByAuthor(@PathVariable(value = "id") Integer id){
        return bookRepo.findByAuthorId(id);
    }
}
