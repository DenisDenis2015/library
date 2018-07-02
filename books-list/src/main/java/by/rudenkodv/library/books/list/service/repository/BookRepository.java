package by.rudenkodv.library.books.list.service.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import by.rudenkodv.library.books.list.model.Book;
import by.rudenkodv.library.books.list.model.BookProjection;


@RepositoryRestResource(excerptProjection = BookProjection.class)
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    
    public List<Book> findByTitle(String title);

}
