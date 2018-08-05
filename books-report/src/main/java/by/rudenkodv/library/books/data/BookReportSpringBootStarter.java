package by.rudenkodv.library.books.data;

import by.rudenkodv.library.books.data.ws.BookReportApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class BookReportSpringBootStarter {

	public static void main(String[] args) {
		SpringApplication.run(BookReportSpringBootStarter.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			System.out.println("books-report works....");
		};
	}
}
