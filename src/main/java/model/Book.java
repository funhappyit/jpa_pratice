package model;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

// @Entity
// @DiscriminatorValue("B")
// @PrimaryKeyJoinColumn(name="BOOK_ID")
public class Book extends Item{
	private String author;//작가
	private String isbn;

}
