package tech.arash;

import java.math.BigDecimal;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import tech.arash.wsdl.AddBookRequest;
import tech.arash.wsdl.AddBookResponse;
import tech.arash.wsdl.BookInfo;
import tech.arash.wsdl.DeleteBookRequest;
import tech.arash.wsdl.DeleteBookResponse;
import tech.arash.wsdl.GetAllBooksRequest;
import tech.arash.wsdl.GetAllBooksResponse;
import tech.arash.wsdl.GetBookByIdRequest;
import tech.arash.wsdl.GetBookByIdResponse;
import tech.arash.wsdl.UpdateBookRequest;
import tech.arash.wsdl.UpdateBookResponse;

public class BookClient extends WebServiceGatewaySupport  {
	public GetBookByIdResponse getBookById(long bookId) {
		GetBookByIdRequest request = new GetBookByIdRequest();
		request.setBookId(bookId);
		GetBookByIdResponse response = (GetBookByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/getBookByIdRequest"));
		return response;
	}
	public GetAllBooksResponse getAllBooks() {
		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = (GetAllBooksResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/getAllBooksRequest"));
     	return response;
	}	
	public AddBookResponse addBook(String title, BigDecimal price) {
		AddBookRequest request = new AddBookRequest();
		request.setTitle(title);
		request.setPrice(price);
		AddBookResponse response = (AddBookResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/addBookRequest"));
     	return response;
	}	
	public UpdateBookResponse updateBook(BookInfo bookInfo) {
		UpdateBookRequest request = new UpdateBookRequest();
		request.setBookInfo(bookInfo);
		UpdateBookResponse response = (UpdateBookResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/updateBookRequest"));
     	return response;
	}	
	public DeleteBookResponse deleteBook(long bookId) {
		DeleteBookRequest request = new DeleteBookRequest();
		request.setBookId(bookId);
		DeleteBookResponse response = (DeleteBookResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/soapws/deleteBookRequest"));
     	return response;
	}		
}

