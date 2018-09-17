package org.acme;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.acme.domain.Book;
import org.acme.domain.BookSaveOperation;
import org.acme.domain.BookUpdateOperation;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test cases for BookController
 */
public class BookControllerTest {

    private static EmbeddedServer server;
    private static HttpClient client;
    private  Book book;

    HttpRequest request;
    HttpResponse response;
    Long id;
    List<Long> bookIds = new ArrayList<>();

    @BeforeClass
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(HttpClient.class, server.getURL());
    }

    @AfterClass
    public static void stopServer() {
        if (server != null) {
            server.stop();
        }
        if (client != null) {
            client.stop();
        }
    }

    @Test
    public void testInsertBooks() {

        request = HttpRequest.POST("/books", new BookSaveOperation(LocalDate.now(), "Java EE 8 Recipes", "Josh", "Juneau", new Long(750)));
        response = client.toBlocking().exchange(request);

        assertEquals(HttpStatus.CREATED, response.getStatus());

        request = HttpRequest.POST("/books", new BookSaveOperation(LocalDate.now(), "Java 9 Recipes", "Josh", "Juneau", new Long(600)));
        response = client.toBlocking().exchange(request);
        id = entityId(response, "/books/");

        assertEquals(HttpStatus.CREATED, response.getStatus());

       
    }

    @Test
    public void testBookRetrieve() {
        request = HttpRequest.GET("/books");
        List<Book> books = client.toBlocking().retrieve(request, Argument.of(List.class, Book.class));
        // Populate a book instance for later
        for(Book b:books){
            book = b;
        }
        assertEquals(2, books.size());

        

    }
    
    @Test
    public void testBookOperations() {
        request = HttpRequest.GET("/books");
        List<Book> books = client.toBlocking().retrieve(request, Argument.of(List.class, Book.class));
        // Populate a book instance for later
        for(Book b:books){
            book = b;
        }
        
        request = HttpRequest.PUT("/books/", new BookUpdateOperation(book.getId(),
                book.getPublishDate(),
                "Java 10 Recipes",
                book.getAuthorFirst(),
                book.getAuthorLast(),
                book.getPages()));

        response = client.toBlocking().exchange(request);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());

        request = HttpRequest.GET("/books/" + book.getId());
        book = client.toBlocking().retrieve(request, Book.class);
        assertEquals("Java 10 Recipes", book.getTitle());

        testDelete();

    }
    
    
    public void testDelete(){
       request = HttpRequest.GET("/books");
        List<Book> books = client.toBlocking().retrieve(request, Argument.of(List.class, Book.class));
        // Populate a book instance for later
        for(Book b:books){
            request = HttpRequest.DELETE("/books/" + b.getId());
            response = client.toBlocking().exchange(request);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
        }
    }

    Long entityId(HttpResponse response, String path) {
        String value = response.header(HttpHeaders.LOCATION);
        if (value == null) {
            return null;
        }
        int index = value.indexOf(path);
        if (index != -1) {
            return Long.valueOf(value.substring(index + path.length()));
        }
        return null;
    }
}
