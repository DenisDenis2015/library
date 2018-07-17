package by.rudenkodv.library.books.list.service.repository;

import by.rudenkodv.library.books.list.model.Genre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReactiveGenreRepository extends ReactiveCrudRepository<Genre, Long> {
	
    public Flux<Genre> findByGenre(String genre);

}
