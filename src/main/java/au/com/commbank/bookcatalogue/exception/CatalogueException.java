package au.com.commbank.bookcatalogue.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CatalogueException {

	@ExceptionHandler(IdNotFoundException.class)
	  public ResponseEntity<ErrorDetails> IdNotFound(IdNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.toString() , ex.getMessage(),request.getDescription(false), new Date());
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
}
