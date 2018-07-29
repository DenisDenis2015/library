package by.rudenkodv.library.books.list.service.repository;

import by.rudenkodv.library.books.list.model.Genre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveGenreRepository extends ReactiveCrudRepository<Genre, String>, ReactiveGenreRepositoryCustom {

}
