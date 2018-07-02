package by.rudenkodv.library.books.list.model;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Book.class})
public interface BookProjection {

    Long getId();

    String getTitle();

    String getAuthor();

    String getDescription();

    Genre getGenre();

    Date getYear();

}
