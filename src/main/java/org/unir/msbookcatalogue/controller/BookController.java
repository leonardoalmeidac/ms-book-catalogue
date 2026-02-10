package org.unir.msbookcatalogue.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unir.msbookcatalogue.dto.BookResponse;
import org.unir.msbookcatalogue.dto.CreateBookRequest;
import org.unir.msbookcatalogue.dto.UpdateBookRequest;
import org.unir.msbookcatalogue.service.BookService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody CreateBookRequest request) {

        BookResponse created = bookService.createBook(request);
        URI location = URI.create("/api/v1/books/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookRequest request) {

        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> patchBook(
            @PathVariable Long id,
            @RequestBody UpdateBookRequest request) {

        return ResponseEntity.ok(bookService.patchBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Boolean visible
    ) {
        List<BookResponse> books = bookService.searchBooks(
                title, author, category, isbn, rating, visible
        );
        return ResponseEntity.ok(books);
    }
}
