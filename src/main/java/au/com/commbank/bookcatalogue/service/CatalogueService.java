package au.com.commbank.bookcatalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import au.com.commbank.bookcatalogue.broker.KafkaTopic;
import au.com.commbank.bookcatalogue.db.CatalogueDB;
import au.com.commbank.bookcatalogue.dto.Book;
import au.com.commbank.bookcatalogue.exception.IdNotFoundException;
import au.com.commbank.bookcatalogue.exception.InvalidISBNValueException;
import au.com.commbank.bookcatalogue.validate.Inspector;


@Component
public class CatalogueService {
	enum MsgStore{
		INVALIDLENGTH,
		InvalidISBNValue,
		IdNotFoundException,
		BookCannotBeDeleted,
		BookDeletedWithISBN,
		AddedBook,
		SelectedBook,
		InvalidISBN,
		InvalidTitle
	}
	
	@Autowired
	CatalogueDB db;
	@Autowired
	KafkaTopic topic;
	@Autowired
	Inspector inspect;
	@Autowired
	IdNotFoundException idNotFndException; 

	
	private String getMsg(MsgStore  enumMsg) {
		String returnMsg;
		switch (enumMsg) {
			case INVALIDLENGTH:
				returnMsg ="Invalid Length. Expected 13 digit ISBN number";
				break;
			case IdNotFoundException:
				returnMsg = "Matching ISBN not found.No changes will be applied";
				break;
			case BookCannotBeDeleted:
				returnMsg ="Book cannot be deleted.Absent ISBN - ";
				break;
			case BookDeletedWithISBN:
				returnMsg ="Deleted book with ISBN - ";
				break;	
			case AddedBook:
				returnMsg="Added book with ISBN - ";
				break;
			case SelectedBook:
				returnMsg="Selected book with ISBN - ";
				break;
			case InvalidISBN:
				returnMsg="Invalid ISBN -";
				break;
			case InvalidTitle:
				returnMsg="Invalid Title -";
				break;	
			default:
				returnMsg ="";
				break;
		}
		
		return returnMsg;
	}
	
	public ResponseEntity<?> addBook(Book book) throws InvalidISBNValueException {
		if (inspect.validateBook(book)) {
			db.addBook(book);	
			topic.append(getMsg(MsgStore.AddedBook) +book.getISBN());
		}
		return new ResponseEntity<>(
			      book, 
			      HttpStatus.CREATED);
	}
	
	public Book getBookByISBN(String isbn) throws IdNotFoundException {
		Book book = db.getBook(isbn);
		if (book== null) {
			idNotFndException.setMessage(getMsg(MsgStore.InvalidISBN)+isbn );
			topic.append(getMsg(MsgStore.InvalidISBN)+isbn);
			throw idNotFndException;
		} else {
			topic.append(getMsg(MsgStore.SelectedBook)+isbn);
		}
		return book;
	}
	
	public Book getBookByTitle(String title) throws IdNotFoundException {
		Book book = db.getBookByTitle(title);
		if (book == null) {
			idNotFndException.setMessage(getMsg(MsgStore.InvalidTitle)+title );
			topic.append(getMsg(MsgStore.InvalidTitle)+title);
			throw idNotFndException;
		} else {
			topic.append(getMsg(MsgStore.SelectedBook)+title);
		}
		return book;
	}
	public ResponseEntity<?> updateBook(Book book) throws IdNotFoundException, InvalidISBNValueException {
		HttpStatus status=HttpStatus.OK;
		if (db.getBook(book.getISBN()) !=null ) {
			db.updatedBook(book);
			topic.append("Updated book with ISBN - "+book.getISBN());
		} else {
			idNotFndException.setMessage(getMsg(MsgStore.IdNotFoundException));
			topic.append(getMsg(MsgStore.IdNotFoundException));
			throw idNotFndException;
		}
		return new ResponseEntity<>(
			      book, 
			      status);
	}
	
	public ResponseEntity<HttpStatus> deleteBook(String isbn) throws IdNotFoundException {
		HttpStatus status=HttpStatus.OK;
		
		if (db.getBook(isbn) != null ) {
				db.deleteBook(isbn);
				topic.append(getMsg(MsgStore.BookDeletedWithISBN)+isbn);

		} else {
			idNotFndException.setMessage(getMsg(MsgStore.IdNotFoundException));
			topic.append(getMsg(MsgStore.BookCannotBeDeleted)+isbn);
			throw idNotFndException;
		}
		return new ResponseEntity<HttpStatus>( status);
		
	}
}
