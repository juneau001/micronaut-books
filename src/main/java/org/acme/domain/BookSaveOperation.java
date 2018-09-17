
package org.acme.domain;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Josh Juneau
 */
public class BookSaveOperation implements java.io.Serializable {
    
    
    @NotNull
    private LocalDate publishDate;
    
    @NotNull
    @NotBlank
    private String title;
    
    @NotNull
    @NotBlank
    private String authorFirst;
    
    @NotNull
    @NotBlank
    private String authorLast;
    
    private Long pages;
    
    public BookSaveOperation(){}
    
    public BookSaveOperation(LocalDate publishDate, String title,
                    String authorFirst, String authorLast, Long pages){
        this.publishDate = publishDate;
        this.title = title;
        this.authorFirst = authorFirst;
        this.authorLast = authorLast;
        this.pages = pages;
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
}
