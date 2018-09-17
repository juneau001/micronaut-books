package org.acme.book;

import org.acme.domain.Book;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import org.acme.domain.BookSaveOperation;
import org.acme.domain.BookUpdateOperation;

@Validated
@Controller("/books")
public class BookController {

    protected final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Get("/")
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Put("/")
    public HttpResponse update(@Body @Valid BookUpdateOperation operation) {

        bookRepository.update(operation.getId(), operation.getPublishDate(),
                operation.getTitle(), operation.getAuthorFirst(), operation.getAuthorLast(), operation.getPages());
        return HttpResponse.noContent().header(HttpHeaders.LOCATION, location(operation.getId()).getPath());

    }

    @Get("/{id}")
    Book show(Long id) {
        return bookRepository
                .findById(id)
                .orElse(null);
    }

    @Delete("/{id}")
    HttpResponse delete(Long id) {
        bookRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    @Post("/")
    HttpResponse<Book> save(@Body @Valid BookSaveOperation operation) {

        Book book = bookRepository.save(operation.getPublishDate(), operation.getTitle(),
                operation.getAuthorFirst(), operation.getAuthorLast(), operation.getPages());
        return HttpResponse
                .created(book)
                .headers(headers -> headers.location(location(book)));
    }

    protected URI location(Book book) {
        return location(book.getId());
    }

    protected URI location(Long id) {
        return URI.create("/books/" + id);
    }

}
