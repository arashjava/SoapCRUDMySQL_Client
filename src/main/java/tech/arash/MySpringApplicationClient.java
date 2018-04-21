package tech.arash;
import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tech.arash.wsdl.AddBookResponse;
import tech.arash.wsdl.BookInfo;
import tech.arash.wsdl.DeleteBookResponse;
import tech.arash.wsdl.GetAllBooksResponse;
import tech.arash.wsdl.GetBookByIdResponse;
import tech.arash.wsdl.ServiceStatus;
import tech.arash.wsdl.UpdateBookResponse;
@SpringBootApplication
public class MySpringApplicationClient {  
	public static void main(String[] args) {
		SpringApplication.run(MySpringApplicationClient.class, args);
    }       
	@Bean
	CommandLineRunner lookup(BookClient bookClient) {
		return args -> {
			System.out.println(" Find a Book by Id ");
			GetBookByIdResponse bookByIdResponse = bookClient.getBookById(1);
			BookInfo bookInfo = bookByIdResponse.getBookInfo();
			System.out.println(bookInfo.getBookId() + ", "+ bookInfo.getTitle()
			     + ", " + bookInfo.getPrice());
			
			System.out.println("List all the books ---");
			GetAllBooksResponse allBooksResponse = bookClient.getAllBooks();
			allBooksResponse.getBookInfo().stream()
			   .forEach(e -> System.out.println(e.getBookId() + ", "+ e.getTitle() + ", " + e.getPrice()));
			
			System.out.println("Adding a Book ...");
		    String title = "Programing in 21st century..";
		    BigDecimal price = BigDecimal.valueOf(69.5);
			AddBookResponse addBookResponse = bookClient.addBook(title, price);
			bookInfo = addBookResponse.getBookInfo();
			if (bookInfo != null) {
			  System.out.println(bookInfo.getBookId() + ", "+ bookInfo.getTitle()
			       + ", " + bookInfo.getPrice());
			}
			ServiceStatus serviceStatus = addBookResponse.getServiceStatus();
			System.out.println("StatusCode: " + serviceStatus.getStatusCode() + 
					", Message: " + serviceStatus.getMessage());
			
			System.out.println("Updating a book");
			bookInfo = new BookInfo();
			bookInfo.setBookId(1);
			bookInfo.setTitle("Java programing under the ground");
			bookInfo.setPrice(BigDecimal.valueOf(49.3));
			
			UpdateBookResponse updateBookResponse = bookClient.updateBook(bookInfo);
			serviceStatus = updateBookResponse.getServiceStatus();
			System.out.println("StatusCode: " + serviceStatus.getStatusCode() + 
					", Message: " + serviceStatus.getMessage());
			System.out.println("Deleting a book ...");
			long bookId = 3;
			DeleteBookResponse deleteBookResponse = bookClient.deleteBook(bookId);
			serviceStatus = deleteBookResponse.getServiceStatus();
			System.out.println("StatusCode: " + serviceStatus.getStatusCode() + 
					", Message: " + serviceStatus.getMessage());			
		};
	}	
}         
