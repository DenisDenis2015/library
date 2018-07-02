package by.rudenkodv.library.books.list.springboot;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import by.rudenkodv.library.books.list.model.Book;
import by.rudenkodv.library.books.list.model.Genre;
import by.rudenkodv.library.books.list.service.repository.BookRepository;
import by.rudenkodv.library.books.list.service.repository.GenreRepository;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "by.rudenkodv.library.books.list")
@EnableJpaRepositories(basePackages = "by.rudenkodv.library.books.list.service.repository")
@EntityScan("by.rudenkodv.library.books.list.model")
public class BookListSpringBootStarter implements CommandLineRunner {

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private GenreRepository genreRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookListSpringBootStarter.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		genreRepository.deleteAll();

		List<Genre> genre = Arrays.asList(new Genre("horrors"), new Genre("action"), new Genre("adventure"),
				new Genre("fantastic"), new Genre("fantasy"));

		genreRepository.save(genre);

		System.out.println("Get genre from repo");

		genreRepository.findAll().forEach(System.out::println);

		bookRepository.deleteAll();
		
		List<Book> books = Stream.generate(() ->{
			Book book = new Book();
			book.setAuthor("Author" + RandomUtils.nextInt());
			book.setTitle("Title" + RandomUtils.nextInt());
			book.setYear(new Date());
			book.setDescription("description" + RandomUtils.nextInt());
			book.setGenre(genreRepository.findByGenre("fantasy"));
			return book;
		}).limit(10).collect(Collectors.toList());

		bookRepository.save(books);

		System.out.println("Get book from repo");

		bookRepository.findAll().forEach(System.out::println);

	}
}
