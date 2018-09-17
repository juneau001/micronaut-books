
package org.acme.book;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.acme.domain.Book;

/**
 * Business logic for the service.
 */
@Singleton
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public BookRepositoryImpl(@CurrentSession EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional
    public Book save(LocalDate publishDate, String title, String authorFirst, String authorLast, Long pages) {
        Book book = new Book(publishDate, title, authorFirst, authorLast, pages);
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return entityManager
                .createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Override
    @Transactional
    public int update(Long id, LocalDate publishDate, String title, String authorFirst, String authorLast, Long pages) {
        return entityManager.createQuery("UPDATE Book b SET publishDate = :publishDate, title = :title, " +
                "authorFirst = :authorFirst, authorLast = :authorLast, pages = :pages where id = :id")
                .setParameter("publishDate", publishDate)
                .setParameter("title", title)
                .setParameter("authorFirst", authorFirst)
                .setParameter("authorLast", authorLast)
                .setParameter("pages", pages)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id).ifPresent(book -> entityManager.remove(book));
    }

}
