package by.rudenkodv.library.books.list;

import by.rudenkodv.library.books.list.model.Book;
import by.rudenkodv.library.books.list.model.Genre;
import by.rudenkodv.library.books.list.service.repository.ReactiveBookRepository;
import by.rudenkodv.library.books.list.service.repository.ReactiveGenreRepository;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class BookListSpringBootStarter {

    @Autowired
    private ReactiveBookRepository bookRepository;

    @Autowired
    private ReactiveGenreRepository genreRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookListSpringBootStarter.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {

            genreRepository.deleteAll().subscribe(null, null, () -> {
                genreRepository.saveAll(Flux.fromStream(Stream.of(
                        new Genre("horrors"), new Genre("action"), new Genre("adventure"),
                        new Genre("fantastic"), new Genre("fantasy")
                ))).subscribe(null, null, () -> {
                    bookRepository.deleteAll().subscribe(null, null, () -> {
                        bookRepository.saveAll(Flux.fromIterable(getBooks())).subscribe((item) -> {
                            System.out.println(item);
                        });
                    });
                });
            });
        };
    }

    private List<Book> getBooks() {

        List<Genre> genres = genreRepository.findAll().collectList().block();

        try {

            byte[] fileContent = Files.readAllBytes(Paths.get("/home/denis/Work/Project/library/books-list/src/main/resources/book/content/451fahrenheit.pdf"));

            RestTemplate restTemplate = new RestTemplate();
            String avaliable = restTemplate.getForObject("http://localhost:9991/book-data/available", String.class);
            System.out.println(avaliable);

            RestTemplate restTemplate2 = new RestTemplate();
            ResponseEntity<String> idImage =
                    restTemplate2.postForEntity("http://localhost:9991/book-data/save/image/data", fileContent, String.class);
            System.out.println(idImage);
        } catch (Exception e) {
            System.out.println(e);
        }


        return Stream.generate(() -> {
            Book book = new Book();
            book.setAuthor("Author" + RandomUtils.nextInt());
            book.setTitle("Title" + RandomUtils.nextInt());
            book.setYear(new Date());
            book.setDescription("description" + RandomUtils.nextInt());
            book.setGenre(genres.get(RandomUtils.nextInt(genres.size())));
            return book;
        }).limit(10).collect(Collectors.toList());
    }
}
