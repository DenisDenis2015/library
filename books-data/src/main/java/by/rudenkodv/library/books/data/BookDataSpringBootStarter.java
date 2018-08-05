package by.rudenkodv.library.books.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class BookDataSpringBootStarter {

	public static void main(String[] args) {
		SpringApplication.run(BookDataSpringBootStarter.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			System.out.println("books-list works....");
		};
	}
}
