package domain.repositories;

import domain.entities.Book;
import domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByStatus(Status status);

    List<Book> findByTitleContainingIgnoreCase(String title);
}
