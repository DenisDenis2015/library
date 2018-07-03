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
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class BookListSpringBootStarter {

	@Bean
	CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		// Possibly...
		// config.applyPermitDefaultValues()
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("");
		config.addAllowedMethod("");
		PathPatternParser patternParser = new PathPatternParser();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(patternParser);
		source.registerCorsConfiguration("/**", config);
		return new CorsWebFilter(source);
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

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

			Mono<Void> deleteAllGenre = genreRepository.deleteAll();

			Flux<Genre> saveAllGenre = genreRepository.saveAll(Flux.fromStream(Stream.of(
					new Genre("horrors"), new Genre("action"), new Genre("adventure"),
					new Genre("fantastic"), new Genre("fantasy")
			)));

			Flux.concat(deleteAllGenre, saveAllGenre);

			List<Genre> genres = genreRepository.findAll().toStream().collect(Collectors.toList());

			List<Book> books = Stream.generate(() -> {
				Book book = new Book();
				book.setAuthor("Author" + RandomUtils.nextInt());
				book.setTitle("Title" + RandomUtils.nextInt());
				book.setYear(new Date());
				book.setDescription("description" + RandomUtils.nextInt());
				book.setGenre(genres.get(RandomUtils.nextInt(genres.size())));
				return book;
			}).limit(10).collect(Collectors.toList());

			bookRepository.deleteAll().subscribe( null, null, () -> {
				bookRepository.saveAll(Flux.fromIterable(books)).subscribe(System.out::println);
			});
		};
	}
}
