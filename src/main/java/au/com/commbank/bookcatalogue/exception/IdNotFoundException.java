package au.com.commbank.bookcatalogue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
@Component 
public class IdNotFoundException extends Exception{
	/**
	 * 
	 */
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
		this.httpcode = HttpStatus.BAD_REQUEST;
	}
}
