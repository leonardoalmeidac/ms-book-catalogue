package org.unir.msbookcatalogue.service;

import org.unir.msbookcatalogue.dto.BookResponse;
import org.unir.msbookcatalogue.dto.CreateBookRequest;
import org.unir.msbookcatalogue.dto.UpdateBookRequest;

import java.util.List;

public interface BookService {

    BookResponse createBook(CreateBookRequest request);

    BookResponse updateBook(Long id, UpdateBookRequest request);

    BookResponse patchBook(Long id, UpdateBookRequest request);

    void deleteBook(Long id);

    BookResponse getBookById(Long id);

    List<BookResponse> searchBooks(
            String title,
            String author,
            String category,
            String isbn,
            Integer rating,
            Boolean visible);
}
