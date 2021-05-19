package au.com.commbank.bookcatalogue.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
@Component 
public class Book {
	
	@JsonProperty("title")
	 private String title;
	
	@JsonProperty("author")
	 private List < String > author = new ArrayList < String > ();
	 
	@JsonProperty("isbn")
	private String isbn;
	
	@JsonProperty("publicationDates")
	 private String publicationDates;


	 // Getter Methods 
	 public String getTitle() {
	  return title;
	 }
	 
	 public List<String> getAuthor() {
		return author;
	}
	 public String getISBN() {
	  return isbn;
	 }

	 public String getPublicationDates() {
	  return publicationDates;
	 }

	 // Setter Methods 
	 public void setTitle(String Title) {
	  this.title = Title;
	 }

	 public void setAuthor(List<String> author) {
		this.author = author;
	 }	

	 public void setISBN(String ISBN) {
	  this.isbn = ISBN;
	 }

	 public void setPublicationDates(String PublicationDates) {
	  this.publicationDates = PublicationDates;
	 }
		 
	}
