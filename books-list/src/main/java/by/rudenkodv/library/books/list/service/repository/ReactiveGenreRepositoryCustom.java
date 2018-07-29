package by.rudenkodv.library.books.list.service.repository;

import by.rudenkodv.library.books.list.model.Genre;
import reactor.core.publisher.Flux;

public interface ReactiveGenreRepositoryCustom {

    Flux<Genre> getAllGenres();

}
