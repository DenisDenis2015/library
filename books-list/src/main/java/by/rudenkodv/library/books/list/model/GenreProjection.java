package by.rudenkodv.library.books.list.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Genre.class})
public interface GenreProjection {

    Long getId();

    String getGenre();

}
