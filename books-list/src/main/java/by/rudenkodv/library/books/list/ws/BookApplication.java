package by.rudenkodv.library.books.list.ws;

import by.rudenkodv.library.books.list.model.Book;
import by.rudenkodv.library.books.list.model.Genre;
import by.rudenkodv.library.books.list.service.repository.ReactiveBookRepository;
import by.rudenkodv.library.books.list.service.repository.ReactiveGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

//@RefreshScope
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

	@GetMapping(value = "/get/all/books")
	public Flux<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping(value = "/get/all/genres")
	public Flux<Genre> getAllGenres() {
		return genreRepository.findAll();
	}
}
