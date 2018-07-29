package by.rudenkodv.library.books.list.service.repository.impl;

import by.rudenkodv.library.books.list.model.Genre;
import by.rudenkodv.library.books.list.service.repository.ReactiveBookRepository;
import by.rudenkodv.library.books.list.service.repository.ReactiveGenreRepository;
import by.rudenkodv.library.books.list.service.repository.ReactiveGenreRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

public class ReactiveGenreRepositoryImpl implements ReactiveGenreRepositoryCustom {

    @Autowired
    private ReactiveGenreRepository reactiveGenreRepository;

    @Autowired
    private ReactiveBookRepository reactiveBookRepository;

    @Override
    public Flux<Genre> getAllGenres() {
        return reactiveGenreRepository.findAll().doOnNext(genre -> {
            genre.setCount(reactiveBookRepository.countByGenreName(genre.getName()).block());
        });
    }
}
