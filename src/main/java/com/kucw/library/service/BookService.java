package com.kucw.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kucw.library.Book;
import com.kucw.library.BookRequest;
import com.kucw.library.dao.BookDao;

@Component
public class BookService {

	@Autowired
	private BookDao bookDao;

	public Book getBookById(Integer bookId) {
		return bookDao.getBookById(bookId);
	}
	
	public Integer createBook(BookRequest bookRequest) {
		return bookDao.createBook(bookRequest);
	}
	
	public void updateBook(Integer bookId, BookRequest bookRequest) {
		bookDao.updateBook(bookId, bookRequest);
	}
	public void deleteBookById(Integer bookId) {
		bookDao.deleteBookById(bookId);
	}
}
