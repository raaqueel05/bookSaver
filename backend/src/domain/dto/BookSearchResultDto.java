package domain.dto;

public class BookSearchResultDto {
    private String title;
    private String author;
    private String coverUrl;
    private String isbn;
    private String genre;
    private Integer totalPages;

    public BookSearchResultDto(String title, String author, String coverUrl,
                               String isbn, String genre, Integer totalPages) {
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.isbn = isbn;
        this.genre = genre;
        this.totalPages = totalPages;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCoverUrl() { return coverUrl; }
    public String getIsbn() { return isbn; }
    public String getGenre() { return genre; }
    public Integer getTotalPages() { return totalPages; }
}
