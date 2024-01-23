package com.example.bookflixspring.book;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping("/books")
public class BookController{
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            Optional<Book> bookOptional = bookRepository.findById(id);
            return ResponseEntity.ok(bookOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createBook(@RequestBody Book newBook, UriComponentsBuilder ucb) {
        Book savedBook = bookRepository.save(newBook);

        URI locationOfNewBook = ucb
                    .path("/books/{id}")
                    .buildAndExpand(savedBook.getId())
                    .toUri();
        
        return ResponseEntity.created(locationOfNewBook).build();
    }

    @PutMapping("/{requestedId}")
    public ResponseEntity<Void> updateBook(@PathVariable Long requestedId, @RequestBody Book updatedBook) {

        System.out.println("Updated book: " + updatedBook); // Add this line

        if (bookRepository.existsById(requestedId)) {
            Book book = bookRepository.findById(requestedId).get();
            book.setName(updatedBook.getName());
            book.setAuthor(updatedBook.getAuthor());
            book.setDescription(updatedBook.getDescription());
            bookRepository.save(book);   
        }
        else{
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    private ResponseEntity<List<Book>> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(
            PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())
        );
        return ResponseEntity.ok(books.getContent()); 
    }

    @DeleteMapping("/{requestedId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long requestedId) {

        if (bookRepository.existsById(requestedId)) {
            bookRepository.deleteById(requestedId);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
