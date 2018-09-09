package by.rudenkodv.library.book.data;

import by.rudenkodv.library.book.data.service.BookDataService;
import by.rudenkodv.library.book.data.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class BookDataSpringBootStarter {

    @Autowired
    private BookDataService bookDataService;

    @Autowired
    private BookImageService bookImageService;

    public static void main(String[] args) {
        SpringApplication.run(BookDataSpringBootStarter.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            System.out.println("books-data works....");
        };
    }
}