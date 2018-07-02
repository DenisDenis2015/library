package by.rudenkodv.library.books.list.service.repository;

import java.util.List;


import by.rudenkodv.library.books.list.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveBookRepository extends ReactiveCrudRepository<Book, Long> {
    
    public Mono<Book> findByTitle(String title);

}
