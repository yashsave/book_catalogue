package au.com.commbank.bookcatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.commbank.bookcatalogue.dto.Book;
import au.com.commbank.bookcatalogue.exception.IdNotFoundException;
import au.com.commbank.bookcatalogue.exception.InvalidISBNValueException;
import au.com.commbank.bookcatalogue.service.CatalogueService;

@RestController
public class CatalogueController {

	@Autowired
	CatalogueService catalogueService;
	
	/**
	 * Adds a book to a datastore. Expects a JSON book object. e.g. of book object below
	 * {"title":"Books 4","author":["John4","Anna4","Pete4"],"isbn":1234567890123,"publicationDates":"2014-01-01T23:28:56.782Z"}
	 * @param book
	 * @throws InvalidISBNValue 
	 */
	@RequestMapping(method=RequestMethod.POST, value="/book", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addBook(@RequestBody Book book) throws InvalidISBNValueException {
		return catalogueService.addBook(book);
	}

	/**
	 * Updates a book based on ISBN as it is assumed to be unique for any book
	 * Expects a book object to be passed in the body. e.g. of book objects
	 * {"title":"Books 4","author":["John4","Anna4","Pete4"],"isbn":1234567890123,"publicationDates":"2014-01-01T23:28:56.782Z"}
	 * @param book
	 * @throws IdNotFoundException
	 * @throws InvalidISBNValue 
	 */
	@RequestMapping(method= RequestMethod.PUT, value="/book", consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> updateBook(@RequestBody Book  book) throws IdNotFoundException, InvalidISBNValueException   {
		return catalogueService.updateBook(book);		
	}
	
	/**
	 * Deletes a book from catalogue based on ISBN number passed in the URL
	 * @param isbn
	 * @throws IdNotFoundException
	 */
	@RequestMapping(method= RequestMethod.DELETE, value="/book/{isbn}"  )
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable String isbn ) throws IdNotFoundException {
		return catalogueService.deleteBook(isbn);
	}
	
	/**
	 * 
	 * @param isbn
	 * @returns a book based on the ISBN  passed in URL
	 * @throws IdNotFoundException
	 */
	@RequestMapping(method= RequestMethod.GET, value="/book/isbn/{isbn}", produces = "application/json"  )
	public Book listBookByISBN(@PathVariable String isbn) throws IdNotFoundException {
		return catalogueService.getBookByISBN(isbn) ;
	}
	
	/**
	 * 
	 * @param title
	 * @return  a book based on the Title  passed in URL
	 * @throws IdNotFoundException
	 */
	@RequestMapping(method= RequestMethod.GET, value="/book/title/{title}", produces = "application/json"  )
	public Book listBookByTitle(@PathVariable String title) throws IdNotFoundException {
		return catalogueService.getBookByTitle(title) ;
	}
}
