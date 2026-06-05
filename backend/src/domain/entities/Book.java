package domain.entities;

import domain.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookId;

    private String title;
    private String author;
    private String coverUrl;
    private String isbn;
    private String genre;
    private Integer totalPages;
    private Integer currentPage;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer rating;

    @Column(length = 2000)
    private String review;

    private LocalDate dateAdded;
    private LocalDate dateStarted;
    private LocalDate dateFinished;

    public Book() {}

    // Constructor per afegir un llibre des de Google Books API
    public Book(String title, String author, String coverUrl, String isbn, String genre, Integer totalPages) {
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.isbn = isbn;
        this.genre = genre;
        this.totalPages = totalPages;
        this.currentPage = 0;
        this.status = Status.PENDING;
        this.dateAdded = LocalDate.now();
    }

    // Constructor complet
    public Book(UUID bookId, String title, String author, String coverUrl, String isbn, String genre,
                Integer totalPages, Integer currentPage, Status status, Integer rating,
                String review, LocalDate dateAdded, LocalDate dateStarted, LocalDate dateFinished) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.isbn = isbn;
        this.genre = genre;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.status = status;
        this.rating = rating;
        this.review = review;
        this.dateAdded = dateAdded;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
    }

    public UUID getBookId() { return bookId; }
    public void setBookId(UUID bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCoverUrl() { return coverUrl; }
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getTotalPages() { return totalPages; }
    public void setTotalPages(Integer totalPages) { this.totalPages = totalPages; }

    public Integer getCurrentPage() { return currentPage; }
    public void setCurrentPage(Integer currentPage) { this.currentPage = currentPage; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public LocalDate getDateAdded() { return dateAdded; }
    public void setDateAdded(LocalDate dateAdded) { this.dateAdded = dateAdded; }

    public LocalDate getDateStarted() { return dateStarted; }
    public void setDateStarted(LocalDate dateStarted) { this.dateStarted = dateStarted; }

    public LocalDate getDateFinished() { return dateFinished; }
    public void setDateFinished(LocalDate dateFinished) { this.dateFinished = dateFinished; }
}
