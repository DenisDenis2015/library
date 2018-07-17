package by.rudenkodv.library.books.list.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Data
@Document
public class Book implements Serializable {

	private static final long serialVersionUID = -3846312502042795415L;

	@Id
    private String id;
    
    private String title, author, description;
    
    private Genre genre;
    
    private Date year;
}
