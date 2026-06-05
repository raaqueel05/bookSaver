package domain.controllers;

import domain.entities.Book;
import domain.enums.Status;
import domain.services.BookService;
import domain.services.OpenLibraryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
public class ViewController {

    private final BookService bookService;
    private final OpenLibraryService openLibraryService;

    public ViewController(BookService bookService, OpenLibraryService openLibraryService) {
        this.bookService = bookService;
        this.openLibraryService = openLibraryService;
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) Status status) {
        List<Book> allBooks = bookService.getAll();
        List<Book> displayBooks = status != null
                ? allBooks.stream().filter(b -> b.getStatus() == status).toList()
                : allBooks;

        model.addAttribute("books", displayBooks);
        model.addAttribute("activeStatus", status != null ? status.name() : "");
        model.addAttribute("countReading", allBooks.stream().filter(b -> b.getStatus() == Status.READING).count());
        model.addAttribute("countRead",    allBooks.stream().filter(b -> b.getStatus() == Status.READ).count());
        model.addAttribute("countPending", allBooks.stream().filter(b -> b.getStatus() == Status.PENDING).count());
        model.addAttribute("countPaused",  allBooks.stream().filter(b -> b.getStatus() == Status.PAUSED).count());
        return "index";
    }

    @GetMapping("/search-books")
    public String searchBooks(Model model, @RequestParam(required = false) String q) {
        if (q != null && !q.isBlank()) {
            model.addAttribute("results", openLibraryService.search(q));
        }
        model.addAttribute("query", q != null ? q : "");
        return "search";
    }

    @PostMapping("/books/add")
    public String addBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam(required = false) String coverUrl,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer totalPages) {
        bookService.save(new Book(title, author, coverUrl, isbn, genre, totalPages));
        return "redirect:/";
    }

    @GetMapping("/books/{id}")
    public String bookDetail(@PathVariable UUID id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        model.addAttribute("statuses", Status.values());
        return "book";
    }

    @PostMapping("/books/{id}/update")
    public String updateBook(
            @PathVariable UUID id,
            @RequestParam Status status,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String review,
            @RequestParam(required = false) Integer currentPage,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStarted,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinished) {

        Book updated = new Book();
        updated.setStatus(status);
        updated.setRating(rating);
        updated.setReview(review != null && !review.isBlank() ? review : null);
        updated.setCurrentPage(currentPage);
        updated.setDateStarted(dateStarted);
        updated.setDateFinished(dateFinished);
        bookService.update(id, updated);
        return "redirect:/";
    }

    @PostMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable UUID id) {
        bookService.delete(id);
        return "redirect:/";
    }
}
