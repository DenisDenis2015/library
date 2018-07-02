package by.rudenkodv.library.books.list.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = -3846312502042795415L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @Column
    private String title;
    
    @Column
    private String author;
    
    @Column
    private String description;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="genre_id")
    private Genre genre;
    
    @Column
    private Date year;

    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}



	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}



	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}



	/**
	 * @return the year
	 */
	public Date getYear() {
		return year;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @param genre the genre to set
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}



	/**
	 * @param year the year to set
	 */
	public void setYear(Date year) {
		this.year = year;
	}



	@Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description + ", year="
                + year + genre + "]";
    }
    
}
