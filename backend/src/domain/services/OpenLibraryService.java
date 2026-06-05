package domain.services;

import domain.dto.BookSearchResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OpenLibraryService {

    private static final Logger log = LoggerFactory.getLogger(OpenLibraryService.class);
    private static final String OPEN_LIBRARY_URL = "https://openlibrary.org/search.json";

    private final RestTemplate restTemplate;

    public OpenLibraryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<BookSearchResultDto> search(String query) {
        URI uri = UriComponentsBuilder.fromHttpUrl(OPEN_LIBRARY_URL)
                .queryParam("q", "{q}")
                .queryParam("limit", 10)
                .queryParam("fields", "title,author_name,cover_i,isbn,subject,number_of_pages_median")
                .buildAndExpand(query)
                .encode()
                .toUri();

        log.info("Cercant a Open Library: {}", uri);

        Map<String, Object> response;
        try {
            response = restTemplate.getForObject(uri, Map.class);
        } catch (Exception e) {
            log.error("Error cridant Open Library API: {}", e.getMessage());
            return new ArrayList<>();
        }

        List<BookSearchResultDto> results = new ArrayList<>();
        if (response == null || !response.containsKey("docs")) return results;

        List<Map<String, Object>> docs = (List<Map<String, Object>>) response.get("docs");
        for (Map<String, Object> doc : docs) {
            try {
                results.add(mapToDto(doc));
            } catch (Exception e) {
                log.warn("Error parsejant un resultat, es salta: {}", e.getMessage());
            }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    private BookSearchResultDto mapToDto(Map<String, Object> doc) {
        String title = (String) doc.get("title");

        List<String> authors = (List<String>) doc.get("author_name");
        String author = (authors != null && !authors.isEmpty()) ? authors.get(0) : "Desconegut";

        List<String> subjects = (List<String>) doc.get("subject");
        String genre = (subjects != null && !subjects.isEmpty()) ? subjects.get(0) : null;

        Object pagesRaw = doc.get("number_of_pages_median");
        Integer totalPages = pagesRaw != null ? ((Number) pagesRaw).intValue() : null;

        Object coverIdRaw = doc.get("cover_i");
        String coverUrl = coverIdRaw != null
                ? "https://covers.openlibrary.org/b/id/" + coverIdRaw + "-M.jpg"
                : null;

        List<String> isbns = (List<String>) doc.get("isbn");
        String isbn = (isbns != null && !isbns.isEmpty()) ? isbns.get(0) : null;

        return new BookSearchResultDto(title, author, coverUrl, isbn, genre, totalPages);
    }
}
