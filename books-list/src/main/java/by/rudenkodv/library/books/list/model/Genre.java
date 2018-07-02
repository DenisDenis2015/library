package by.rudenkodv.library.books.list.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Genre implements Serializable {

	private static final long serialVersionUID = 4205656669930290800L;

	public Genre() {
	}

	public Genre(String genre) {
		this.genre = genre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
	private Long id;

	@Column
	private String genre;

	@RestResource(path = "getBooksByGenre", rel = "getBooksByGenre")
	@OneToMany(mappedBy = "genre")
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
