package au.com.commbank.bookcatalogue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
/**
 * Thrown when ISBN value is non-numeric or its length is not 13 digits
 * 
 * @author yasave
 *
 */
@Component 
public class InvalidISBNValueException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
    private HttpStatus httpcode;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpcode() {
		return httpcode;
	}

	public void setHttpcode(HttpStatus httpcode) {
		this.httpcode = httpcode;
	}
}
