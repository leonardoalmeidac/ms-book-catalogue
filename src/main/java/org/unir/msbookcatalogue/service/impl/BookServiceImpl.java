package org.unir.msbookcatalogue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.unir.msbookcatalogue.dto.BookResponse;
import org.unir.msbookcatalogue.dto.CreateBookRequest;
import org.unir.msbookcatalogue.dto.UpdateBookRequest;
import org.unir.msbookcatalogue.exception.BookNotFoundException;
import org.unir.msbookcatalogue.model.Book;
import org.unir.msbookcatalogue.repository.BookRepository;
import org.unir.msbookcatalogue.service.BookService;

import java.util.List;

import static org.unir.msbookcatalogue.repository.specification.BookSpecification.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(CreateBookRequest request) {
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publicationDate(request.getPublicationDate())
                .category(request.getCategory())
                .isbn(request.getIsbn())
                .rating(request.getRating())
                .visible(request.getVisible() != null ? request.getVisible() : true)
                .build();

        return mapToResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse updateBook(Long id, UpdateBookRequest request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublicationDate(request.getPublicationDate());
        book.setCategory(request.getCategory());
        book.setIsbn(request.getIsbn());
        book.setRating(request.getRating());
        book.setVisible(request.getVisible());

        return mapToResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse patchBook(Long id, UpdateBookRequest request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if (request.getPublicationDate() != null) {
            book.setPublicationDate(request.getPublicationDate());
        }
        if (request.getCategory() != null) {
            book.setCategory(request.getCategory());
        }
        if (request.getIsbn() != null) {
            book.setIsbn(request.getIsbn());
        }
        if (request.getRating() != null) {
            book.setRating(request.getRating());
        }
        if (request.getVisible() != null) {
            book.setVisible(request.getVisible());
        }
        return mapToResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return mapToResponse(book);
    }

    @Override
    public List<BookResponse> searchBooks(
            String title,
            String author,
            String publicationDate,
            String category,
            String isbn,
            Integer rating,
            Boolean visible) {
        Specification<Book> specification = Specification
                .where(hasTitle(title))
                .and(hasAuthor(author))
                .and(hasPublicationDate(publicationDate))
                .and(hasCategory(category))
                .and(hasIsbn(isbn))
                .and(hasRating(rating))
                .and(isVisible(visible));

        return bookRepository.findAll(specification)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private BookResponse mapToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationDate(book.getPublicationDate())
                .category(book.getCategory())
                .isbn(book.getIsbn())
                .rating(book.getRating())
                .visible(book.getVisible())
                .build();
    }
}