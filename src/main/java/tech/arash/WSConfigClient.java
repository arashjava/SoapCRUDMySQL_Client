package tech.arash;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSConfigClient {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("tech.arash.wsdl");
		return marshaller;
	}
	
	@Bean
	public BookClient articleClient(Jaxb2Marshaller marshaller) {
		BookClient client = new BookClient();
		client.setDefaultUri("http://localhost:8080/soapws/books.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
}
