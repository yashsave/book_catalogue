package au.com.commbank.bookcatalogue.db;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import au.com.commbank.bookcatalogue.dto.Book;

@Component
public class CatalogueDB {

	private Map <String, Book> books = new HashMap<String, Book>();
	
	public void addBook (Book book) {
		books.put(book.getISBN(), book);
	}
	
	public void updatedBook(Book book ) {
		books.put(book.getISBN(), book);
	}
	
	public Book getBook (String isbn) {
	   return books.get(isbn);
	}
	
	public void deleteBook(String isbn) {
		books.remove(isbn);
	}
}
