package au.com.commbank.bookcatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.commbank.bookcatalogue.dto.Book;
import au.com.commbank.bookcatalogue.service.CatalogueService;

@RestController
public class CatalogueController {

	@Autowired
	CatalogueService catalogueService;
	
	@RequestMapping(method=RequestMethod.POST, value="/book", consumes = "application/json", produces = "application/json")
	public ResponseEntity<HttpStatus> addBook(@RequestBody Book book) {
		return catalogueService.addBook(book);
	}
	
	@RequestMapping(method= RequestMethod.PUT, value="/book", consumes = "application/json", produces = "application/json" )
	public ResponseEntity<HttpStatus> updateBook(@RequestBody Book  book) {
		return catalogueService.updateBook(book);
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value="/book/{isbn}"  )
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable String isbn ) {
		return catalogueService.deleteBook(isbn);
	}
	
	@RequestMapping(method= RequestMethod.GET, value="/book/{isbn}" )
	public Book listBook(@PathVariable String isbn) {
		return catalogueService.getBook(isbn) ;
	}
	
//	@RequestMapping(method= RequestMethod.POST, value="/booktoqueue", consumes = "application/json", produces = "application/json")
//	public String sendToQueue() {
//		return null;
//		
//	}
	
}
