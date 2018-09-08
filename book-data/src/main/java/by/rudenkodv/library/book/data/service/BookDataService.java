package by.rudenkodv.library.book.data.service;

import by.rudenkodv.library.book.data.model.BookData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookDataService extends MongoRepository <BookData, String>{
}
