package by.rudenkodv.library.book.data.ws;


import by.rudenkodv.library.book.data.model.BookData;
import by.rudenkodv.library.book.data.model.Image;
import by.rudenkodv.library.book.data.service.BookDataService;
import by.rudenkodv.library.book.data.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class BookDataRest {

    @Autowired
    private BookDataService bookDataService;

    @Autowired
    private BookImageService bookImageService;

    @GetMapping(value = "/available")
    public String available() {
        return "rest service is  available";
    }

    @GetMapping(value = "/get/book/data/{id}")
    public BookData getBookData(final @PathVariable(name = "id") String id) {
        return bookDataService.findByBookId(id);
    }

    @GetMapping(value = "/get/image/data/{id}")
    public Image getImage(final @PathVariable(name = "id") String id) {
        return bookImageService.findByBookId(id);
    }

    @PostMapping(value = "save/book/data/{bookId}")
    public String saveBookData(final @RequestBody byte[] data, final @PathVariable String bookId) {
        Assert.noNullElements(new Object[]{data, bookId}, "All data should be fill");
        BookData bookData = new BookData();
        bookData.setBookId(bookId);
        bookData.setData(data);
        BookData bookDataPersistence = bookDataService.save(bookData);
        return bookDataPersistence.getBookId();
    }

    @PostMapping(value = "save/image/data/{bookId}")
    public String saveImageData(final @RequestBody byte[] data, final @PathVariable String bookId) {
        Assert.noNullElements(new Object[]{data, bookId}, "All data should be fill");
        Image image = new Image();
        image.setBookId(bookId);
        image.setData(data);
        Image imagePersistence = bookImageService.save(image);
        return imagePersistence.getId();
    }

    @GetMapping(value = "/delete/all/data")
    public void deleteAllData(){
        bookImageService.deleteAll();;
        bookDataService.deleteAll();
    }
}
