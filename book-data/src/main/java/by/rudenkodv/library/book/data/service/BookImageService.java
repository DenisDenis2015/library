package by.rudenkodv.library.book.data.service;

import by.rudenkodv.library.book.data.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookImageService extends MongoRepository<Image, String> {

    Image findByBookId(String bookId);
}
