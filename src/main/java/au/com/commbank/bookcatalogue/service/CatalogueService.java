package au.com.commbank.bookcatalogue.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import au.com.commbank.bookcatalogue.broker.KafkaTopic;
import au.com.commbank.bookcatalogue.db.CatalogueDB;
import au.com.commbank.bookcatalogue.dto.Book;
import au.com.commbank.bookcatalogue.exception.ErrorDetails;
import au.com.commbank.bookcatalogue.exception.IdNotFoundException;


@Component
public class CatalogueService {
	@Autowired
	CatalogueDB db;
	//@Autowired
	//KafkaTopic topic;
	
	@Autowired
	IdNotFoundException idNotFndException;
	
	public ResponseEntity<String> addBook(Book book) {
		db.addBook(book);	
		//topic.append("Added book with ISBN - "+book.getISBN());
		return new ResponseEntity<>(
			      "Successfully added ", 
			      HttpStatus.OK);
	}
	
	public Book getBook(String isbn) throws IdNotFoundException {
		if (db.getBook(isbn)== null) {
			idNotFndException.setMessage("ISBN " +isbn +" not found");
			throw idNotFndException;
		}
		return db.getBook(isbn);
	}
	
	public ResponseEntity<String> updateBook(Book book) throws IdNotFoundException {
		HttpStatus status=HttpStatus.OK;
		if (db.getBook(book.getISBN()) !=null ) {
			addBook(book);
			
		} else {
			idNotFndException.setMessage("Matching ISBN not found.No changes will be applied");
			throw idNotFndException;
		}
		return new ResponseEntity<>(
			      "Successfully added ", 
			      status);
	}
	
	public ResponseEntity<HttpStatus> deleteBook(String isbn) throws IdNotFoundException {
		HttpStatus status=HttpStatus.OK;
		
		if (db.getBook(isbn) != null ) {
				db.deleteBook(isbn);
		} else {
			idNotFndException.setMessage("Matching ISBN not found.No changes will be applied");
			throw idNotFndException;
		}
		return new ResponseEntity<HttpStatus>( status);
		
	}
}
