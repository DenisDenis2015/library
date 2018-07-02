package by.rudenkodv.library.books.list.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import by.rudenkodv.library.books.list.model.Genre;
import by.rudenkodv.library.books.list.model.GenreProjection;

@RepositoryRestResource(excerptProjection = GenreProjection.class)
public interface GenreRepository extends CrudRepository<Genre, Long> {
	
    public Genre findByGenre(String genre);

}
