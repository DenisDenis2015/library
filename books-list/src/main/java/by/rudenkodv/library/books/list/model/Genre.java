package by.rudenkodv.library.books.list.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class Genre implements Serializable {

	private static final long serialVersionUID = 4205656669930290800L;

	public Genre() {
	}

	public Genre(String genre) {
		this.genre = genre;
	}

	@Id
	private Long id;

	private String genre;

	private List<Book> book;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Genre [genre=" + getGenre() + ", id=" + getId() +"]";
	}
}
