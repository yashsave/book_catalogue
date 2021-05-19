package au.com.commbank.bookcatalogue.exception;

import java.util.Date;

import org.springframework.stereotype.Component;

public class ErrorDetails {
	  private String httpStatusCode;
	  private String message;
	  private String details;
	  private Date timestamp;
	  
	  public ErrorDetails(String httpStatusCode, String message,String details,Date timestamp  ) {
		  this.setHttpStatusCode(httpStatusCode);
		  this.setTimestamp(timestamp);
		  this.setMessage(message);
		  this.setDetails(details);
	  }

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
