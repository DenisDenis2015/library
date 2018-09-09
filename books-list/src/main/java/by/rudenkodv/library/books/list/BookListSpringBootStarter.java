package by.rudenkodv.library.books.list;

import by.rudenkodv.library.books.list.model.Book;
import by.rudenkodv.library.books.list.model.Genre;
import by.rudenkodv.library.books.list.service.repository.ReactiveBookRepository;
import by.rudenkodv.library.books.list.service.repository.ReactiveGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import reactor.core.publisher.Flux;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class BookListSpringBootStarter {

    private static final String BOOK_DATA_SERVICE_URL = "http://localhost:9991/book-data";

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

            new RestTemplate().getForObject(BOOK_DATA_SERVICE_URL + "/delete/all/data", Void.class);

            genreRepository.deleteAll().subscribe(null, null, () -> {
                genreRepository.saveAll(Flux.fromStream(Stream.of(
                        new Genre("horrors"), new Genre("action"), new Genre("adventure"),
                        new Genre("Фантастика"), new Genre("Фэнтази"), new Genre("Антиутопия")
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

        List<Book> bookList = new ArrayList<>();

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("/home/denis/Work/Project/library/books-list/src/main/resources/book/bookList.xml"));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("book");
            for (int index = 0; index < nodeList.getLength(); index++) {
                Node node = nodeList.item(index);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Book bookFromDB = bookRepository.save(new Book()).block();
                    Element eElement = (Element) node;
                    bookFromDB.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
                    bookFromDB.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    bookFromDB.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    bookFromDB.setGenre(genres.stream()
                            .filter((genre) ->
                                    genre.getName().equals(eElement.getElementsByTagName("genre").item(0).getTextContent()))
                            .findFirst().get());
                    bookFromDB.setYear(LocalDate.parse(eElement.getElementsByTagName("year").item(0).getTextContent(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                    attachImageAndContent(bookFromDB, eElement);
                    bookList.add(bookFromDB);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    private void attachImageAndContent(Book book, Element eElement) throws Exception {
        byte[] image = readDataFromFile(eElement.getElementsByTagName("image").item(0).getTextContent());
        String imageId = saveData(image, BOOK_DATA_SERVICE_URL + "/save/image/data/" + book.getId());
        book.setImageId(imageId);
        byte[] content = readDataFromFile(eElement.getElementsByTagName("content").item(0).getTextContent());
        String contentId = saveData(content, BOOK_DATA_SERVICE_URL + "/save/book/data/" + book.getId());
        book.setContentId(contentId);
    }

    private String saveData(byte[] content, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<byte[]> entity = new HttpEntity<>(content);
        return restTemplate.postForEntity(url, entity, String.class).getBody();
    }

    private byte[] readDataFromFile(String path) throws Exception {
        final URL resource = getClass().getClassLoader().getResource(path);
        return Files.readAllBytes(Paths.get(resource.toURI().getPath()));
    }
}
