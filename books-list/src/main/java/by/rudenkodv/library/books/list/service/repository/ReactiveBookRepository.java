package by.rudenkodv.library.books.list.service.repository;

import by.rudenkodv.library.books.list.model.Book;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveBookRepository extends ReactiveCrudRepository<Book, String> {
    
    public Mono<Book> findByTitle(String title);

    @Query("{'genre.name' : ?0}")
    public Flux<Book> findByGenre(String name);
}
