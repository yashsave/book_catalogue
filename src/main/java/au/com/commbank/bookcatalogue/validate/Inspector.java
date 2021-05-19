package au.com.commbank.bookcatalogue.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import au.com.commbank.bookcatalogue.db.CatalogueDB;
import au.com.commbank.bookcatalogue.dto.Book;
import au.com.commbank.bookcatalogue.exception.InvalidISBNValueException;

@Component
public class Inspector {
	@Autowired
	CatalogueDB db;
	private boolean isNumeric (String ISBN) {
		boolean res = true;
		try {
			Long.parseLong(ISBN);
		}
		catch (NumberFormatException nfe) {
			res = false;
		}
		catch (Exception e) {
			res = false;
		}
		return res;
	}
	@Autowired
	InvalidISBNValueException invalidISBNValue;
	
	public boolean validateBook(Book book) throws InvalidISBNValueException {
		boolean retValue = true;
		String ISBN = book.getISBN();
		if ((ISBN.length() != 13) || !isNumeric(ISBN)) {
			invalidISBNValue.setMessage("Invalid ISBN. Expected 13 digit numeric ISBN number");
			invalidISBNValue.setHttpcode(HttpStatus.UNPROCESSABLE_ENTITY);
			retValue = false;
			throw invalidISBNValue;
		}
		
		if (db.getBook(book.getISBN())!=null){
			invalidISBNValue.setMessage("ISBN already exist");
			invalidISBNValue.setHttpcode(HttpStatus.UNPROCESSABLE_ENTITY);
			retValue = false;
			throw invalidISBNValue;			
		}
		return retValue;
	}
}
