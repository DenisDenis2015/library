package by.rudenkodv.library.books.list.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Data
@Document
public class Genre implements Serializable {

	private static final long serialVersionUID = 4205656669930290800L;

	@Id
	private String id;

	@NonNull
	private String name;

	private Long count;

}
