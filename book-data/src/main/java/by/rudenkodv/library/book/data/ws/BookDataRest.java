package by.rudenkodv.library.book.data.ws;


import by.rudenkodv.library.book.data.service.BookDataService;
import by.rudenkodv.library.book.data.service.BookImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public byte[] getBookData(final @PathVariable(name = "id") String id) {
        return new byte[0];
    }

    @GetMapping(value = "/get/image/data/{id}")
    public byte[] getImage(final @PathVariable(name = "id") String id) {
        return new byte[0];
    }

    @PostMapping(value = "save/book/data")
    public String  saveBookData(final @PathVariable(name = "data") byte[] data){
        return "id";
    }

    @PostMapping(value = "save/image/data")
    public String  saveImageData(final @PathVariable(name = "data") byte[] data){
        return "id";
    }
}
