package by.rudenkodv.library.book.data;

import by.rudenkodv.library.book.data.model.Image;
import by.rudenkodv.library.book.data.service.BookDataService;
import by.rudenkodv.library.book.data.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        System.out.println("runner....");
        return args -> {

            System.out.println("books-data works....");

            bookImageService.deleteAll();

/*            try {
                byte[] fileContent = Files.readAllBytes(Paths.get("/home/denis/Work/Project/library/books-list/src/main/resources/book/img/451fahrenheit.jpg"));
                Image image = new Image();
                image.setData(fileContent);
                image.setBookId("BookId");
                bookImageService.save(image);

            } catch (IOException e) {
                e.printStackTrace();
            }*/
        };
    }
}