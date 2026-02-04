package com.kucw.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kucw.library.Book;
import com.kucw.library.BookRequest;
import com.kucw.library.service.BookService;

@RestController
@CrossOrigin(origins = "*")
public class BookContrller {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable Integer bookId)
	{
		Book book = bookService.getBookById(bookId);
		if(book != null){
			return ResponseEntity.status(HttpStatus.OK).body(book);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        Integer bookId = bookService.createBook(bookRequest);

        Book book = bookService.getBookById(bookId);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer bookId,
										   @RequestBody BookRequest bookRequest){
		Book book = bookService.getBookById(bookId);
		if(book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		bookService.updateBook(bookId, bookRequest);
		Book updatedBook = bookService.getBookById(bookId);
		return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
	}
	
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer bookId){
		bookService.deleteBookById(bookId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
