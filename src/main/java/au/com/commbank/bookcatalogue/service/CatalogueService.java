package au.com.commbank.bookcatalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import au.com.commbank.bookcatalogue.db.CatalogueDB;
import au.com.commbank.bookcatalogue.dto.Book;


@Component
public class CatalogueService {
	@Autowired
	CatalogueDB db;
	
	public ResponseEntity<HttpStatus> addBook(Book book) {
		db.addBook(book);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	public Book getBook(String isbn) {
		return db.getBook(isbn);
	}
	
	public ResponseEntity<HttpStatus> updateBook(Book book) {
		HttpStatus status=HttpStatus.OK;
		if (book != null ) {
				addBook(book);
		} else {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<HttpStatus>( status);
	}
	
	public ResponseEntity<HttpStatus> deleteBook(String isbn) {
		HttpStatus status=HttpStatus.OK;
		
		if (db.getBook(isbn) != null ) {
				db.deleteBook(isbn);
		} else {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<HttpStatus>( status);
		
	}
}
