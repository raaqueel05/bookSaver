package domain.services;

import domain.entities.Book;
import domain.enums.Status;
import domain.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getByStatus(Status status) {
        return bookRepository.findByStatus(status);
    }

    public Book getById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Llibre no trobat: " + id));
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book update(UUID id, Book updatedBook) {
        Book book = getById(id);
        book.setStatus(updatedBook.getStatus());
        book.setRating(updatedBook.getRating());
        book.setReview(updatedBook.getReview());
        book.setCurrentPage(updatedBook.getCurrentPage());
        book.setDateStarted(updatedBook.getDateStarted());
        book.setDateFinished(updatedBook.getDateFinished());

        if (book.getStatus() == Status.READ && book.getTotalPages() != null) {
            book.setCurrentPage(book.getTotalPages());
        }

        return bookRepository.save(book);
    }

    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }
}
