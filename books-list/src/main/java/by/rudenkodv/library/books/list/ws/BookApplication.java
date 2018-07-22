package by.rudenkodv.library.books.list.ws;

import by.rudenkodv.library.books.list.model.Book;
import by.rudenkodv.library.books.list.model.Genre;
import by.rudenkodv.library.books.list.service.repository.ReactiveBookRepository;
import by.rudenkodv.library.books.list.service.repository.ReactiveGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RefreshScope
@RestController
public class BookApplication {

	@Autowired
	private ReactiveBookRepository bookRepository;

	@Autowired
	private ReactiveGenreRepository genreRepository;

	@GetMapping(value = "/available")
	public String available() {
		return "rest service is  available";
	}

	@GetMapping(value = "/get/all/books", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping(value = "/get/all/books/{genreName}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Book> getBooksByGenre(final @PathVariable(name = "genreName") String genreName) {
		return bookRepository.findByGenre(genreName);
	}

	@GetMapping(value = "/get/all/genres", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Genre> getAllGenres() {
		return genreRepository.findAll();
	}

	@GetMapping(value = "/get/all/books/stream" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Book> getAllBooksStream() {
		return Flux.zip(Flux.interval(Duration.ofSeconds(3)) , bookRepository.findAll()).map(Tuple2::getT2);
	}
}
