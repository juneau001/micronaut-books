
package org.acme.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * JPA Mappings for the BOOK database table.
 */
@Entity
@Table(name="BOOK")
public class Book {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,
    generator="book_generator")
    @SequenceGenerator(name="book_generator",sequenceName="book_s", allocationSize=1)
    private Long id;
    
    @Column(name="PUBLISH_DATE")
    @NotNull
    private LocalDate publishDate;
    
    @Column(name="TITLE")
    @NotNull
    private String title;
    
    @Column(name="AUTHOR_FIRST")
    @NotNull
    private String authorFirst;
    
    @Column(name="AUTHOR_LAST")
    @NotNull
    private String authorLast;
    
    private Long pages;
    
    public Book(){}
    
    public Book(@NotNull Long id, @NotNull LocalDate publishDate, @NotNull String title, String authorFirst, String authorLast, Long pages){
        this.id = id;
        this.publishDate = publishDate;
        this.title = title;
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.pages = pages;
    }

    public Book(@NotNull LocalDate publishDate, @NotNull String title, String authorFirst, String authorLast, Long pages){
        this.publishDate = publishDate;
        this.title = title;
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.pages = pages;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the publishDate
     */
    public LocalDate getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate the publishDate to set
     */
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the authorFirst
     */
    public String getAuthorFirst() {
        return authorFirst;
    }

    /**
     * @param authorFirst the authorFirst to set
     */
    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    /**
     * @return the authorLast
     */
    public String getAuthorLast() {
        return authorLast;
    }

    /**
     * @param authorLast the authorLast to set
     */
    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }

    /**
     * @return the pages
     */
    public Long getPages() {
        return pages;
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(Long pages) {
        this.pages = pages;
    }
   
    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", publishDate='" + publishDate + '\'' +
            ", title='" + title + '\'' +
            ", authorFirst='" + authorFirst + '\'' +
            ", authorLast='" + authorLast + '\'' +
            ", pages='" + pages +
            '}';
    }
}
