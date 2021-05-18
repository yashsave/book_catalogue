package au.com.commbank.bookcatalogue.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import au.com.commbank.bookcatalogue.dto.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class CatalogueControllerTest {
	Book book = new Book();
	static final String isbn = "1234567890000";

	
	/**
	 * Test the Post method after adding a book
	 * STEP-1 We first do a GET to make sure the item does not exist, 
	 * STEP-2 Add a Book object and then
	 * STEP-3 Check if it was added successfully with a GET request
	 */
	@Test
	public void test_AddBook() {
		
		//STEP-1 - Delete isbn - check isbn does not exist
		deleteisbn(isbn);
		test_get1234567890000(null);
		
		//STEP-2 - adds isbn 1234567890000
		Book book = new Book();
		List<String> authors = new ArrayList<String>();
		authors.add("John");
		authors.add("Anna");
		authors.add("Peter");
		
		book.setISBN(isbn);
		book.setAuthor(authors);
		book.setPublicationDates("2014-01-01T23:28:56.782Z");
		book.setTitle("Books 4");
		
		 RestAssured.baseURI = "http://localhost:8081";
		 String path="/book";
		 RestAssured.given()
                .auth()
                .preemptive()
                .basic("admin", "commbank")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(book)
                .post(path)
                .then().statusCode(200);
		 
		 //STEP-3 - Checks Books object is created- isbn exist
		 test_get1234567890000(isbn);	 
	}
	
	//Test ISBN value, similarly we can test remaining JSON elements. 
	//Although not covered in these tests
	private void test_get1234567890000(String isbn) {
		RestAssured.baseURI = "http://localhost:8081/book";
		 RestAssured.given()
		   .auth()
          .preemptive()
          .basic("admin", "commbank")
          .header("Accept", ContentType.JSON.getAcceptHeader())
          .contentType(ContentType.JSON)
          .get("/1234567890000")
          .then().body("isbn", equalTo(isbn));
	}
	
	private void deleteisbn(String isbn ) {
		RestAssured.baseURI = "http://localhost:8081/book";
		 RestAssured.given()
		   .auth()
		   .preemptive()
		   .basic("admin", "commbank")
		   .header("Accept", ContentType.JSON.getAcceptHeader())
		   .contentType(ContentType.JSON)
		   .delete("/"+isbn);
	}
	

	@Test
	public void test_UpdateBook() {
		/*--- Delete Book with ISBN - 1234567890123  ---*/
		deleteisbn("1234567890123");
		
		/*--- ADD A NEW BOOK ---*/
		Book book = new Book();
		List<String> authors = new ArrayList<String>();
		authors.add("John");
		authors.add("Anna4");
		authors.add("Pete4");
		book.setISBN("1234567890123");
		book.setAuthor(authors);
		book.setPublicationDates("2014-01-01T23:28:56.782Z");
		book.setTitle("Title old");
		
		 RestAssured.baseURI = "http://localhost:8081";
		 String path="/book";
		 RestAssured.given()
                .auth()
                .preemptive()
                .basic("admin", "commbank")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body(book)
                .post(path)
                .then().statusCode(200);
		
		/*--- UPDATE THE BOOK ---*/
		 authors = new ArrayList<String>();
		 book = new Book();
		authors.add("Johnathan");
		authors.add("Anasthasia");
		authors.add("Peter");
		book.setISBN("1234567890123");
		book.setAuthor(authors);
		book.setPublicationDates("2014-01-01T23:28:56.782Z");
		book.setTitle("Titlenew");
		
		 RestAssured.baseURI = "http://localhost:8081";
		 path="/book";
		 RestAssured.given()
               .auth()
               .preemptive()
               .basic("admin", "commbank")
               .header("Accept", ContentType.JSON.getAcceptHeader())
               .contentType(ContentType.JSON)
               .body(book)
               .put(path)
               .then().statusCode(200);
		
		 
		 /*--- CHECH BOOK HAS UPDATED ---*/
		 
		 // Specify the base URL to the RESTful web service
		 RestAssured.baseURI = "http://localhost:8081/book";
		 
		 //Test Title
		 RestAssured.given()
		    .auth()
            .preemptive()
            .basic("admin", "commbank")
            .header("Accept", ContentType.JSON.getAcceptHeader())
            .contentType(ContentType.JSON)
            .get("/1234567890123")
            .then().body("title", equalTo("Titlenew"));
		 
		 //TestAuthor
		 RestAssured.given()
			 .auth()
	         .preemptive()
	         .basic("admin", "commbank")
	         .header("Accept", ContentType.JSON.getAcceptHeader())
	         .contentType(ContentType.JSON)
	         .get("/1234567890123")
	         .then().body("author", hasItems("Johnathan","Anasthasia","Peter"));
	}





	
	
	

}
