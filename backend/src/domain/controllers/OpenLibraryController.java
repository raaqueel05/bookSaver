package domain.controllers;

import domain.dto.BookSearchResultDto;
import domain.services.OpenLibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/open-library")
@CrossOrigin(origins = "*")
public class OpenLibraryController {

    private final OpenLibraryService openLibraryService;

    public OpenLibraryController(OpenLibraryService openLibraryService) {
        this.openLibraryService = openLibraryService;
    }

    @GetMapping("/search")
    public List<BookSearchResultDto> search(@RequestParam String q) {
        return openLibraryService.search(q);
    }
}
