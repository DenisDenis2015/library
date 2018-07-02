package by.rudenkodv.library.books.list.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.rudenkodv.library.books.list.model.Book;
import by.rudenkodv.library.books.list.model.Genre;
import by.rudenkodv.library.books.list.service.repository.BookRepository;
import by.rudenkodv.library.books.list.service.repository.GenreRepository;

//@RefreshScope
//@RestController
public class BookApplication {

/*	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private GenreRepository genreRepository;

	@RequestMapping(value = "/available")
	public String available() {
		return "from available Spring in Action";
	}

	@RequestMapping(value = "/get/all/books")
	public Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@RequestMapping(value = "/get/all/genres")
	public Iterable<Genre> getAllGenres() {
		return genreRepository.findAll();
	}*/
}
