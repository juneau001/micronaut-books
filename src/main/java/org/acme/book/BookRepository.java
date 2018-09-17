package org.acme.book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.acme.domain.Book;

/**
 *
 */
public interface BookRepository {
    Book save(LocalDate publishDate, String title, String authorFirst, String authorLast, Long pages);

    Optional<Book> findById(Long id);

    void deleteById(Long id);

    List<Book> findAll();

    int update(Long id, LocalDate publishDate, String title, String authorFirst, String authorLast, Long pages);
}
