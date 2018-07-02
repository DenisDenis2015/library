package by.rudenkodv.library.books.list.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
public class Book implements Serializable {

	private static final long serialVersionUID = -3846312502042795415L;

	@Id
    private Long id;
    
    private String title;
    
    private String author;
    
    private String description;
    
    private Genre genre;
    
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
