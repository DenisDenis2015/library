package by.rudenkodv.library.book.data.model;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Data
@Document
public class Image implements Serializable {

    @Id
    private String id;
    private String bookId;
    private byte[] data;
}
